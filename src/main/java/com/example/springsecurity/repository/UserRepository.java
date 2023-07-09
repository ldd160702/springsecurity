package com.example.springsecurity.repository;

import com.example.springsecurity.model.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByUsername(String username);

    void deleteByUsername(String username);
}
