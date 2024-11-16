package com.fullstack.product_service1.services;

import java.util.List;

import com.fullstack.product_service1.model.ProductRequest;

public interface ProductService {

	public void createProduct(ProductRequest productRequest);
	public List<ProductRequest> getAllProducts();
}
