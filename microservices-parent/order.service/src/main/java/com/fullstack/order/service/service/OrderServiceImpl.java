package com.fullstack.order.service.service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fullstack.order.service.dto.InventoryResponse;
import com.fullstack.order.service.dto.OrderLineItem;
import com.fullstack.order.service.dto.OrderRequest;
import com.fullstack.order.service.entity.Order;
import com.fullstack.order.service.entity.OrderItem;
import com.fullstack.order.service.repo.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	private WebClient.Builder webClientBuilder;

	@Autowired
	public OrderServiceImpl(WebClient.Builder webClient) {
		this.webClientBuilder = webClient;
	}

	@Override
	public String placeOrder(OrderRequest orderRequests) {
		log.info("placeOrder method called ");
		Order order = new Order();
		order.setOrderNumber(UUID.randomUUID().toString());

		List<OrderItem> orderLineItems = orderRequests.getOrderLineItemList().stream().map(this::mapToDto).toList();

		order.setOrderItems(orderLineItems);

		List<String> codes = orderRequests.getOrderLineItemList().stream().map(OrderLineItem::getCode).toList();

		InventoryResponse[] response = webClientBuilder.build().get()
				.uri("http://INVENTORY-SERVICE/api/inventory", t -> t.queryParam("code", codes).build()).retrieve()
				.bodyToMono(InventoryResponse[].class).block();

		Boolean isStock = Arrays.stream(response).allMatch(InventoryResponse::isAvailable);

		if (Boolean.TRUE.equals(isStock)) {
			orderRepository.save(order);
			log.info("Order placed successfully with {} ", order.getId());
			return "Order Placed Successfully";
		} else {
			throw new IllegalArgumentException("Not All product are in stock , please try after few times");
		}

	}

	private OrderItem mapToDto(OrderLineItem orderLineItem) {
		OrderItem items = new OrderItem();
		items.setCode(orderLineItem.getCode());
		items.setPrice(orderLineItem.getPrice());
		items.setQuantity(orderLineItem.getQuantity());
		return items;
	}

}
