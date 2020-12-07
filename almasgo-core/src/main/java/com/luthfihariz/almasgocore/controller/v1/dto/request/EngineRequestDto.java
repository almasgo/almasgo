package com.luthfihariz.almasgocore.controller.v1.dto.request;


import com.luthfihariz.almasgocore.model.enums.EngineType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EngineRequestDto {
    private String name;
    private EngineType type;
}
