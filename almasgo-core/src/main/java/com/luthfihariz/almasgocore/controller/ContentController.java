package com.luthfihariz.almasgocore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.dto.mapper.ContentMapper;
import com.luthfihariz.almasgocore.controller.dto.request.ContentRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.ContentResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ObjectMapper objectMapper;

    @GetMapping("/{id}")
    public ContentResponseDto getContent(@PathVariable("id") Long contentId) throws JsonProcessingException {
        return ContentMapper.toResponseDto(objectMapper, contentService.getContent(contentId));
    }

}
