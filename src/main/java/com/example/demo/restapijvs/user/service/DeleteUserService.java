package com.example.demo.restapijvs.user.service;

import com.example.demo.restapijvs.user.service.in.DeleteUserUseCase;
import com.example.demo.restapijvs.user.service.out.DeleteUserPort;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@RequiredArgsConstructor
public class DeleteUserService implements DeleteUserUseCase {
    private final DeleteUserPort deleteUserPort;

    @Override
    public ResponseEntity<ResponseApi> deleteUser(Integer userId) {
        return deleteUserPort.deleteUser(userId);
    }
}
