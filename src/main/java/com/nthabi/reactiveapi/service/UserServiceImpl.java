package com.nthabi.reactiveapi.service;

import com.nthabi.reactiveapi.requestDTO.UserRequest;
import com.nthabi.reactiveapi.dto.UserDTO;
import com.nthabi.reactiveapi.entities.Users;
import com.nthabi.reactiveapi.respository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {


    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Mono<UserDTO> createUser(Mono<UserRequest> createUser) {
        return createUser
                .flatMap(this::convertToEntity)
                .flatMap(usersRepository::save)
                .mapNotNull(this::convertToDTO);
    }

    @Override
    public Mono<UserDTO> getUser(int id) {
        return usersRepository.findById(id).mapNotNull(this::convertToDTO);
    }

    @Override
    public Flux<UserDTO> findAll(int page, int limit) {
        if (page > 0) page = page - 1;
        Pageable pageable = PageRequest.of(page, limit);
        return usersRepository.findAllBy(pageable)
                .map(user -> convertToDTO(user));
    }

    private Mono<Users> convertToEntity (UserRequest userRequest) {
        return Mono.fromCallable(() -> {
            Users user = new Users();
            BeanUtils.copyProperties(userRequest, user);
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            return user;
        }).subscribeOn(Schedulers.boundedElastic());
    }

    private UserDTO convertToDTO(Users user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return usersRepository.findByEmail(username)
                .map(user -> User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .authorities(new ArrayList<>())
                        .build());
    }
}
