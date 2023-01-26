package com.example.demo.restapijvs.product.service;

import com.example.demo.restapijvs.product.entity.ProductEntity;
import com.example.demo.restapijvs.product.model.ProductRequestModel;
import com.example.demo.restapijvs.product.model.ProductResponseModel;
import com.example.demo.restapijvs.product.repository.ProductRepository;
import com.example.demo.restapijvs.utils.Headers;
import com.example.demo.restapijvs.utils.Json;
import com.example.demo.restapijvs.utils.ResponseApi;
import com.example.demo.restapijvs.utils.ValidationApi;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {
    @Autowired(required = false)
    ProductRepository productRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    ResponseApi responseApi;
    @Autowired
    Json json;
    @Autowired
    Headers headers;

    @Override
    public ResponseEntity<ResponseApi> getProducts() {
        var correlationId = headers.GenerateCorrelationId();
        var customHeader = headers.SetCorrelationId(correlationId);
        try {
            log.info(correlationId + " --- [LOG] ProductServiceImpl/getProducts is called");
            log.info(correlationId + " --- [LOG] productRepository.findAll is called");
            var result = (List<ProductEntity>) productRepository.findAll();
            var resultJson = json.toJson(result);
            log.info(correlationId + " --- [LOG] Result from productRepository -> " + resultJson);
            if (result.size() > 0) {
                var data = result.stream().map(x -> modelMapper.map(x, ProductResponseModel.class)).toList();
                return new ResponseEntity<>(responseApi.HttpStatusOK("Success get data", data), customHeader, HttpStatus.OK);
            }
            log.info(correlationId + " --- [LOG] Data not found from database");
            return new ResponseEntity<>(responseApi.DataNotFound(), customHeader, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            log.error(correlationId + " --- [LOG] Error exception -> " + ex);
            return new ResponseEntity<>(responseApi.InternalServerError(ex), customHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseApi> getProductById(Integer productId) {
        var correlationId = new Headers().GenerateCorrelationId();
        var headers = new Headers().SetCorrelationId(correlationId);
        try {
            log.info(correlationId + " --- [LOG] ProductServiceImpl/getProductById is called");
            log.info(correlationId + " --- [LOG] productRepository.findById is called");
            var result = productRepository.findById(productId);
            var resultJson = new Json<>().toJson(result);
            log.info(correlationId + " --- [LOG] Result from productRepository -> : " + resultJson);
            if (!result.isEmpty()) {
                var data = modelMapper.map(result, ProductResponseModel.class);
                return new ResponseEntity<>(responseApi.HttpStatusOK("Success get Data", data), headers, HttpStatus.OK);
            }
            log.info(correlationId + " --- [LOG] Data not found from database");
            return new ResponseEntity<>(responseApi.DataNotFound(), headers, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            log.error(correlationId + " --- [LOG] Error exception -> " + ex);
            return new ResponseEntity<>(responseApi.InternalServerError(ex), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseApi> addProduct(ProductRequestModel request) {
        var correlationId = new Headers().GenerateCorrelationId();
        var headers = new Headers().SetCorrelationId(correlationId);
        try {
            log.info(correlationId + " --- [LOG] ProductServiceImpl/addProduct is called");
            log.info(correlationId + " --- [LOG] Validation request is called");
            var validate = new ValidationApi<>(request).Validate();
            if (!validate.equals("")) {
                log.info(correlationId + " --- [LOG] Validation went wrong : " + validate);
                return new ResponseEntity<>(responseApi.BadRequest(validate), headers, HttpStatus.BAD_REQUEST);
            }
            long size = productRepository.count();
            int newId = (int) size + 1;
            ProductEntity newProduct = new ProductEntity(
                    newId, request.getName(), request.getDescription(), request.getStock(), request.getPrice()
            );
            productRepository.save(newProduct);
            var result = productRepository.findById(newId);
            if (!result.isEmpty()) {
                var resultJson = new Json<>().toJson(result);
                log.info(correlationId + " --- [LOG] Result from productRepository -> " + resultJson);
                var data = modelMapper.map(result, ProductResponseModel.class);
                return new ResponseEntity<>(responseApi.HttpStatusOK("Success add Data", data), headers, HttpStatus.OK);
            }
            log.info(correlationId + " --- [LOG] Data not found from database");
            return new ResponseEntity<>(responseApi.DataNotFound(), headers, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            log.error(correlationId + " --- [LOG] Error exception -> " + ex);
            return new ResponseEntity<>(responseApi.InternalServerError(ex), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseApi> deleteProduct(Integer productId) {
        var correlationId = new Headers().GenerateCorrelationId();
        var headers = new Headers().SetCorrelationId(correlationId);
        try {
            log.info(correlationId + " --- [LOG] ProductServiceImpl/deleteProduct is called");
            log.info(correlationId + " --- [LOG] productRepository.findById is called");
            var getProdById = productRepository.findById(productId);
            if (!getProdById.isEmpty()) {
                log.info(correlationId + " --- [LOG] ProductId -> " + productId + " is deleted");
                productRepository.deleteById(productId);
                return new ResponseEntity<>(responseApi.HttpStatusOK("Success delete Data", null), headers, HttpStatus.OK);
            }
            log.info(correlationId + " --- [LOG] Data not found from database");
            return new ResponseEntity<>(responseApi.DataNotFound(), headers, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            log.error(correlationId + " --- [LOG] Error exception -> " + ex);
            return new ResponseEntity<>(responseApi.InternalServerError(ex), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<ResponseApi> updateProduct(ProductRequestModel request) {
        var correlationId = new Headers().GenerateCorrelationId();
        var headers = new Headers().SetCorrelationId(correlationId);
        try {
            log.info(correlationId + " --- [LOG] ProductServiceImpl/updateProduct is called");
            log.info(correlationId + " --- [LOG] Validation request is called");
            var validate = new ValidationApi<>(request).Validate();
            if (!validate.equals("")) {
                log.info(correlationId + " --- [LOG] Validation went wrong : " + validate);
                return new ResponseEntity<>(responseApi.BadRequest(validate), headers, HttpStatus.BAD_REQUEST);
            }
            var result = productRepository.findById(request.getId());
            if (!result.isEmpty()) {
                var updatedProd = modelMapper.map(request, ProductEntity.class);
                productRepository.save(updatedProd);
                var resultJson = new Json<>().toJson(updatedProd);
                log.info(correlationId + " --- [LOG] Result from productRepository -> " + resultJson);
                var data = modelMapper.map(updatedProd, ProductResponseModel.class);
                return new ResponseEntity<>(responseApi.HttpStatusOK("Success add Data", data), headers, HttpStatus.OK);
            }
            log.info(correlationId + " --- [LOG] Data not found from database");
            return new ResponseEntity<>(responseApi.DataNotFound(), headers, HttpStatus.FORBIDDEN);
        } catch (Exception ex) {
            log.error(correlationId + " --- [LOG] Error exception -> " + ex);
            return new ResponseEntity<>(responseApi.InternalServerError(ex), headers, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
