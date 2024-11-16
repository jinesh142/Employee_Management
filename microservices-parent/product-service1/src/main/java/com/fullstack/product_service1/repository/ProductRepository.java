package com.fullstack.product_service1.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fullstack.product_service1.entity.Product;

public interface ProductRepository extends MongoRepository<Product, String>{

}
