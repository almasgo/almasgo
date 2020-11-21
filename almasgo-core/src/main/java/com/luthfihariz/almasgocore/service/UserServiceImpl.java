package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.exception.InvalidPasswordException;
import com.luthfihariz.almasgocore.exception.UserAlreadyRegisteredException;
import com.luthfihariz.almasgocore.exception.UserNotFoundException;
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

    public User changePassword(String oldPassword, String newPassword, String email){
        User loggedInUser = userRepository.findByEmail(email);
        if (loggedInUser == null) {
            throw new UserNotFoundException();
        }

        if(!passwordEncoder.matches(oldPassword, loggedInUser.getPassword())){
            throw new InvalidPasswordException();
        }

        loggedInUser.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(loggedInUser);
    }
}
