package com.example.demo.restapijvs.user.service.in;

import com.example.demo.restapijvs.user.model.UserRequestModel;
import com.example.demo.restapijvs.utils.ResponseApi;
import org.springframework.http.ResponseEntity;

public interface UpdateUserUseCase {
    ResponseEntity<ResponseApi> updateUser(UserRequestModel requestModel);
}
