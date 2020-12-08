package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.repository.SearchableContentRepository;
import com.luthfihariz.almasgocore.repository.UserRepository;
import com.luthfihariz.almasgocore.security.AuthenticationFacade;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchableContentRepository searchableContentRepository;

    @Override
    public SearchResponse search(SearchQuery searchQuery, Long engineId) throws IOException {
        if (searchQuery.getSize() == null || searchQuery.getSize() > 20) {
            searchQuery.setSize(20);
        }

        if (searchQuery.getPage() == null || searchQuery.getPage() > 10000) {
            searchQuery.setPage(0);
        }

        return searchableContentRepository.search(searchQuery, engineId);
    }
}
