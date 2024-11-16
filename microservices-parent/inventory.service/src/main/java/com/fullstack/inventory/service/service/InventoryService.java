package com.fullstack.inventory.service.service;

import java.util.List;

import com.fullstack.inventory.service.controller.InventoryResponse;

public interface InventoryService {

	public List<InventoryResponse> isStockAvailabe(List<String> code);
	
}
