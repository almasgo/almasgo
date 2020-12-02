package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.model.Engine;

public interface ApiKeyValidatorService {

     Engine isValid(String apiKey, Long engineId);
}
