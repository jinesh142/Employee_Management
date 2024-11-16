package com.fullstack.product.service.services;

import java.util.List;

import com.fullstack.product.service.model.ProductRequest;

public interface ProductService {

	public void createProduct(ProductRequest productRequest);
	public List<ProductRequest> getAllProducts();
}
