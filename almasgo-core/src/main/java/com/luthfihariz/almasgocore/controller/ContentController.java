package com.luthfihariz.almasgocore.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.dto.mapper.ContentMapper;
import com.luthfihariz.almasgocore.controller.dto.mapper.SearchQueryMapper;
import com.luthfihariz.almasgocore.controller.dto.request.ContentRequestDto;
import com.luthfihariz.almasgocore.controller.dto.request.SearchRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.ContentBulkResponseDto;
import com.luthfihariz.almasgocore.controller.dto.response.ContentResponseDto;
import com.luthfihariz.almasgocore.controller.dto.response.SearchResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.security.AuthenticationFacade;
import com.luthfihariz.almasgocore.service.ContentService;
import com.luthfihariz.almasgocore.service.SearchService;
import org.elasticsearch.action.search.SearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/content")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SearchService searchService;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @PostMapping
    public ContentResponseDto addContent(@RequestBody ContentRequestDto contentRequest) {
        Content content = ContentMapper.fromRequestDto(objectMapper, contentRequest);
        Content savedContent = contentService.addContent(content, authenticationFacade.getSearchClientPrincipal().getEngineId());
        return ContentMapper.toResponseDto(objectMapper, savedContent);
    }

    @PostMapping("/bulk")
    public ContentBulkResponseDto addContents(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return contentService.addContents(multipartFile.getInputStream(), authenticationFacade.getSearchClientPrincipal().getEngineId());
    }

    @GetMapping
    public List<ContentResponseDto> getContents(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "20") Integer size) {
        List<Content> contents = contentService
                .getPaginatedContentByEngineId(authenticationFacade.getSearchClientPrincipal().getEngineId(), page, size);
        if (contents.isEmpty()) {
            return Collections.emptyList();
        }

        return contents.stream()
                .map(content -> ContentMapper.toResponseDto(objectMapper, content))
                .collect(Collectors.toList());
    }

    @GetMapping("/{contentId}")
    public ContentResponseDto getContent(@PathVariable("contentId") Long contentId) throws JsonProcessingException {
        return ContentMapper.toResponseDto(objectMapper, contentService.getContent(contentId));
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> removeContent(@PathVariable("contentId") Long contentId) {
        contentService.removeContent(contentId, authenticationFacade.getSearchClientPrincipal().getEngineId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{contentId}")
    public ContentResponseDto updateContent(@PathVariable("contentId") Long contentId,
                                            @RequestBody ContentRequestDto contentRequestDto) throws IOException {
        Content content = ContentMapper.fromRequestDto(objectMapper, contentRequestDto);
        content.setId(contentId);
        return ContentMapper.toResponseDto(objectMapper, contentService.updateContent(content,
                authenticationFacade.getSearchClientPrincipal().getEngineId()));
    }


    @PostMapping("/search")
    public SearchResponseDto search(@RequestBody SearchRequestDto searchRequestDto) throws IOException {
        SearchResponse searchResponse = searchService.search(SearchQueryMapper.fromSearchRequestDto(searchRequestDto),
                authenticationFacade.getSearchClientPrincipal().getEngineId());
        return SearchQueryMapper.toSearchResponseDto(searchResponse, searchRequestDto);
    }
}
