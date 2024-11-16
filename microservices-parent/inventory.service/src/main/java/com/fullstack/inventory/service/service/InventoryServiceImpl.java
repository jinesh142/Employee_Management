package com.fullstack.inventory.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fullstack.inventory.service.controller.InventoryResponse;
import com.fullstack.inventory.service.repo.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	InventoryRepository inventoryRepository;

	@Override
	@Transactional(readOnly = true)
	public List<InventoryResponse> isStockAvailabe(List<String> code) {

		return inventoryRepository.findByCodeIn(code).stream().map(inventory -> InventoryResponse.builder()
				.skuCode(inventory.getCode()).isAvailable(inventory.getQuantity() > 0).build()).toList();
	}

}
