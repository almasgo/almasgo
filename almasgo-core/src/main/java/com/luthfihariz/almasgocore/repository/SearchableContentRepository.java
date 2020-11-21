package com.luthfihariz.almasgocore.repository;

import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;

public interface SearchableContentRepository {

    void save(Content content, Long engineId) throws IOException;
    SearchHit[] search(SearchQuery searchQuery, Long engineId) throws IOException;
    DeleteResponse delete(Long contentId, Long engineId) throws IOException;
    void update(Content content, Long engineId) throws IOException;
}
