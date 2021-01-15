package com.luthfihariz.almasgocore.controller.dto.request;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SortRequestDto {
    private String field;
    private SortOrder order;
}
