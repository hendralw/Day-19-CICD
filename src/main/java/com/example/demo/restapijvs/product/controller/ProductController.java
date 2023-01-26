package com.example.demo.restapijvs.product.controller;

import com.example.demo.restapijvs.product.entity.ProductEntity;
import com.example.demo.restapijvs.product.model.ProductRequestModel;
import com.example.demo.restapijvs.product.service.ProductService;
import com.example.demo.restapijvs.utils.ResponseApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/api")
public class ProductController {
    @Autowired(required = false)
    ProductService productService;

    @Operation(summary = "Get all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Product not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/v1/products")
    public ResponseEntity<ResponseApi> getProducts() {
        log.info(" --- [LOG] GET/v1/products is called");
        var result = productService.getProducts();
        return result;
    }

    @Operation(summary = "Get product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Product not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @GetMapping("/v1/products/{productId}")
    public ResponseEntity<ResponseApi> getProductById(@PathVariable Integer productId) {
        log.info(" --- [LOG] GET/v1/products/{productId} is called");
        var result = productService.getProductById(productId);
        return result;
    }

    @Operation(summary = "Add product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Bad request",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PostMapping("/v1/products")
    public ResponseEntity<ResponseApi> addProduct(@RequestBody ProductRequestModel request) {
        log.info(" --- [LOG] POST/v1/products is called");
        var result = productService.addProduct(request);
        return result;
    }

    @Operation(summary = "Delete product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted the product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductEntity.class)) }),
            @ApiResponse(responseCode = "403", description = "Product not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @DeleteMapping("/v1/products/{productId}")
    public ResponseEntity<ResponseApi> deleteProduct(@PathVariable Integer productId) {
        log.info(" --- [LOG] DELETE/v1/products/{productId} is called");
        var result = productService.deleteProduct(productId);
        return result;
    }

    @Operation(summary = "Update product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the product",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ProductRequestModel.class)) }),
            @ApiResponse(responseCode = "403", description = "Product not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content) })
    @PutMapping("/v1/products")
    public ResponseEntity<ResponseApi> updateProduct(@RequestBody ProductRequestModel request) {
        log.info(" --- [LOG] PUT/v1/products is called");
        var result = productService.updateProduct(request);
        return result;
    }
}
