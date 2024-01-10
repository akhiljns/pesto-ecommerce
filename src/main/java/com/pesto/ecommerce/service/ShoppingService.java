package com.pesto.ecommerce.service;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ShoppingService {

    @Autowired
    private InventoryService inventoryService;

    @Autowired
    private CartService cartService;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();


   @Transactional(isolation = Isolation.READ_COMMITTED)
    public void buyItems(Integer userId) {
        // Retrieve the user's cart
        Map<Integer, Integer> cartItems = cartService.viewCart(userId);

        // Spawn a new thread for the transaction
        if(cartItems.size()>0){
            executorService.execute(() -> performCheckout(userId, cartItems));
        } else {
            
        }
    }

    private void performCheckout(Integer userId, Map<Integer, Integer> cartItems) {
        try {
            // Perform the checkout transaction
            for (Map.Entry<Integer, Integer> entry : cartItems.entrySet()) {
                Integer productId = entry.getKey();
                int quantity = entry.getValue();

                // Update inventory
                inventoryService.updateInventory(productId, -quantity);
            }

            // Clear the user's cart after successful checkout
            cartService.clearCart(userId);
        } catch (Exception e) {
            // Handle exceptions as needed
            e.printStackTrace();
        }
    }

    @PreDestroy
    public void cleanUp() {
        // Shutdown the executor service during application shutdown
        executorService.shutdown();
    }
    
}
