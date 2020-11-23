package com.luthfihariz.almasgocore.service;


import com.luthfihariz.almasgocore.model.Content;

import java.io.IOException;
import java.util.List;

public interface ContentService {
    public Content addContent(Content content, Long engineId);

    void removeContent(Long contentId, Long engineId);

    Content updateContent(Content content, Long engineId) throws IOException;

    Content getContent(Long contentId);

    List<Content> getPaginatedContentByEngineId(Long engineId, Integer page, Integer size);
}
