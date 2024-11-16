package com.fullstack.product_service1.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.product_service1.entity.Product;
import com.fullstack.product_service1.model.ProductRequest;
import com.fullstack.product_service1.repository.ProductRepository;
import com.google.common.reflect.TypeToken;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepo;

	ModelMapper modelMapper = new ModelMapper();

	@Override
	public void createProduct(ProductRequest productRequest) {

		Product product = modelMapper.map(productRequest, Product.class);
		productRepo.save(product);
		log.info("Product {} saved....!!", product.getId());
	}

	@Override
	public List<ProductRequest> getAllProducts() {
		List<Product> productList = productRepo.findAll();
		return modelMapper.map(productList, new TypeToken<List<ProductRequest>>() {
		}.getType());
	}

}
