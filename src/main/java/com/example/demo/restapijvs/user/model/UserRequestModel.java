package com.example.demo.restapijvs.user.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestModel {
    private int id;
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotNull(message = "email must not be null")
    @NotBlank(message = "email must not be blank")
    private String email;
    @NotNull(message = "phone must not be null")
    @NotBlank(message = "phone must not be blank")
    private String phone;
    @NotNull(message = "password must not be null")
    @NotBlank(message = "password must not be blank")
    private String password;
}
