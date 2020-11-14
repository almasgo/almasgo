package com.luthfihariz.almasgocore.repository;

import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;

public interface SearchableContentRepository {

    void save(Content content, Long userId) throws IOException;
    SearchHit[] search(SearchQuery searchQuery, Long userId) throws IOException;
    DeleteResponse delete(Long contentId, Long userId) throws IOException;
    void update(Content content, Long userId) throws IOException;
}
