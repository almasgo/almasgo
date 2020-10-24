package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.exception.UserAlreadyRegisteredException;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new UserAlreadyRegisteredException();
        }

        User encryptedUser = new User(user.getName(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
        return userRepository.save(encryptedUser);
    }
}
