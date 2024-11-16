package com.fullstack.order.service.dto;

import java.util.List;

import lombok.Data;

@Data
public class OrderRequest {

	private List<OrderLineItem> orderLineItemList;
}
