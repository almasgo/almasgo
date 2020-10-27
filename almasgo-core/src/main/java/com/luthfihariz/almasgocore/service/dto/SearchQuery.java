package com.luthfihariz.almasgocore.service.dto;

import com.luthfihariz.almasgocore.controller.dto.request.FilterRequestDto;
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

}
