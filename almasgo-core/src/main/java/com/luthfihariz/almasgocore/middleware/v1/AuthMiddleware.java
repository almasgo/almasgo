package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.controller.dto.response.AuthResponseDto;
import com.luthfihariz.almasgocore.model.User;

public interface AuthMiddleware {
    AuthResponseDto login(User user) throws Exception;
}
