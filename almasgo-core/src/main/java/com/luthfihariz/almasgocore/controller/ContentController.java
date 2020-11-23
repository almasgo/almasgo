package com.luthfihariz.almasgocore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.dto.mapper.ContentMapper;
import com.luthfihariz.almasgocore.controller.dto.mapper.SearchQueryMapper;
import com.luthfihariz.almasgocore.controller.dto.request.ContentRequestDto;
import com.luthfihariz.almasgocore.controller.dto.request.SearchRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.ContentResponseDto;
import com.luthfihariz.almasgocore.controller.dto.response.SearchResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.service.ContentService;
import com.luthfihariz.almasgocore.service.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/engine")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SearchService searchService;

    @PostMapping("/{id}/content")
    public ContentResponseDto addContent(@RequestBody ContentRequestDto contentRequest,
                                         @PathVariable("id") Long engineId) {
        Content content = ContentMapper.fromRequestDto(objectMapper, contentRequest);
        Content savedContent = contentService.addContent(content, engineId);
        return ContentMapper.toResponseDto(objectMapper, savedContent);
    }

    @GetMapping("/{id}/content")
    public List<ContentResponseDto> getContents(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "20") Integer size,
                                                @PathVariable("id") Long engineId) {
        List<Content> contents = contentService.getPaginatedContentByEngineId(engineId, page, size);
        if (contents.isEmpty()) {
            return Collections.emptyList();
        }

        return contents.stream()
                .map(content -> ContentMapper.toResponseDto(objectMapper, content))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}/content/{contentId}")
    public ContentResponseDto getContent(@PathVariable("contentId") Long contentId) throws JsonProcessingException {
        return ContentMapper.toResponseDto(objectMapper, contentService.getContent(contentId));
    }

    @DeleteMapping("/{id}/content/{contentId}")
    public ResponseEntity<Void> removeContent(@PathVariable("id") Long engineId,
                                              @PathVariable("contentId") Long contentId) {
        contentService.removeContent(contentId, engineId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}/content/{contentId}")
    public ContentResponseDto updateContent(@PathVariable("id") Long engineId,
                                            @PathVariable("contentId") Long contentId,
                                            @RequestBody ContentRequestDto contentRequestDto) throws IOException {
        Content content = ContentMapper.fromRequestDto(objectMapper, contentRequestDto);
        content.setId(contentId);
        return ContentMapper.toResponseDto(objectMapper, contentService.updateContent(content, engineId));
    }


    @PostMapping("/{id}/search")
    public SearchResponseDto search(@RequestBody SearchRequestDto searchRequestDto, @PathVariable("id") Long engineId) throws IOException {
        SearchResponse searchResponse = searchService.search(SearchQueryMapper.fromSearchRequestDto(searchRequestDto), engineId);
        return SearchQueryMapper.toSearchResponseDto(searchResponse);
    }
}
