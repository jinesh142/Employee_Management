package com.fullstack.order.service.service;

import com.fullstack.order.service.dto.OrderRequest;

public interface OrderService {

	public String placeOrder(OrderRequest orderRequests);
}
