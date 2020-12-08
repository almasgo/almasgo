package com.luthfihariz.almasgocore.controller.v1.dto.mapper;

public interface Mapper<ResDto, Entity, ReqDto> {
    public ResDto toResponseDto(Entity entity);

    public Entity fromRequestDto(ReqDto reqDto);

}
