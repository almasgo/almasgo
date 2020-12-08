package com.luthfihariz.almasgocore.controller.v1.dashboard;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luthfihariz.almasgocore.controller.v1.dto.request.ChangePasswordRequestDto;
import com.luthfihariz.almasgocore.controller.v1.dto.request.ForgotPasswordRequestDto;
import com.luthfihariz.almasgocore.controller.v1.dto.request.RegisterUserRequestDto;
import com.luthfihariz.almasgocore.controller.dto.response.RegisterUserResponseDto;
import com.luthfihariz.almasgocore.middleware.v1.ContentMiddleware;
import com.luthfihariz.almasgocore.middleware.v1.UserMiddleware;
import com.luthfihariz.almasgocore.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/dashboard/v1/user")
public class DashboardUserController {

    @Autowired
    private UserMiddleware userMiddleware;

    @Autowired
    private ContentMiddleware contentMiddleware;

    @Autowired
    private ObjectMapper objectMapper;

    @PostMapping
    public RegisterUserResponseDto register(@RequestBody RegisterUserRequestDto userRequest) {
        User user = new User(userRequest.getName(), userRequest.getEmail(), userRequest.getPassword());
        User newUser = userMiddleware.register(user);
        return new RegisterUserResponseDto(newUser.getId(), newUser.getName(), newUser.getEmail());
    }


    @PostMapping("password")
    public ResponseEntity<Void> changePassword(@RequestBody ChangePasswordRequestDto passwordRequest,
                                               Principal principal) {
        userMiddleware.changePassword(passwordRequest.getOldPassword(), passwordRequest.getNewPassword(), principal.getName());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping("forgot-password")
    public ResponseEntity<Void> forgotPassword(@RequestBody ForgotPasswordRequestDto forgotPasswordRequest) {
        userMiddleware.forgotPassword(forgotPasswordRequest.getEmail());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
