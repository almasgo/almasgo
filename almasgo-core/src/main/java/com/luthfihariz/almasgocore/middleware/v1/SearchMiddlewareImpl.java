package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.service.SearchService;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchMiddlewareImpl implements SearchMiddleware{

    @Autowired
    private SearchService searchService;

    @Override
    public SearchResponse search(SearchQuery searchQuery, Long engineId) throws IOException {
        return searchService.search(searchQuery, engineId);
    }
}
