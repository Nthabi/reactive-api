package com.nthabi.reactiveapi.service;

import com.nthabi.reactiveapi.domain.UserRequest;
import com.nthabi.reactiveapi.dto.UserDTO;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDTO> createUser(Mono<UserRequest> createUser);

    Mono<UserDTO> getUser(int id);

}
