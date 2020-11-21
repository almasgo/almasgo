package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.exception.AddContentFailException;
import com.luthfihariz.almasgocore.exception.ContentNotFoundException;
import com.luthfihariz.almasgocore.exception.EngineNotFoundException;
import com.luthfihariz.almasgocore.exception.UserNotFoundException;
import com.luthfihariz.almasgocore.model.Content;
import com.luthfihariz.almasgocore.model.Engine;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.repository.ContentRepository;
import com.luthfihariz.almasgocore.repository.EngineRepository;
import com.luthfihariz.almasgocore.repository.SearchableContentRepository;
import com.luthfihariz.almasgocore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
    private EngineRepository engineRepository;

    @Autowired
    private SearchableContentRepository searchableContentRepository;

    @Override
    public Content addContent(Content content, Long engineId) {

        Engine engine = engineRepository.getOne(engineId);

        try {
            content.setEngine(engine);
            Content savedContent = contentRepository.save(content); // save to db
            searchableContentRepository.save(savedContent, engine.getId()); // save to elastic
            return savedContent;
        } catch (IOException e) {
            throw new AddContentFailException();
        } catch (EntityNotFoundException ex) {
            throw new EngineNotFoundException();
        }
    }

    @Override
    public void removeContent(Long contentId, Long engineId) {

        try {
            Content content = contentRepository.getOne(contentId);
            contentRepository.delete(content);
            searchableContentRepository.delete(content.getId(), engineId);
        } catch (EntityNotFoundException | IOException ex) {
            throw new ContentNotFoundException();
        }
    }

    @Override
    public Content updateContent(Content newContent, Long engineId) throws IOException {
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

            if (newContent.getAttributes() != null) {
                content.setAttributes(newContent.getAttributes());
            }

            Content updatedContent = contentRepository.save(content);

            if (updatedContent.getVisibility() == 0) {
                searchableContentRepository.delete(updatedContent.getId(), engineId);
            } else {
                searchableContentRepository.update(updatedContent, engineId);
            }

            return updatedContent;
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
    public List<Content> getPaginatedContentByEngineId(Long engineId, Integer page, Integer size) {

        if (page < 0) {
            page = 0;
        }

        if (size > 20) {
            size = 20;
        }

        Pageable pagination = PageRequest.of(page, size);
        return contentRepository.findAllByEngineId(engineId, pagination);
    }

    private User getUserFromEmail(String email) throws UserNotFoundException {
        User loggedInUser = userRepository.findByEmail(email);

        if (loggedInUser == null) {
            throw new UserNotFoundException();
        }

        return loggedInUser;
    }
}
