package com.luthfihariz.almasgocore.middleware.v1;

import com.luthfihariz.almasgocore.model.User;

public interface UserMiddleware {

    public User register(User user);

    public User changePassword(String oldPassword, String newPassword, String email);

    public void forgotPassword(String email);
}
