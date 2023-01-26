package com.example.demo.restapijvs.user.adapter.controller;

import com.example.demo.restapijvs.user.service.in.GetAllUserUseCase;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class GetAllUserController {
    private final GetAllUserUseCase getAllUserUseCase;

    @GetMapping("/v2/users")
    public ResponseEntity<ResponseApi> getAllUser() {
        return getAllUserUseCase.getAllUser();
    }
}
