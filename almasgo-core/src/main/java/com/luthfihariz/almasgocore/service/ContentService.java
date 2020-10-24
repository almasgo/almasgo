package com.luthfihariz.almasgocore.service;


import com.luthfihariz.almasgocore.model.Content;

public interface ContentService {
    public Content addContent(Content content, String email);

    void removeContent(Long contentId);

    Content updateContent(Content content);

    Content getContent(Long contentId);
}
