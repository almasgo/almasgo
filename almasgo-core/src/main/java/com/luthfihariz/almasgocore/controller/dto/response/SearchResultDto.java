package com.luthfihariz.almasgocore.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@Getter
@AllArgsConstructor
public class SearchResultDto {
    Double score;
    Map<String, Object> content;
}
