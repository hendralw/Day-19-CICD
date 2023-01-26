package com.example.demo.restapijvs.user.service.out;

import com.example.demo.restapijvs.user.model.UserRequestModel;
import com.example.demo.restapijvs.utils.ResponseApi;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;

public interface CreateUserPort {
    ResponseEntity<ResponseApi> createUser(UserRequestModel requestModel);
}
