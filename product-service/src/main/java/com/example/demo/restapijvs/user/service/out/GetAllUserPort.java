package com.example.demo.restapijvs.user.service.out;

import com.example.demo.restapijvs.utils.ResponseApi;
import org.springframework.http.ResponseEntity;

public interface GetAllUserPort {
    ResponseEntity<ResponseApi> getAllUser();
}
