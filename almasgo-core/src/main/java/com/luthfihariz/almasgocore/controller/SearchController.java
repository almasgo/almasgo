package com.luthfihariz.almasgocore.controller;

import com.luthfihariz.almasgocore.controller.dto.mapper.SearchQueryMapper;
import com.luthfihariz.almasgocore.controller.dto.request.SearchRequestDto;
import com.luthfihariz.almasgocore.service.SearchService;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/search")
public class SearchController {

    @Autowired
    private SearchService searchService;

    @PostMapping
    public List<SearchHit> search(@RequestBody SearchRequestDto searchRequestDto, Principal principal) throws IOException {
        return Arrays.asList(searchService.search(SearchQueryMapper.fromSearchRequestDto(searchRequestDto), principal.getName()));
    }
}
