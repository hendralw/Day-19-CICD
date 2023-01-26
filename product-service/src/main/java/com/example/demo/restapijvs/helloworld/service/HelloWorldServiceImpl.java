package com.example.demo.restapijvs.helloworld.service;

import com.example.demo.restapijvs.helloworld.model.RequestModel;
import com.example.demo.restapijvs.helloworld.model.ResponseModel;
import com.example.demo.restapijvs.utils.ResponseApi;
import com.example.demo.restapijvs.utils.ValidationApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HelloWorldServiceImpl implements HelloWorldService {
    ResponseApi resp;
    ResponseModel data;

    @Override
    public ResponseApi getHelloWorld() {
        data = new ResponseModel("Hello World");
        resp = new ResponseApi(
                HttpStatus.OK.value(),
                "Success get Data",
                HttpStatus.OK.getReasonPhrase(),
                data
        );
        return resp;
    }

    @Override
    public ResponseApi createHelloWorld(RequestModel request) {
        var validation = new ValidationApi(request).Validate();
        if (!validation.equals("")) {
            resp = new ResponseApi(
                    HttpStatus.BAD_REQUEST.value(),
                    validation,
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    null
            );
            return resp;
        }

        data = new ResponseModel(request.getMsg());
        resp = new ResponseApi(
                HttpStatus.OK.value(),
                "Success create Data",
                HttpStatus.OK.getReasonPhrase(),
                data
        );
        return resp;
    }

    @Override
    public ResponseApi deleteHelloWorld() {
        data = new ResponseModel("Hello World");
        resp = new ResponseApi(
                HttpStatus.OK.value(),
                "Success delete Data",
                HttpStatus.OK.getReasonPhrase(),
                data
        );
        return resp;
    }
}
