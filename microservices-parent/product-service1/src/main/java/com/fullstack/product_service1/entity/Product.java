package com.fullstack.product_service1.entity;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document(value = "product")
@Data
public class Product {

	@Id
	private String id;
	private String name;
	private String describtion;
	private BigDecimal price;
}
