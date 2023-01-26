package com.example.demo.restapijvs.user.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AuthenticationResponse {
    String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }
}


