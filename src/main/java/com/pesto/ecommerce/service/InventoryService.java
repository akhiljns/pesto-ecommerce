package com.pesto.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pesto.ecommerce.model.Inventory;
import com.pesto.ecommerce.repository.InventoryRepository;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Autowired
    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional
    public void updateInventory(Integer productId, int quantityChange) {
        // Retrieve the current inventory for the product
        Inventory inventory = inventoryRepository.findByProductId(productId).get();

        // Update the inventory quantity
        int newQuantity = inventory.getQuantity() + quantityChange;
        inventory.setQuantity(newQuantity);

        // Save or update the inventory in the database
        inventoryRepository.save(inventory);
    }

}
