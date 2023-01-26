package com.example.demo.restapijvs.helloworld.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;


@Data
@NoArgsConstructor
public class RequestModel {
    @Getter
    @Setter
    @NotNull(message = "msg must not be null")
    @NotBlank(message = "msg must not be blank")
    private String msg;

    public RequestModel(String msg) {
        this.msg = msg;
    }
}
