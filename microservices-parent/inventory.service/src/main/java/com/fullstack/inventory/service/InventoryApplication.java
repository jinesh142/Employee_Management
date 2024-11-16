package com.fullstack.inventory.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.fullstack.inventory.service.entity.Inventory;
import com.fullstack.inventory.service.repo.InventoryRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository repo) {
		return args -> {
			Inventory inv = new Inventory();
			inv.setCode("Code 1");
			inv.setQuantity(10);
			
			Inventory inv2 = new Inventory();
			inv2.setCode("Code 2");
			inv2.setQuantity(0);
			
			repo.save(inv);
			repo.save(inv2);
		};
	}
}
