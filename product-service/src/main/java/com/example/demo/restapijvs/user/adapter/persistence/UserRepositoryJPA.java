package com.example.demo.restapijvs.user.adapter.persistence;

import com.example.demo.restapijvs.user.entity.UserEntity;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface UserRepositoryJPA extends JpaRepository<UserEntity, Integer>{

    UserEntity findByPhone(String phone);
    UserEntity findByEmail(String email);
}
