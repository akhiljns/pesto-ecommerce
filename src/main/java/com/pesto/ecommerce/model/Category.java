package com.pesto.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "category")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "category_name")
	private @NotBlank String categoryName;

	private @NotBlank String description;

	private @NotBlank String imageUrl;

}
