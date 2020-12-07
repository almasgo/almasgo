package com.luthfihariz.almasgocore.controller.v1.dto.request;

import com.luthfihariz.almasgocore.controller.dto.request.FilterRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class SearchRequestDto {
    private Integer page;
    private Integer size;
    private String query;
    private FilterRequestDto filter;
}
