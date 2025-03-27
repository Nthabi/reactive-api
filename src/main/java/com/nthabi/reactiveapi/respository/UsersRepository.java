package com.nthabi.reactiveapi.respository;

import com.nthabi.reactiveapi.entities.Users;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends ReactiveCrudRepository<Users, UUID> {
}
