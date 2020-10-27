package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.repository.SearchableContentRepository;
import com.luthfihariz.almasgocore.repository.UserRepository;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private SearchableContentRepository searchableContentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public SearchHit[] search(SearchQuery searchQuery, String email) throws IOException {
        User loggedInUser = userRepository.findByEmail(email);

        return searchableContentRepository.search(searchQuery, loggedInUser.getId());
    }
}
