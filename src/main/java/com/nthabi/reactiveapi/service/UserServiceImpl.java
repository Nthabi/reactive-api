package com.nthabi.reactiveapi.service;

import com.nthabi.reactiveapi.domain.UserRequest;
import com.nthabi.reactiveapi.dto.UserDTO;
import com.nthabi.reactiveapi.respository.UsersRepository;
import reactor.core.publisher.Mono;

public class UserServiceImpl implements UserService {


    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Mono<UserDTO> createUser(Mono<UserRequest> createUser) {
        return null;
    }
}
