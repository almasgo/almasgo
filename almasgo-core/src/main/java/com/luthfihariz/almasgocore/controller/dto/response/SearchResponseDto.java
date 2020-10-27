package com.luthfihariz.almasgocore.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class SearchResponseDto {
    private Long searchTimestamp;
    private Integer totalHit;
    private List<Map<String, Object>> hits;
}
