package com.luthfihariz.almasgocore.controller.v1.dto.request;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentRequestDto {
    private String externalUniqueId;
    private String title;
    private String description;
    private Integer visibility;
    private List<String> tags;
    private Map<String, Object> attributes;
}
