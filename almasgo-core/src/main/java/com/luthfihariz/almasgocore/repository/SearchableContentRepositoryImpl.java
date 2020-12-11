package com.luthfihariz.almasgocore.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.dto.request.FilterRequestDto;
import com.luthfihariz.almasgocore.controller.dto.request.RangeFilterDto;
import com.luthfihariz.almasgocore.controller.dto.request.SortOrder;
import com.luthfihariz.almasgocore.controller.dto.response.ContentBulkResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.security.AuthenticationFacade;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.Requests;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.RangeQueryBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;


@Repository
public class SearchableContentRepositoryImpl implements SearchableContentRepository {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public void save(Content content, Long engineId) throws IOException {
        String json = objectMapper.writeValueAsString(content);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        IndexRequest indexRequest = Requests.indexRequest(getIndexName(engineId));
        indexRequest.id(content.getId().toString());
        indexRequest.source(map);

        RequestOptions options = RequestOptions.DEFAULT;
        restHighLevelClient.index(indexRequest, options);
    }

    public ContentBulkResponseDto saveAll(List<Content> contents, Long engineId) throws IOException {
        BulkRequest bulkRequest = Requests.bulkRequest();

        AtomicReference<Integer> failure = new AtomicReference<>(0);
        contents.forEach(content -> {

            try {
                String json = objectMapper.writeValueAsString(content);
                Map<String, Object> map = objectMapper.readValue(json, Map.class);

                IndexRequest indexRequest = Requests
                        .indexRequest(getIndexName(engineId))
                        .source(map);
                bulkRequest.add(indexRequest);
            } catch (JsonProcessingException e) {
                failure.getAndSet(failure.get() + 1);
            }
        });


        RequestOptions options = RequestOptions.DEFAULT;
        BulkResponse responses = restHighLevelClient.bulk(bulkRequest, options);
        responses.forEach(response -> {
            if (response.isFailed()) {
                failure.getAndSet(failure.get() + 1);
            }
        });

        return new ContentBulkResponseDto(contents.size() - failure.get(), failure.get(), contents.size());
    }

    @Override
    public SearchResponse search(SearchQuery searchQuery, Long engineId) throws IOException {
        SearchRequest searchRequest = Requests.searchRequest(getIndexName(engineId));


        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery()
                .should(QueryBuilders.matchQuery("title", searchQuery.getQuery()))
                .should(QueryBuilders.matchQuery("description", searchQuery.getQuery()))
                .should(QueryBuilders.matchQuery("tag", searchQuery.getQuery()));

        String[] queryWords = searchQuery.getQuery().split(" ");
        for (String query : queryWords) {
            boolQueryBuilder = boolQueryBuilder.should(new MatchQueryBuilder("tag", query));
        }

        if (searchQuery.getFilter() != null) {
            FilterRequestDto filter = searchQuery.getFilter();
            if (filter.getRange() != null) {
                for (String keyToFilter : filter.getRange().keySet()) {
                    RangeFilterDto valueToFilter = filter.getRange().get(keyToFilter);

                    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(keyToFilter);

                    if (valueToFilter.getLte() != null) {
                        rangeQueryBuilder.lte(valueToFilter.getLte());
                    }

                    if (valueToFilter.getLt() != null) {
                        rangeQueryBuilder.lt(valueToFilter.getLt());
                    }

                    if (valueToFilter.getGt() != null) {
                        rangeQueryBuilder.gt(valueToFilter.getGt());
                    }

                    if (valueToFilter.getGte() != null) {
                        rangeQueryBuilder.gte(valueToFilter.getGte());
                    }

                    boolQueryBuilder.filter(rangeQueryBuilder);
                }

            } else if (filter.getMatch() != null) {
                for (String keyToFilter : filter.getMatch().keySet()) {
                    Object valueToFilter = filter.getMatch().get(keyToFilter).toString().toLowerCase();

                    boolQueryBuilder.filter(QueryBuilders.termQuery(keyToFilter, valueToFilter));
                }
            }
        }

        var sortOrder = org.elasticsearch.search.sort.SortOrder.ASC;
        if (searchQuery.getSort().getOrder().equals(SortOrder.DESCENDING)) {
            sortOrder = org.elasticsearch.search.sort.SortOrder.DESC;
        }

        SearchSourceBuilder searchSourceBuilder = SearchSourceBuilder.searchSource()
                .from(searchQuery.getPage() * searchQuery.getSize())
                .size(searchQuery.getSize())
                .query(boolQueryBuilder)
                .sort(searchQuery.getSort().getField(), sortOrder);

        searchRequest.source(searchSourceBuilder);
        return restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
    }

    @Override
    public DeleteResponse delete(Long contentId, Long engineId) throws IOException {
        DeleteRequest deleteRequest = Requests.deleteRequest(getIndexName(engineId));
        deleteRequest.id(contentId.toString());
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void update(Content content, Long engineId) throws IOException {
        delete(content.getId(), engineId);
        save(content, engineId);
    }

    private String getIndexName(Long engineId) {
        return "content_e" + engineId + "_u" + getLoggedInUserId();
    }

    private Long getLoggedInUserId() {
        return authenticationFacade.getSearchClientPrincipal().getUserId();
    }
}
