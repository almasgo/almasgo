package com.luthfihariz.almasgocore.controller.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContentBulkResponseDto {
    private Integer success;
    private Integer fail;
    private Integer total;
}
