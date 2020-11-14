package com.luthfihariz.almasgocore.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.dto.request.FilterRequestDto;
import com.luthfihariz.almasgocore.controller.dto.request.RangeFilterDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;
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
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;


@Repository
public class SearchableContentRepositoryImpl implements SearchableContentRepository {

    @Autowired
    RestHighLevelClient restHighLevelClient;

    @Autowired
    ObjectMapper objectMapper;

    public void save(Content content, Long userId) throws IOException {
        String json = objectMapper.writeValueAsString(content);
        Map<String, Object> map = objectMapper.readValue(json, Map.class);

        IndexRequest indexRequest = Requests.indexRequest(getIndexNameFromUserId(userId));
        indexRequest.id(content.getId().toString());
        indexRequest.source(map);

        RequestOptions options = RequestOptions.DEFAULT;
        restHighLevelClient.index(indexRequest, options);
    }

    @Override
    public SearchHit[] search(SearchQuery searchQuery, Long userId) throws IOException {
        SearchRequest searchRequest = Requests.searchRequest(getIndexNameFromUserId(userId));

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

                    RangeQueryBuilder rangeQueryBuilder = QueryBuilders
                            .rangeQuery(keyToFilter)
                            .lte(valueToFilter.getLte())
                            .lt(valueToFilter.getLt())
                            .gt(valueToFilter.getGt())
                            .gte(valueToFilter.getGte());

                    boolQueryBuilder.filter(rangeQueryBuilder);
                }

            } else if (filter.getMatch() != null) {
                for (String keyToFilter : filter.getMatch().keySet()) {
                    Object valueToFilter = filter.getMatch().get(keyToFilter);

                    boolQueryBuilder.filter(QueryBuilders.termQuery(keyToFilter, valueToFilter));
                }
            }
        }

        searchRequest.source(new SearchSourceBuilder().query(boolQueryBuilder));
        SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return searchResponse.getHits().getHits();
    }

    @Override
    public DeleteResponse delete(Long contentId, Long userId) throws IOException {
        DeleteRequest deleteRequest = Requests.deleteRequest(getIndexNameFromUserId(userId));
        deleteRequest.id(contentId.toString());
        return restHighLevelClient.delete(deleteRequest, RequestOptions.DEFAULT);
    }

    @Override
    public void update(Content content, Long userId) throws IOException {
        delete(content.getId(), userId);
        save(content, userId);
    }

    private String getIndexNameFromUserId(Long userId) {
        return "content_u" + userId;
    }
}
