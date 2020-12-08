package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.controller.dto.response.ContentBulkResponseDto;
import com.luthfihariz.almasgocore.model.Content;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface ContentMiddleware {
    public Content addContent(Content content, Long engineId);

    ContentBulkResponseDto addContents(InputStream inputStream, Long engineId) throws IOException;

    void removeContent(Long contentId, Long engineId);

    Content updateContent(Content content, Long engineId) throws IOException;

    Content getContent(Long contentId);

    List<Content> getPaginatedContentByEngineId(Long engineId, Integer page, Integer size);
}
