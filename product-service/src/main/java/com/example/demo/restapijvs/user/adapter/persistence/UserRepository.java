package com.example.demo.restapijvs.user.adapter.persistence;

import com.example.demo.restapijvs.user.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {
    UserEntity findByEmail(String email);
}
