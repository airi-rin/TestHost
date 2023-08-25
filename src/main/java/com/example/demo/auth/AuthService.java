package com.example.demo.auth;

import com.example.demo.request.person.LoginRequest;
import com.example.demo.response.person.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    public LoginResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken((UserDetailsPrincipal) authentication.getPrincipal());
        return new LoginResponse(jwt);
    }
    public UserDetailsPrincipal getPrincipal() {
        return (UserDetailsPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public UserEntity getUser() {
        return getPrincipal().getUser();
    }
}
