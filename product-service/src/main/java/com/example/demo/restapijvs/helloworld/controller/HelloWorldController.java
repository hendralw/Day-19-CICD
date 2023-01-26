package com.example.demo.restapijvs.helloworld.controller;

import com.example.demo.restapijvs.helloworld.service.HelloWorldService;
import com.example.demo.restapijvs.helloworld.model.RequestModel;
import com.example.demo.restapijvs.utils.Headers;
import com.example.demo.restapijvs.utils.Json;
import com.example.demo.restapijvs.utils.ResponseApi;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@Slf4j
public class HelloWorldController {
    @Autowired(required = false)
    HelloWorldService helloWorldService;
    Headers headersUtils = new Headers();
    Json jsonUtils = new Json();

    @GetMapping("/v1/messages")
    public ResponseEntity<ResponseApi> getMessage() {
        var correlationId = headersUtils.GenerateCorrelationId();
        var headers = headersUtils.SetCorrelationId(correlationId);
        log.info(correlationId + " --- [LOG] GET/v1/messages is called");
        try {
            log.info(correlationId + " --- [LOG] HelloWorldServiceImpl/getHelloWorld is called");
            var result = helloWorldService.getHelloWorld();
            var resultJson = jsonUtils.toJson(result);
            log.info(correlationId + " --- [LOG] result : " + resultJson);
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(correlationId + " --- [LOG] Error exception : " + ex.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
        }
    }

    @PostMapping("/v1/messages")
    public ResponseEntity<ResponseApi> createMessage(@RequestBody RequestModel request) throws JsonProcessingException {
        var correlationId = headersUtils.GenerateCorrelationId();
        var headers = headersUtils.SetCorrelationId(correlationId);
        var requestJson = jsonUtils.toJson(request);
        log.info(correlationId + " --- [LOG] POST/v1/messages is called");
        log.info(correlationId + " --- [LOG] RequestBody : " + requestJson);
        try {
            log.info(correlationId + " --- [LOG] HelloWorldServiceImpl/createHelloWorld is called");
            var result = helloWorldService.createHelloWorld(request);
            var resultJson = jsonUtils.toJson(result);
            log.info(correlationId + " --- [LOG] result : " + resultJson);
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(correlationId + " --- [LOG] Error exception : " + ex.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
        }
    }

    @DeleteMapping("/v1/messages/{messagesId}")
    public ResponseEntity<ResponseApi> deleteMessage(@PathVariable Integer messagesId) {
        var correlationId = headersUtils.GenerateCorrelationId();
        var headers = headersUtils.SetCorrelationId(correlationId);
        log.info(correlationId + " --- [LOG] DELETE/v1/messages is called");
        try {
            log.info(correlationId + " --- [LOG] HelloWorldServiceImpl/deleteHelloWorld is called");
            var result = helloWorldService.deleteHelloWorld();
            var resultJson = jsonUtils.toJson(result);
            log.info(correlationId + " --- [LOG] result : " + resultJson);
            return new ResponseEntity<>(result, headers, HttpStatus.OK);
        } catch (Exception ex) {
            log.error(correlationId + " --- [LOG] Error exception : " + ex.getMessage());
            return new ResponseEntity<>(headers, HttpStatus.CONFLICT);
        }
    }
}
