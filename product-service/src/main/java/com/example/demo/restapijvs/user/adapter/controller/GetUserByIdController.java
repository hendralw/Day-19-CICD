package com.example.demo.restapijvs.user.adapter.controller;

import com.example.demo.restapijvs.user.service.in.CreateUserUseCase;
import com.example.demo.restapijvs.user.service.in.GetAllUserUseCase;
import com.example.demo.restapijvs.user.service.in.GetUserByIdUseCase;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class GetUserByIdController {
    private final GetUserByIdUseCase getUserByIdUseCase;

    @GetMapping("/v2/users/{userId}")
    public ResponseEntity<ResponseApi> getUserById(@PathVariable Integer userId) {
        return getUserByIdUseCase.getUserById(userId);
    }
}
