package com.pesto.ecommerce.controllers;

import com.pesto.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<String> addToCart(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam int quantity) {
        try {
            cartService.addToCart(userId, productId, quantity);
            return new ResponseEntity<>("Product added to cart successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/remove")
    public ResponseEntity<String> removeProduct(@RequestParam Integer userId, @RequestParam Integer productId) {
        try {
            cartService.removeProduct(userId, productId);
            return new ResponseEntity<>("Product removed from cart successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/view")
    public ResponseEntity<?> viewCart(@RequestParam Integer userId) {
        try {
            return new ResponseEntity<>(cartService.viewCart(userId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCart(@RequestParam Integer userId, @RequestParam Integer productId, @RequestParam int quantity) {
        try {
            cartService.updateCart(userId, productId, quantity);
            return new ResponseEntity<>("Cart updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/clear")
    public ResponseEntity<String> clearCart(@RequestParam Integer userId) {
        try {
            cartService.clearCart(userId);
            return new ResponseEntity<>("Cart cleared successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
