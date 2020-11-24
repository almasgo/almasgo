package com.luthfihariz.almasgocore.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SearchResponseDto {
    private Long took;
    private Integer page;
    private Integer size;
    private Integer count;
    private Double maxScore;
    private List<SearchResultDto> results;
}
