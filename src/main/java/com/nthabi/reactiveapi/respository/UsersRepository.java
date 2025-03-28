package com.nthabi.reactiveapi.respository;

import com.nthabi.reactiveapi.entities.Users;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;



@Repository
public interface UsersRepository extends ReactiveCrudRepository<Users, Integer> {
    Flux<Users> findAllBy(Pageable pageable);
}
