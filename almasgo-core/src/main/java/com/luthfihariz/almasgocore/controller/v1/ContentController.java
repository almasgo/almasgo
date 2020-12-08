package com.luthfihariz.almasgocore.controller.v1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.v1.dto.mapper.ContentMapper;
import com.luthfihariz.almasgocore.controller.v1.dto.mapper.SearchQueryMapper;
import com.luthfihariz.almasgocore.controller.v1.dto.request.ContentRequestDto;
import com.luthfihariz.almasgocore.controller.v1.dto.request.SearchRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.ContentBulkResponseDto;
import com.luthfihariz.almasgocore.controller.dto.response.ContentResponseDto;
import com.luthfihariz.almasgocore.controller.dto.response.SearchResponseDto;
import com.luthfihariz.almasgocore.middleware.v1.ContentMiddleware;
import com.luthfihariz.almasgocore.middleware.v1.SearchMiddleware;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.security.AuthenticationFacade;
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
@RequestMapping("/api/v1/content")
public class ContentController {

    @Autowired
    private ContentMiddleware contentMiddleware;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SearchMiddleware searchMiddleware;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    @PostMapping
    public ContentResponseDto addContent(@RequestBody ContentRequestDto contentRequest) {
        Content content = ContentMapper.fromRequestDto(objectMapper, contentRequest);
        Content savedContent = contentMiddleware.addContent(content, authenticationFacade.getSearchClientPrincipal().getEngineId());
        return ContentMapper.toResponseDto(objectMapper, savedContent);
    }

    @PostMapping("/bulk")
    public ContentBulkResponseDto addContents(@RequestParam("file") MultipartFile multipartFile) throws IOException {
        return contentMiddleware.addContents(multipartFile.getInputStream(), authenticationFacade.getSearchClientPrincipal().getEngineId());
    }

    @GetMapping
    public List<ContentResponseDto> getContents(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "size", defaultValue = "20") Integer size) {
        List<Content> contents = contentMiddleware
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
        return ContentMapper.toResponseDto(objectMapper, contentMiddleware.getContent(contentId));
    }

    @DeleteMapping("/{contentId}")
    public ResponseEntity<Void> removeContent(@PathVariable("contentId") Long contentId) {
        contentMiddleware.removeContent(contentId, authenticationFacade.getSearchClientPrincipal().getEngineId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{contentId}")
    public ContentResponseDto updateContent(@PathVariable("contentId") Long contentId,
                                            @RequestBody ContentRequestDto contentRequestDto) throws IOException {
        Content content = ContentMapper.fromRequestDto(objectMapper, contentRequestDto);
        content.setId(contentId);
        return ContentMapper.toResponseDto(objectMapper, contentMiddleware.updateContent(content,
                authenticationFacade.getSearchClientPrincipal().getEngineId()));
    }


    @PostMapping("/search")
    public SearchResponseDto search(@RequestBody SearchRequestDto searchRequestDto) throws IOException {
        SearchResponse searchResponse = searchMiddleware.search(SearchQueryMapper.fromSearchRequestDto(searchRequestDto),
                authenticationFacade.getSearchClientPrincipal().getEngineId());
        return SearchQueryMapper.toSearchResponseDto(searchResponse, searchRequestDto);
    }
}
