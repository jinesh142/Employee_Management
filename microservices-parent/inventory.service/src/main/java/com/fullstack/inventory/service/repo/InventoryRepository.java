package com.fullstack.inventory.service.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fullstack.inventory.service.entity.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

	List<Inventory> findByCodeIn(List<String> code);
}
