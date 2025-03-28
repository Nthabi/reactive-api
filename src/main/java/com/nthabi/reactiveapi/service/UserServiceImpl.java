package com.nthabi.reactiveapi.service;

import com.nthabi.reactiveapi.requestDTO.UserRequest;
import com.nthabi.reactiveapi.dto.UserDTO;
import com.nthabi.reactiveapi.entities.Users;
import com.nthabi.reactiveapi.respository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserServiceImpl implements UserService {


    private final UsersRepository usersRepository;

    public UserServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public Mono<UserDTO> createUser(Mono<UserRequest> createUser) {
        return createUser
                .mapNotNull(this::convertToEntity)
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

    private Users convertToEntity (UserRequest userRequest) {
        Users user = new Users();
        BeanUtils.copyProperties(userRequest, user);
        return user;
    }

    private UserDTO convertToDTO(Users user) {
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
