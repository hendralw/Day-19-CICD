package com.example.demo.restapijvs.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@NoArgsConstructor
@Service
public class ResponseApi<T> {
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;
    @Getter
    @Setter
    private String status;
    @Getter
    @Setter
    private T data;

    public ResponseApi(int code, String message, String status, T data) {
        this.code = code;
        this.message = message;
        this.status = status;
        this.data = data;
    }

    public ResponseApi DataNotFound() {
        var resp = new ResponseApi<>(
                HttpStatus.FORBIDDEN.value(),
                "Data not found",
                HttpStatus.FORBIDDEN.getReasonPhrase(),
                null
        );
        return resp;
    }

    public ResponseApi HttpStatusOK(String message, T data) {
        var resp = new ResponseApi<>(
                HttpStatus.OK.value(),
                message,
                HttpStatus.OK.getReasonPhrase(),
                data
        );
        return resp;
    }

    public ResponseApi InternalServerError(Exception ex) {
        var resp = new ResponseApi<>(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.toString(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
                null
        );
        return resp;
    }

    public ResponseApi BadRequest(String message) {
        var resp = new ResponseApi<>(
                HttpStatus.BAD_REQUEST.value(),
                message,
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                null
        );
        return resp;
    }
}
