package com.nthabi.reactiveapi.service;

import com.nthabi.reactiveapi.requestDTO.UserRequest;
import com.nthabi.reactiveapi.dto.UserDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserService {

    Mono<UserDTO> createUser(Mono<UserRequest> createUser);

    Mono<UserDTO> getUser(int id);

    Flux<UserDTO> findAll(int page, int limit);

}
