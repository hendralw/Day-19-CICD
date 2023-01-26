package com.example.demo.restapijvs.user.adapter.controller;

import com.example.demo.restapijvs.user.model.UserRequestModel;
import com.example.demo.restapijvs.user.service.in.CreateUserUseCase;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class CreateUserController {
    private final CreateUserUseCase createUserUseCase;

    @PostMapping("/v2/users")
    public ResponseEntity<ResponseApi> createUser(@RequestBody UserRequestModel requestModel) {
        return createUserUseCase.createUser(requestModel);
    }
}
