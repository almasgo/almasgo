package com.luthfihariz.almasgocore.dto.mapper;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.dto.request.ContentRequestDto;
import com.luthfihariz.almasgocore.dto.response.ContentResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;

import java.util.Arrays;
import java.util.List;

public class ContentMapper {

    public static ContentResponseDto toResponseDto(ObjectMapper objectMapper, Content content) {
        List<String> tags = null;
        try {
            tags = Arrays.asList(objectMapper.readValue(content.getTags(), String[].class));
        } catch (JsonProcessingException ignored) {
        }

        return new ContentResponseDto(content.getId(),
                content.getExternalUniqueId(), content.getTitle(),
                content.getDescription(), content.getPopularityInPercentage(),
                content.getVisibility(), tags);
    }

    public static Content fromRequestDto(ObjectMapper objectMapper, ContentRequestDto contentRequest) {
        String tags = null;
        if (contentRequest.getTags() != null) {
            try {
                tags = objectMapper.writeValueAsString(contentRequest.getTags());
            } catch (JsonProcessingException ignored) {
            }
        }

        return new Content(contentRequest.getExternalUniqueId(),
                contentRequest.getTitle(), contentRequest.getDescription(),
                contentRequest.getPopularityInPercentage(), contentRequest.getVisibility(),
                tags
        );
    }
}
