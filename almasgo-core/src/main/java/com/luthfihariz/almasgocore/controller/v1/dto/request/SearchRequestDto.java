package com.luthfihariz.almasgocore.controller.v1.dto.request;

import com.luthfihariz.almasgocore.controller.dto.request.FilterRequestDto;
import com.luthfihariz.almasgocore.controller.dto.request.SortRequestDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestDto {
    private Integer page;
    private Integer size;
    private String query;
    private FilterRequestDto filter;
    private SortRequestDto sort;
}
