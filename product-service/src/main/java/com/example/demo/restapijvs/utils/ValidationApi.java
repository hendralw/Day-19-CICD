package com.example.demo.restapijvs.utils;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Set;

public class ValidationApi<T> {


    @Getter
    @Setter
    private T data;
    ValidatorFactory validatorFactory;
    Validator validator;

    public ValidationApi(T data) {
        this.data = data;
    }

    public String Validate() {
        var errMsg = "";
        validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.getValidator();
        Set<ConstraintViolation<T>> violations = validator.validate(data);

        if (violations.size() > 0) {
            for (ConstraintViolation<T> violation : violations) {
                errMsg += " | " + violation.getMessage();
            }
        }
        return errMsg;
    }
}
