package com.luthfihariz.almasgocore.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
public class FilterRequestDto {
    private Map<String, Object> match;
    private Map<String, RangeFilterDto> range;
}
