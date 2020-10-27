package com.luthfihariz.almasgocore.controller.dto.mapper;

import com.luthfihariz.almasgocore.controller.dto.request.SearchRequestDto;
import com.luthfihariz.almasgocore.service.dto.SearchQuery;

public class SearchQueryMapper {

    public static SearchQuery fromSearchRequestDto(SearchRequestDto searchRequestDto) {
        return new SearchQuery(searchRequestDto.getQuery(), searchRequestDto.getFilter());
    }
}
