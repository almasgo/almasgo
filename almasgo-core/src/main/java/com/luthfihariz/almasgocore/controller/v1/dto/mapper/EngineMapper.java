package com.luthfihariz.almasgocore.controller.v1.dto.mapper;

import com.luthfihariz.almasgocore.controller.v1.dto.request.EngineRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.EngineResponseDto;
import com.luthfihariz.almasgocore.model.Engine;
import org.springframework.stereotype.Component;

@Component
public class EngineMapper implements Mapper<EngineResponseDto, Engine, EngineRequestDto> {

    @Override
    public EngineResponseDto toResponseDto(Engine engine) {
        return new EngineResponseDto(engine.getId(), engine.getName(),
                engine.getType().getCode(), engine.getApiKey());
    }

    @Override
    public Engine fromRequestDto(EngineRequestDto engineRequestDto) {
        return new Engine(engineRequestDto.getName(), engineRequestDto.getType());
    }
}
