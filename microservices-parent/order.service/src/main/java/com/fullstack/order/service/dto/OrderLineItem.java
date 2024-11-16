package com.fullstack.order.service.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class OrderLineItem {

	private Long id;
	
	private String code;
	
	private BigDecimal price;
	
	private int quantity;
}
