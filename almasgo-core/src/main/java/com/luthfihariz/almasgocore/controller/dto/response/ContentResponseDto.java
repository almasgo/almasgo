package com.luthfihariz.almasgocore.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Setter
public class ContentResponseDto {
    private Long id;
    private String uniqueExternalId;
    private String title;
    private String description;
    private Integer popularityInPercentage;
    private Integer visibility;
    private List<String> tags;
    private Map<String, Object> attributes;
}
