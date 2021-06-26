package com.techie.microservices.inventoryservice.repository;

import com.techie.microservices.inventoryservice.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//@Repository
public interface InventoryRepository extends JpaRepository<Integer,Long> {
    Optional<Inventory> findBySkuCode(String skuCode);
}
