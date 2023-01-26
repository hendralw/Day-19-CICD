package com.example.demo.restapijvs.user.service;

import com.example.demo.restapijvs.user.service.in.GetUserByIdUseCase;
import com.example.demo.restapijvs.user.service.out.GetUserByIdPort;
import com.example.demo.restapijvs.utils.ResponseApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdService implements GetUserByIdUseCase {
    private final GetUserByIdPort getUserByIdPort;

    @Override
    public ResponseEntity<ResponseApi> getUserById(Integer userId) {
        return getUserByIdPort.getUserById(userId);
    }
}
