package com.example.demo.restapijvs.product.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequestModel {
    private int id;
    @NotNull(message = "name must not be null")
    @NotBlank(message = "name must not be blank")
    private String name;
    @NotBlank(message = "description must not be blank")
    private String description;
    @NotNull(message = "stock must not be null")
    private Integer stock;
    @Max(value = 12, message = "price max 12 digit")
    @NotNull(message = "price must not be null")
    private Long price;
}
