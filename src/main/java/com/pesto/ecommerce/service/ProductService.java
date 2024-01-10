package com.pesto.ecommerce.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pesto.ecommerce.dto.product.ProductDto;
import com.pesto.ecommerce.model.Category;
import com.pesto.ecommerce.model.Product;
import com.pesto.ecommerce.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public static Product getProductFromDto(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setCategory(category);
        product.setDescription(productDto.getDescription());
        product.setImageURL(productDto.getImageURL());
        product.setPrice(productDto.getPrice());
        product.setName(productDto.getName());
        return product;
    }


    public void addProduct(ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        productRepository.save(product);
    }

    // list of all the products
    public List<ProductDto> listProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : products) {
            productDtos.add(new ProductDto(product));
        }
        return productDtos;
    }

    // update a product
    public void updateProduct(Integer productID, ProductDto productDto, Category category) {
        Product product = getProductFromDto(productDto, category);
        product.setId(productID);
        productRepository.save(product);
    }
}
