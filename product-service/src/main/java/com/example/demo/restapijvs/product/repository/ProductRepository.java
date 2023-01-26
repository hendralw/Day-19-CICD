package com.example.demo.restapijvs.product.repository;

import com.example.demo.restapijvs.product.entity.ProductEntity;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
}
