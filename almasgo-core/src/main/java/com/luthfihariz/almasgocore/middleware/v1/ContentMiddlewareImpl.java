package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.controller.dto.response.ContentBulkResponseDto;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class ContentMiddlewareImpl implements ContentMiddleware{

    @Autowired
    private ContentService contentService;

    @Override
    public Content addContent(Content content, Long engineId){
        return  contentService.addContent(content, engineId);
    }

    @Override
    public ContentBulkResponseDto addContents(InputStream inputStream, Long engineId) throws IOException {
        return contentService.addContents(inputStream, engineId);
    }

    @Override
    public void removeContent(Long contentId, Long engineId) {
        contentService.removeContent(contentId, engineId);
    }

    @Override
    public Content updateContent(Content content, Long engineId) throws IOException {
        return contentService.updateContent(content, engineId);
    }

    @Override
    public Content getContent(Long contentId) {
        return contentService.getContent(contentId);
    }

    @Override
    public List<Content> getPaginatedContentByEngineId(Long engineId, Integer page, Integer size) {
        return contentService.getPaginatedContentByEngineId(engineId, page, size);
    }
}
