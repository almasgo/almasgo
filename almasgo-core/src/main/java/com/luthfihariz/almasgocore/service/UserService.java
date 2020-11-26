package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.model.User;

public interface UserService {

    public User register(User user);

    public User changePassword(String oldPassword, String newPassword, String email);

    public void forgotPassword(String email);
}
