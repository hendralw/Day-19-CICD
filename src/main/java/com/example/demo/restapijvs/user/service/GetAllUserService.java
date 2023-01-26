package com.example.demo.restapijvs.user.service;

import com.example.demo.restapijvs.user.service.in.GetAllUserUseCase;
import com.example.demo.restapijvs.user.service.out.GetAllUserPort;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllUserService implements GetAllUserUseCase {
    private final GetAllUserPort getAllUserPort;

    @Override
    public ResponseEntity<ResponseApi> getAllUser() {
        return getAllUserPort.getAllUser();
    }
}
