package com.example.demo.restapijvs.user.service.in;

import com.example.demo.restapijvs.utils.ResponseApi;
import org.springframework.http.ResponseEntity;

public interface GetAllUserUseCase {
    ResponseEntity<ResponseApi> getAllUser();
}
