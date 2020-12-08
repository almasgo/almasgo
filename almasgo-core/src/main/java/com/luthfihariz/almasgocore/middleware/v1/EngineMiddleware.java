package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.model.Engine;

import java.util.List;

public interface EngineMiddleware {
    Engine addEngine(Engine engine, String email);

    void removeEngine(Long engineId);

    Engine getEngine(Long engineId);

    List<Engine> getPaginatedEngineByUserId(String email, Integer page, Integer size);
}
