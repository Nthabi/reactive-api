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

//        return user.map(userRequest ->
//                new UserDTO("Nthabi",
//                        "Mashiane",
//                        "test@email.com",
//                        "Nthabi"))
//                .map(userDTO -> ResponseEntity.status(HttpStatus.CREATED).body(userDTO));
    }

    @GetMapping("/{name}")
    public Mono<UserDTO> getUser(@PathVariable("name") String name){
        return Mono.just(new UserDTO("Nthabi",
                "Mashiane",
                "test@email.com",
                "Nthabi"));
    }
}
