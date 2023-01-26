package com.example.demo.restapijvs.utils;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class Headers {
    public String GenerateCorrelationId() {
        String correlationId = UUID.randomUUID().toString();
        return correlationId;
    }

    public HttpHeaders SetCorrelationId(String correlationId) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Correlation-Id", correlationId);
        return headers;
    }
}
