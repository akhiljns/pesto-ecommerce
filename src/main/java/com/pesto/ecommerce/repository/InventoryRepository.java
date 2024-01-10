package com.pesto.ecommerce.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pesto.ecommerce.model.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Integer> {

    Optional<Inventory> findByProductId(Integer productId);
}
