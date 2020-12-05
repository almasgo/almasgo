package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.service.ApiKeyGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiKeyGeneratorMiddlewareImpl implements ApiKeyGeneratorMiddleware{

    @Autowired
    private ApiKeyGeneratorService apiKeyGeneratorService;

    @Override
    public String generate(){
        return apiKeyGeneratorService.generate();
    }
}
