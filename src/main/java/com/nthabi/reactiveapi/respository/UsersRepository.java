package com.nthabi.reactiveapi.respository;

import com.nthabi.reactiveapi.dto.UserDTO;
import com.nthabi.reactiveapi.entities.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Repository
public interface UsersRepository extends ReactiveCrudRepository<Users, Integer> {
}
