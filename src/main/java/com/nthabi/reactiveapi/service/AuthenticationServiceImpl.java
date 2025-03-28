package com.nthabi.reactiveapi.service;

import com.nthabi.reactiveapi.entities.Users;
import com.nthabi.reactiveapi.respository.UsersRepository;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

    private final ReactiveAuthenticationManager authenticationManager;
    private final UsersRepository usersRepository;

    public AuthenticationServiceImpl(ReactiveAuthenticationManager authenticationManager, UsersRepository usersRepository) {
        this.authenticationManager = authenticationManager;
        this.usersRepository = usersRepository;
    }

    @Override
    public Mono<Map<String, String>> authenticate(String username, String password) {
        return authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username, password))
                .then(getUserDetails(username))
                .map(this::createAuthResponse);
    }

    private Mono<Users> getUserDetails(String username) {
        return usersRepository.findByEmail(username);
    }

    private Map<String, String> createAuthResponse(Users user) {
        Map<String, String> result = new HashMap<>();
        result.put("userId", String.valueOf(user.getId()));
        result.put("token", "JWT");
        return result;
    }
}
