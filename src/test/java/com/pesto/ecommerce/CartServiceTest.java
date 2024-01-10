package com.pesto.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pesto.ecommerce.exception.BadRequestException;
import com.pesto.ecommerce.model.Cart;
import com.pesto.ecommerce.model.Inventory;
import com.pesto.ecommerce.model.Product;
import com.pesto.ecommerce.repository.CartRepository;
import com.pesto.ecommerce.repository.ProductRepository;
import com.pesto.ecommerce.service.CartService;

class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CartService cartService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void addToCart_validProductAndQuantity_shouldAddToCart() {
        // Arrange
        Integer userId = 123;
        Integer productId = 1;
        int quantity = 3;

        Cart cart = new Cart();
        Product product = new Product();
        Inventory inventory = new Inventory();
        inventory.setQuantity(5);
        product.setId(productId);
        product.setInventory(inventory);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act
        cartService.addToCart(userId, productId, quantity);

        // Assert
        assertEquals(quantity, cart.getProducts().get(productId));
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void addToCart_invalidProduct_shouldThrowBadRequestException() {
        // Arrange
        Integer userId = 123;
        Integer productId = 1;
        int quantity = 3;

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(new Cart()));
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(BadRequestException.class, () -> cartService.addToCart(userId, productId, quantity));
        verify(cartRepository, never()).save(any());
    }

    @Test
    void addToCart_quantityExceedsInventory_shouldThrowBadRequestException() {
        // Arrange
        Integer userId = 123;
        Integer productId = 1;
        int quantity = 8;

        Cart cart = new Cart();
        Product product = new Product();
        Inventory inventory = new Inventory();
        inventory.setQuantity(5);
        product.setId(productId);
        product.setInventory(inventory);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        // Act and Assert
        assertThrows(BadRequestException.class, () -> cartService.addToCart(userId, productId, quantity));
        verify(cartRepository, never()).save(any());
    }

    @Test
    void removeProduct_validProduct_shouldRemoveFromCart() {
        // Arrange
        Integer userId = 123;
        Integer productId = 1;

        Cart cart = new Cart();
        cart.getProducts().put(productId, 3);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        // Act
        cartService.removeProduct(userId, productId);

        // Assert
        assertFalse(cart.getProducts().containsKey(productId));
        verify(cartRepository, times(1)).save(cart);
    }


    @Test
    void viewCart_validUserId_shouldReturnCartItems() {
        // Arrange
        Integer userId = 123;

        Cart cart = new Cart();
        cart.getProducts().put(1, 2);
        cart.getProducts().put(3, 5);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        // Act
        Map<Integer, Integer> result = cartService.viewCart(userId);

        // Assert
        assertEquals(cart.getProducts(), result);
    }

    @Test
    void viewCart_invalidUserId_shouldReturnEmptyMap() {
        // Arrange
        Integer userId = 123;

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // Act
        Map<Integer, Integer> result = cartService.viewCart(userId);

        // Assert
        assertTrue(result.isEmpty());
    }

    @Test
    void updateCart_validProductAndQuantity_shouldUpdateCart() {
        // Arrange
        Integer userId = 123;
        Integer productId = 1;
        int quantity = 3;

        Cart cart = new Cart();
        cart.getProducts().put(productId, 2);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        // Act
        cartService.updateCart(userId, productId, quantity);

        // Assert
        assertEquals(quantity, cart.getProducts().get(productId));
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void updateCart_invalidCart_shouldThrowBadRequestException() {
        // Arrange
        Integer userId = 123;
        Integer productId = 1;
        int quantity = 3;

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(BadRequestException.class, () -> cartService.updateCart(userId, productId, quantity));
        verify(cartRepository, never()).save(any());
    }

    @Test
    void updateCart_quantityZero_shouldRemoveFromCart() {
        // Arrange
        Integer userId = 123;
        Integer productId = 1;
        int quantity = 0;

        Cart cart = new Cart();
        cart.getProducts().put(productId, 2);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        // Act
        cartService.updateCart(userId, productId, quantity);

        // Assert
        assertFalse(cart.getProducts().containsKey(productId));
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void clearCart_validUserId_shouldClearCart() {
        // Arrange
        Integer userId = 123;

        Cart cart = new Cart();
        cart.getProducts().put(1, 2);
        cart.getProducts().put(3, 5);

        when(cartRepository.findByUserId(userId)).thenReturn(Optional.of(cart));

        // Act
        cartService.clearCart(userId);

        // Assert
        assertTrue(cart.getProducts().isEmpty());
        verify(cartRepository, times(1)).save(cart);
    }

}
