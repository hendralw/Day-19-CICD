package com.example.demo.restapijvs.helloworld.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class ResponseModel {
    @Getter
    @Setter
    private String msg;

    public ResponseModel(String msg) {
        this.msg = msg;
    }
}
