package com.example.demo.restapijvs.user.service.out;

import com.example.demo.restapijvs.utils.ResponseApi;
import org.springframework.http.ResponseEntity;

public interface DeleteUserPort {
    ResponseEntity<ResponseApi> deleteUser(Integer userId);
}
