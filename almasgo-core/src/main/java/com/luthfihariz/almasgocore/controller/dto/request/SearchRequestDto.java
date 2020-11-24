package com.luthfihariz.almasgocore.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;


@Getter
@Setter
@NoArgsConstructor
public class SearchRequestDto {
    private Integer page;
    private Integer size;
    private String query;
    private FilterRequestDto filter;
}
