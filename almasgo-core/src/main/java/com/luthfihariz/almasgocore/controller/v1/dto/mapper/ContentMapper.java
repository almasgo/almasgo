package com.luthfihariz.almasgocore.controller.v1.dto.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.v1.dto.request.ContentRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.ContentResponseDto;
import com.luthfihariz.almasgocore.model.Content;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ContentMapper {

    public static ContentResponseDto toResponseDto(ObjectMapper objectMapper, Content content) {
        List<String> tags = null;
        Map<String, Object> attributes = null;
        try {
            tags = Arrays.asList(objectMapper.readValue(content.getTags(), String[].class));
            attributes = objectMapper.readValue(content.getAttributes(), Map.class);
        } catch (JsonProcessingException ignored) {
        }

        return new ContentResponseDto(content.getId(),
                content.getExternalUniqueId(), content.getTitle(),
                content.getDescription(),
                content.getVisibility(), tags, attributes);
    }

    public static Content fromRequestDto(ObjectMapper objectMapper, ContentRequestDto contentRequest) {
        String tags = null;
        String attributes = null;
        if (contentRequest.getTags() != null) {
            try {
                tags = objectMapper.writeValueAsString(contentRequest.getTags());
            } catch (JsonProcessingException ignored) {
            }
        }

        if (contentRequest.getAttributes() != null) {
            try {
                attributes = objectMapper.writeValueAsString(contentRequest.getAttributes());
            } catch (JsonProcessingException ignored) {
            }
        }

        return new Content(contentRequest.getExternalUniqueId(),
                contentRequest.getTitle(), contentRequest.getDescription(),
                contentRequest.getVisibility(),
                tags, attributes
        );
    }
}
