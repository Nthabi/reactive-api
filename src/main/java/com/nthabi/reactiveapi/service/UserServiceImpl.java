package com.nthabi.reactiveapi.service;

import com.nthabi.reactiveapi.domain.UserRequest;
import com.nthabi.reactiveapi.dto.UserDTO;
import com.nthabi.reactiveapi.entities.Users;
import com.nthabi.reactiveapi.respository.UsersRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
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
        return usersRepository.findById(id).map(this::convertToDTO);
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
