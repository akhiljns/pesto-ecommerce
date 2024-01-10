package com.pesto.ecommerce.controllers;

import com.pesto.ecommerce.service.ShoppingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/shopping")
public class ShoppingController {

    @Autowired
    private ShoppingService shoppingService;

    @PostMapping("/buy")
    public ResponseEntity<String> buyItems(@RequestParam Integer userId) {
        try {
            shoppingService.buyItems(userId);
            return new ResponseEntity<>("Items bought successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Handle exceptions and return an appropriate response
            return new ResponseEntity<>("Failed to buy items: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
