package com.pesto.ecommerce;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pesto.ecommerce.service.CartService;
import com.pesto.ecommerce.service.InventoryService;
import com.pesto.ecommerce.service.ShoppingService;

public class ShoppingServiceTest {

    @Mock
    private InventoryService inventoryService;

    @Mock
    private CartService cartService;

    @InjectMocks
    private ShoppingService shoppingService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void buyItems_shouldPerformCheckout() {
        // Arrange
        Integer userId = 123;
        Map<Integer, Integer> cartItems = new HashMap<>();
        cartItems.put(1, 2);
        cartItems.put(2, 3);

        when(cartService.viewCart(userId)).thenReturn(cartItems);

        // Act
        shoppingService.buyItems(userId);

        // Assert
        verify(inventoryService, times(1)).updateInventory(1, -2);
        verify(inventoryService, times(1)).updateInventory(2, -3);
        verify(cartService, times(1)).clearCart(userId);
    }

    @Test
    void buyItems_shouldNotPerformCheckoutIfCartIsEmpty() {
        // Arrange
        Integer userId = 456;
        when(cartService.viewCart(userId)).thenReturn(Collections.emptyMap());

        // Act
        shoppingService.buyItems(userId);

        // Assert
        verify(inventoryService, never()).updateInventory(anyInt(), anyInt());
        verify(cartService, never()).clearCart(userId);
    }
}
