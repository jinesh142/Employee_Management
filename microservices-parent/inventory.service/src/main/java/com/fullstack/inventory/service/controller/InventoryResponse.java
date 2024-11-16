package com.fullstack.inventory.service.controller;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryResponse {

	private String skuCode;
	private boolean isAvailable;
}
