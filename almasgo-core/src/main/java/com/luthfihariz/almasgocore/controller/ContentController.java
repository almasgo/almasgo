package com.luthfihariz.almasgocore.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.dto.mapper.ContentMapper;
import com.luthfihariz.almasgocore.dto.request.ContentRequestDto;
import com.luthfihariz.almasgocore.dto.response.ContentResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.service.ContentService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ObjectMapper objectMapper;


    @PostMapping
    public ContentResponseDto addContent(@RequestBody ContentRequestDto contentRequest, Principal principal) throws JsonProcessingException {

        Content content = ContentMapper.fromRequestDto(objectMapper, contentRequest);
        Content savedContent = contentService.addContent(content, principal.getName());
        return ContentMapper.toResponseDto(objectMapper, savedContent);
    }

    @GetMapping("/{id}")
    public ContentResponseDto getContent(@PathVariable("id") Long contentId) throws JsonProcessingException {
        return ContentMapper.toResponseDto(objectMapper, contentService.getContent(contentId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeContent(@PathVariable("id") Long contentId) {
        contentService.removeContent(contentId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ContentResponseDto updateContent(@PathVariable("id") Long contentId,
                                 @RequestBody ContentRequestDto contentRequestDto) throws JsonProcessingException {
        Content content = ContentMapper.fromRequestDto(objectMapper, contentRequestDto);
        content.setId(contentId);
        return ContentMapper.toResponseDto(objectMapper, contentService.updateContent(content));
    }
}
