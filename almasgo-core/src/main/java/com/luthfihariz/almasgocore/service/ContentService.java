package com.luthfihariz.almasgocore.service;


import com.luthfihariz.almasgocore.model.Content;

import java.util.List;

public interface ContentService {
    public Content addContent(Content content, String email);

    void removeContent(Long contentId);

    Content updateContent(Content content);

    Content getContent(Long contentId);

    List<Content> getPaginatedContentByUserId(String email, Integer page, Integer size);
}
