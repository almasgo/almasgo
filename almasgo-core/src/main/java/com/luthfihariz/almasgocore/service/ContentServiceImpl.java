package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.exception.AddContentFailException;
import com.luthfihariz.almasgocore.exception.ContentNotFoundException;
import com.luthfihariz.almasgocore.exception.UserNotFoundException;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.repository.ContentRepository;
import com.luthfihariz.almasgocore.repository.SearchableContentRepository;
import com.luthfihariz.almasgocore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SearchableContentRepository searchableContentRepository;

    @Override
    public Content addContent(Content content, String email) {
        User loggedInUser = userRepository.findByEmail(email);

        if (loggedInUser == null) {
            throw new UserNotFoundException();
        }

        try {
            content.setUser(loggedInUser);
            Content savedContent = contentRepository.save(content); // save to db
            searchableContentRepository.save(savedContent, loggedInUser.getId()); // save to elastic
            return savedContent;
        } catch (IOException e) {
            throw new AddContentFailException();
        }
    }

    @Override
    public void removeContent(Long contentId) {
        try {
            Content content = contentRepository.getOne(contentId);
            contentRepository.delete(content);
        } catch (EntityNotFoundException ex) {
            throw new ContentNotFoundException();
        }
    }

    @Override
    public Content updateContent(Content newContent) {
        try {
            Content content = contentRepository.getOne(newContent.getId());

            if (newContent.getDescription() != null) {
                content.setDescription(newContent.getDescription());
            }

            if (newContent.getTags() != null) {
                content.setTags(newContent.getTags());
            }

            if (newContent.getTitle() != null) {
                content.setTitle(newContent.getTitle());
            }

            if (newContent.getPopularityInPercentage() != null) {
                content.setPopularityInPercentage(newContent.getPopularityInPercentage());
            }

            if (newContent.getVisibility() != null) {
                content.setVisibility(newContent.getVisibility());
            }

            return contentRepository.save(content);
        } catch (EntityNotFoundException ex) {
            throw new ContentNotFoundException();
        }
    }

    @Override
    public Content getContent(Long contentId) {
        Optional<Content> content = contentRepository.findById(contentId);
        if (content.isEmpty()) {
            throw new ContentNotFoundException();
        }

        return content.get();
    }

    @Override
    public List<Content> getPaginatedContentByUserId(String email, Integer page, Integer size) {
        User loggedInUser = userRepository.findByEmail(email);
        if (loggedInUser == null) {
            throw new UserNotFoundException();
        }

        if (page < 0) {
            page = 0;
        }

        if (size > 20) {
            size = 20;
        }

        Pageable pagination = PageRequest.of(page, size);
        return contentRepository.findAllByUserId(loggedInUser.getId(), pagination);
    }
}
