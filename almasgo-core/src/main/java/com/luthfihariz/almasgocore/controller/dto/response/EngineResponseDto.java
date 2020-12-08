package com.luthfihariz.almasgocore.controller.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class EngineResponseDto {
    private Long id;
    private String name;
    private String type;
}
