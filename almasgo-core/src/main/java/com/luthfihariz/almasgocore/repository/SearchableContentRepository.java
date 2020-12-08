package com.luthfihariz.almasgocore.repository;

import com.luthfihariz.almasgocore.controller.dto.response.ContentBulkResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;
import java.util.List;

public interface SearchableContentRepository {

    void save(Content content, Long engineId) throws IOException;
    ContentBulkResponseDto saveAll(List<Content> content, Long engineId) throws IOException;
    SearchResponse search(SearchQuery searchQuery, Long engineId) throws IOException;
    DeleteResponse delete(Long contentId, Long engineId) throws IOException;
    void update(Content content, Long engineId) throws IOException;
}
