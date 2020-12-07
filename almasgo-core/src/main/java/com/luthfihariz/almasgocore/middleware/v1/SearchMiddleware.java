package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.action.search.SearchResponse;

import java.io.IOException;

public interface SearchMiddleware {
    SearchResponse search(SearchQuery searchQuery, Long engineId) throws IOException;
}
