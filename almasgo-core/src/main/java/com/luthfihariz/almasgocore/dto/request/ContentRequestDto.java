package com.luthfihariz.almasgocore.dto.request;


import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentRequestDto {
    private String externalUniqueId;
    private String title;
    private String description;
    private Integer popularityInPercentage;
    private Integer visibility;
    private List<String> tags;
}
