package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.model.Engine;
import com.luthfihariz.almasgocore.repository.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ApiKeyValidatorServiceImpl implements ApiKeyValidatorService {

    @Autowired
    EngineRepository engineRepository;

    @Override
    public Engine isValid(String apiKey, Long engineId) {
        try {
            return engineRepository.findByIdAndApiKey(engineId, apiKey);
        } catch (EntityNotFoundException ex) {
            return null;
        }
    }
}
