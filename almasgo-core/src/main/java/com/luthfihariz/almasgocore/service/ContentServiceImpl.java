package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.exception.ContentNotFoundException;
import com.luthfihariz.almasgocore.exception.UserNotFoundException;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.repository.ContentRepository;
import com.luthfihariz.almasgocore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContextException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Optional;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentRepository contentRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Content addContent(Content content, String email) {
        User loggedInUser = userRepository.findByEmail(email);

        if (loggedInUser == null) {
            throw new UserNotFoundException();
        }

        content.setUser(loggedInUser);
        return contentRepository.save(content);
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
}
