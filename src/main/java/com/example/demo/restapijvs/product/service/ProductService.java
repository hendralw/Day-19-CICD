package com.example.demo.restapijvs.product.service;

import com.example.demo.restapijvs.product.model.ProductRequestModel;
import com.example.demo.restapijvs.user.model.UserRequestModel;
import com.example.demo.restapijvs.utils.ResponseApi;
import org.springframework.http.ResponseEntity;

public interface ProductService {
    ResponseEntity<ResponseApi> getProducts();

    ResponseEntity<ResponseApi> getProductById(Integer productId);

    ResponseEntity<ResponseApi> addProduct(ProductRequestModel request);

    ResponseEntity<ResponseApi> deleteProduct(Integer productId);

    ResponseEntity<ResponseApi> updateProduct(ProductRequestModel request);
}
