package com.luthfihariz.almasgocore.controller.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RangeFilterDto {
    private String lte;
    private String gte;
    private String lt;
    private String gt;
}