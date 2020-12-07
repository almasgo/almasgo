package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.model.Engine;
import com.luthfihariz.almasgocore.service.EngineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EngineMiddlewareImpl implements EngineMiddleware {
    @Autowired
    private EngineService engineService;

    @Override
    public Engine addEngine(Engine engine, String email) {
        return engineService.addEngine(engine, email);
    }

    @Override
    public void removeEngine(Long engineId) {
        engineService.removeEngine(engineId);
    }

    @Override
    public Engine getEngine(Long engineId) {
        return engineService.getEngine(engineId);
    }

    @Override
    public List<Engine> getPaginatedEngineByUserId(String email, Integer page, Integer size) {
        return engineService.getPaginatedEngineByUserId(email, page, size);
    }
}
