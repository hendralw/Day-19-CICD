package com.example.demo.restapijvs.user.model;

import com.example.demo.restapijvs.user.adapter.persistence.UserRepository;
import com.example.demo.restapijvs.user.adapter.persistence.UserRepositoryJPA;
import com.example.demo.restapijvs.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//@Repository
@Service
@RequiredArgsConstructor
public class UserDao {
    @Autowired
    private final UserRepositoryJPA userRepositoryJPA;
    private final UserRepository userRepository;
    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User(
                    "hendra@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
            ),
            new User(
                    "user.mail@gmail.com",
                    "password",
                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
            )
    );

    public UserDetails findUserByEmail(String email) {
        return APPLICATION_USERS
                .stream()
                .filter(u -> u.getUsername().equals(email))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No user was found"));
    }

    public UserDetails findByEmail(String email) throws UsernameNotFoundException {
        UserEntity user = userRepositoryJPA.findByEmail(email);
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), new ArrayList<>());
    }

    public UserDetails findByPhone(String phone) throws UsernameNotFoundException {
        UserEntity user = userRepositoryJPA.findByPhone(phone);
        var asd = user;
        return new org.springframework.security.core.userdetails.User(user.getPhone(), user.getPassword(), new ArrayList<>());
    }
}
