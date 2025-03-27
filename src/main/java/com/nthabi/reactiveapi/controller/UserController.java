package com.nthabi.reactiveapi.controller;

import com.nthabi.reactiveapi.domain.UserRequest;
import com.nthabi.reactiveapi.dto.UserDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/create")
    public Mono<ResponseEntity<UserDTO>> createUser(@RequestBody @Valid Mono<UserRequest> user) {
        return user.map(userRequest ->
                new UserDTO("Nthabi",
                        "Mashiane",
                        "test@email.com",
                        "Nthabi"))
                .map(userDTO -> ResponseEntity.status(HttpStatus.CREATED).body(userDTO));
    }

    @GetMapping("/{name}")
    public Mono<UserDTO> getUser(@PathVariable("name") String name){
        return Mono.just(new UserDTO("Nthabi",
                "Mashiane",
                "test@email.com",
                "Nthabi"));
    }
}
