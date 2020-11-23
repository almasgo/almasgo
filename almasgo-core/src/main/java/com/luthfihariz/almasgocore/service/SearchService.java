package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.search.SearchHit;

import java.io.IOException;

public interface SearchService {
    SearchHit[] search(SearchQuery searchQuery, Long engineId) throws IOException;
}
