package com.luthfihariz.almasgocore.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RangeFilterDto {
    private Double lte;
    private Double gte;
    private Double lt;
    private Double gt;
}