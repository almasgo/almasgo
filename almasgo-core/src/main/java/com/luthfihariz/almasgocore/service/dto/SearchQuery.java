package com.luthfihariz.almasgocore.service.dto;

import com.luthfihariz.almasgocore.controller.dto.request.FilterRequestDto;
import com.luthfihariz.almasgocore.controller.dto.request.SortRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchQuery {
    private String query;
    private FilterRequestDto filter;
    private SortRequestDto sort;
    private Integer page;
    private Integer size;
}
