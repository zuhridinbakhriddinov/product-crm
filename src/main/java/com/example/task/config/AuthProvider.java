package com.example.task.config;


import com.example.task.entity.User;
import com.example.task.enums.UserStatus;
import com.example.task.exception.GeneralApiException;
import com.example.task.repository.UserRepository;
import com.example.task.service.AuthService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AuthProvider implements AuthenticationProvider {

    private final UserRepository userRepository;
    @Lazy
    private final AuthService authService;

    public AuthProvider(UserRepository userRepository, @Lazy AuthService authService) {
        this.userRepository = userRepository;
        this.authService = authService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        final var user = userRepository.findByLoginAndUserStatus(username, UserStatus.ACTIVE).orElseThrow(() ->
                new GeneralApiException("LOGIN OR PASSWORD ERROR"));

        if (Objects.equals(password, user.getPassword())) {
            return new UsernamePasswordAuthenticationToken(user, password, authService.getAuthorities(user.getId()));
        }

        throw new BadCredentialsException("LOGIN OR PASSWORD ERROR");
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof User) {
                return (User) principal;
            }
        }
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.getName().equals(UsernamePasswordAuthenticationToken.class.getName());
    }
}
