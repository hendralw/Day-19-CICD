package com.example.demo.restapijvs.user.adapter.controller;

import com.example.demo.restapijvs.user.service.in.DeleteUserUseCase;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeleteUserController {
    private final DeleteUserUseCase deleteUserUseCase;

    @DeleteMapping("/v2/users/{userId}")
    ResponseEntity<ResponseApi> deleteUser(@PathVariable Integer userId) {
        return deleteUserUseCase.deleteUser(userId);
    }
}
