package com.pesto.ecommerce.dto.product;

import javax.validation.constraints.NotNull;

import com.pesto.ecommerce.model.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer id;
    private @NotNull String name;
    private @NotNull String imageURL;
    private @NotNull double price;
    private @NotNull String description;
    private @NotNull Integer categoryId;
    private Integer quantity;

    public ProductDto(Product product) {
        this.setId(product.getId());
        this.setName(product.getName());
        this.setImageURL(product.getImageURL());
        this.setDescription(product.getDescription());
        this.setPrice(product.getPrice());
        this.setCategoryId(product.getCategory().getId());
        this.setQuantity(product.getInventory().getQuantity());
    }

}
