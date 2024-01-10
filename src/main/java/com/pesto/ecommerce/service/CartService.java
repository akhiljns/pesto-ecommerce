package com.pesto.ecommerce.service;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pesto.ecommerce.exception.BadRequestException;
import com.pesto.ecommerce.model.Cart;
import com.pesto.ecommerce.model.Product;
import com.pesto.ecommerce.repository.CartRepository;
import com.pesto.ecommerce.repository.ProductRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    @Autowired
    public CartService(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public void addToCart(Integer userId, Integer productId, int quantity) {
        // Retrieve the user's cart from the database
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        Cart cart = optionalCart.orElse(new Cart());

        // Check if the product exists
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();

            // Check if the requested quantity is available in the inventory
            if (quantity <= product.getInventory().getQuantity()) {
                // Update the quantity of the product in the cart
                cart.getProducts().put(productId, quantity);

                // Set the user ID for the cart
                cart.setUserId(userId);

                // Save or update the cart in the database
                cartRepository.save(cart);
            } else {
                throw new BadRequestException("quantity entered more than inventory");
            }
        } else {
            throw new BadRequestException("Error: Product not found with ID " + productId);
        }
    }

    public void removeProduct(Integer userId, Integer productId) {
        // Retrieve the user's cart from the database
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            // Remove the product from the cart
            cart.getProducts().remove(productId);

            // Save or update the cart in the database
            cartRepository.save(cart);
        } else {
            throw new BadRequestException("users cart is empty");
        }
    }

    public Map<Integer, Integer> viewCart(Integer userId) {
        // Retrieve the user's cart from the database
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        return optionalCart.map(Cart::getProducts).orElse(Collections.emptyMap());
    }

    public void updateCart(Integer userId, Integer productId, int quantity) {
        // Retrieve the user's cart from the database
        Optional<Cart> optionalCart = cartRepository.findByUserId(userId);
        if (optionalCart.isPresent()) {
            Cart cart = optionalCart.get();

            // Update the quantity of the product in the cart
            cart.getProducts().put(productId, quantity);

            // Remove the product if quantity is 0
            if (quantity <= 0) {
                cart.getProducts().remove(productId);
            }

            // Save or update the cart in the database
            cartRepository.save(cart);
        } else {
            throw new BadRequestException("cart with userid does not exist");
        }
    }

    @Transactional
    public void clearCart(Integer userId) {
        // Retrieve the user's cart from the database
        Cart cart = cartRepository.findByUserId(userId).orElse(new Cart());

        // Clear the products in the cart
        cart.getProducts().clear();

        // Save or update the cart in the database
        cartRepository.save(cart);
    }
}
