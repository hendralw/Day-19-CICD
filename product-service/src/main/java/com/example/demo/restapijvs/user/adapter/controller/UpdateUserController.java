package com.example.demo.restapijvs.user.adapter.controller;

import com.example.demo.restapijvs.user.model.UserRequestModel;
import com.example.demo.restapijvs.user.service.in.UpdateUserUseCase;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class UpdateUserController {
    private final UpdateUserUseCase updateUserUseCase;

    @PutMapping("/v2/users")
    ResponseEntity<ResponseApi> updateUser(@RequestBody UserRequestModel requestModel) {
        return updateUserUseCase.updateUser(requestModel);
    }
}
