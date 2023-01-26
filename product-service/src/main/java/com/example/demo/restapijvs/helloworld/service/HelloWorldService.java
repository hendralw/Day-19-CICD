package com.example.demo.restapijvs.helloworld.service;

import com.example.demo.restapijvs.helloworld.model.RequestModel;
import com.example.demo.restapijvs.utils.ResponseApi;

public interface HelloWorldService {
    ResponseApi getHelloWorld();

    ResponseApi createHelloWorld(RequestModel request);

    ResponseApi deleteHelloWorld();
}
