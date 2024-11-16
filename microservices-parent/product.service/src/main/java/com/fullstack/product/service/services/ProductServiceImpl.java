package com.fullstack.product.service.services;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fullstack.product.service.entity.Product;
import com.fullstack.product.service.model.ProductRequest;
import com.fullstack.product.service.repository.ProductRepository;

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
