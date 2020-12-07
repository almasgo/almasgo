package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMiddlewareImpl implements UserMiddleware {

    @Autowired
    private UserService userService;

    @Override
    public User register(User user) {
        return userService.register(user);
    }

    @Override
    public User changePassword(String oldPassword, String newPassword, String email) {
        return userService.changePassword(oldPassword, newPassword, email);
    }

    @Override
    public void forgotPassword(String email) {
        userService.forgotPassword(email);
    }
}
