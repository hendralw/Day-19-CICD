package com.example.demo.restapijvs.user.service;

import com.example.demo.restapijvs.user.entity.UserEntity;
import com.example.demo.restapijvs.user.model.UserRequestModel;
import com.example.demo.restapijvs.user.service.in.CreateUserUseCase;
import com.example.demo.restapijvs.user.service.out.CreateUserPort;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateUserService implements CreateUserUseCase {
    private final CreateUserPort createUserPort;

    @Override
    public ResponseEntity<ResponseApi> createUser(UserRequestModel requestModel) {
        return createUserPort.createUser(requestModel);
    }
}
