package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.exception.EngineNotFoundException;
import com.luthfihariz.almasgocore.exception.UserNotFoundException;
import com.luthfihariz.almasgocore.model.Engine;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.repository.EngineRepository;
import com.luthfihariz.almasgocore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class EngineServiceImpl implements EngineService {

    @Autowired
    EngineRepository engineRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ApiKeyGeneratorService apiKeyGeneratorService;

    @Override
    public Engine addEngine(Engine engine, String email) {
        User loggedInUser = getUserFromEmail(email);
        engine.setUser(loggedInUser);

        String apiKey = apiKeyGeneratorService.generate();
        engine.setApiKey(apiKey);

        return engineRepository.save(engine);
    }

    @Override
    public void removeEngine(Long engineId) {
        engineRepository.deleteById(engineId);
    }

    @Override
    public Engine getEngine(Long engineId) {
        try {
            return engineRepository.getOne(engineId);
        } catch (EntityNotFoundException ex) {
            throw new EngineNotFoundException();
        }
    }

    @Override
    public List<Engine> getPaginatedEngineByUserId(String email, Integer page, Integer size) {
        User loggedInUser = getUserFromEmail(email);

        if (page < 0) {
            page = 0;
        }

        if (size > 20) {
            size = 20;
        }

        Pageable pageable = PageRequest.of(page, size);
        return engineRepository.findAllByUserId(loggedInUser.getId(), pageable);
    }

    private User getUserFromEmail(String email) throws UserNotFoundException {
        User loggedInUser = userRepository.findByEmail(email);

        if (loggedInUser == null) {
            throw new UserNotFoundException();
        }

        return loggedInUser;
    }
}
