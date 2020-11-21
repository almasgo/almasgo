package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.model.Engine;

import java.util.List;

public interface EngineService {
    Engine addEngine(Engine engine, String email);

    void removeEngine(Long engineId);

    void getEngine(Long engineId);

    List<Engine> getPaginatedEngineByUserId(String email, Integer page, Integer size);
}
