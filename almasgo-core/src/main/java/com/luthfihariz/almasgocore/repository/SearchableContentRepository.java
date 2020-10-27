package com.luthfihariz.almasgocore.repository;

import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;

public interface SearchableContentRepository {

    void save(Content content, Long userId) throws IOException;
    SearchHit[] search(SearchQuery searchQuery, Long userId) throws IOException;
}
