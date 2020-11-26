package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.exception.*;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private EmailSenderService emailSenderService;

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
            throw new InvalidOldPasswordException();
        }

        loggedInUser.setPassword(passwordEncoder.encode(newPassword));
        return userRepository.save(loggedInUser);
    }

    public void forgotPassword(String email) {
        User loggedInUser = userRepository.findByEmail(email);
        if (loggedInUser == null) {
            throw new EmailNotFoundException();
        }

        String newPassword = this.generateNewPassword(8); // length new password
        loggedInUser.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(loggedInUser);

        this.sendForgotPasswordMail(loggedInUser, newPassword);
    }

    private String generateNewPassword(int lengthNewPassword){
        return RandomStringUtils.randomAlphanumeric(lengthNewPassword);
    }

    private void sendForgotPasswordMail(User user, String newPassword){
        String from = "noreply@almasgo.com";
        String to = user.getEmail();
        String subject = "Almasgo - New Password";

        Context context = new Context();
        context.setVariable("name", user.getName());
        context.setVariable("newPassword", newPassword);
        String content = templateEngine.process("email/forgot-password", context);

        emailSenderService.sendMail(from, to, subject, content);
    }

}
