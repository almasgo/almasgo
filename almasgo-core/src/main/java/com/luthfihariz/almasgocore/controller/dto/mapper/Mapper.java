package com.luthfihariz.almasgocore.controller.dto.mapper;

public interface Mapper<ResDto, Entity, ReqDto> {
    public ResDto toResponseDto(Entity entity);

    public Entity fromRequestDto(ReqDto reqDto);

}
