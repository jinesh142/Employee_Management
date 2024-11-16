package com.fullstack.product.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fullstack.product.service.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
