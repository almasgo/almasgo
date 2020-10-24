package com.luthfihariz.almasgocore.service;

import com.luthfihariz.almasgocore.config.JwtTokenProvider;
import com.luthfihariz.almasgocore.dto.request.AuthRequestDto;
import com.luthfihariz.almasgocore.dto.response.AuthResponseDto;
import com.luthfihariz.almasgocore.model.User;
import com.luthfihariz.almasgocore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtToken;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(s);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(),
                new ArrayList<>());
    }

    @Override
    public AuthResponseDto login(User user) throws Exception {
        authenticate(user.getEmail(), user.getPassword());
        UserDetails userDetails = loadUserByUsername(user.getEmail());

        return new AuthResponseDto(jwtToken.generateToken(userDetails.getUsername()));
    }

    private void authenticate(String email, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
