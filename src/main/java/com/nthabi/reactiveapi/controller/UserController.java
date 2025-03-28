package com.nthabi.reactiveapi.controller;

import com.nthabi.reactiveapi.domain.UserRequest;
import com.nthabi.reactiveapi.dto.UserDTO;
import com.nthabi.reactiveapi.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping("/create")
    public Mono<ResponseEntity<UserDTO>> createUser(@RequestBody @Valid Mono<UserRequest> user) {
        return userService.createUser(user)
                .mapNotNull(userDTO -> ResponseEntity.status(HttpStatus.CREATED).body(userDTO));
    }

    @GetMapping("/{id}")
    public Mono<UserDTO> getUser(@PathVariable("id") int id){
        return userService.getUser(id);
    }
}
