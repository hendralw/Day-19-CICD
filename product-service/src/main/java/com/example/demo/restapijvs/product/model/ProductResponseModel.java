package com.example.demo.restapijvs.product.model;

import lombok.Data;

@Data
public class ProductResponseModel {
    private int id;
    private String name;
    private String description;
    private int stock;
    private int price;
}
