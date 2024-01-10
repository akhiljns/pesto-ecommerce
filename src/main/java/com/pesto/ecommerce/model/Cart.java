package com.pesto.ecommerce.model;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "cart")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private Integer userId;

    @ElementCollection
    @CollectionTable(
            name = "cart_product",
            joinColumns = @JoinColumn(name = "cart_id")
    )
    @MapKeyColumn(name = "product_id")
    @Column(name = "quantity")
    private Map<Integer, Integer> products = new HashMap<>();

}
