package com.example.demo.restapijvs.user.service;

import com.example.demo.restapijvs.user.model.UserRequestModel;
import com.example.demo.restapijvs.user.service.in.UpdateUserUseCase;
import com.example.demo.restapijvs.user.service.out.UpdateUserPort;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserService implements UpdateUserUseCase {
    private final UpdateUserPort updateUserPort;

    @Override
    public ResponseEntity<ResponseApi> updateUser(UserRequestModel requestModel) {
        return updateUserPort.updateUser(requestModel);
    }
}
