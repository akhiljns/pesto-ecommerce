package com.pesto.ecommerce.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "inventory")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    private String name;

    @NotNull
    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private @NotNull Product product;
}
