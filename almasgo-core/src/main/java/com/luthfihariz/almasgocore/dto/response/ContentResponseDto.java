package com.luthfihariz.almasgocore.dto.response;


import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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
}
