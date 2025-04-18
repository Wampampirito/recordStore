package com.recordstore.model;

import com.recordstore.enums.PRODUCT_CATEGORY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;
import jakarta.persistence.InheritanceType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
/**
 * Abstract class representing a product in the system, serving as the base for product types like Vinyl, Portable, 
 * Turntable, Headphone, and Speaker. It implements the {@code JOINED} inheritance strategy and includes common attributes
 * like name, price, stock, and product category.
 * 
 * This class also uses Jackson annotations for serialization and deserialization of its subclasses.
 * 
 * @see Vinyl
 * @see Portable
 * @see Turntable
 * @see Headphone
 * @see Speaker
 */
@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Table(name = "products")
@Data
@EqualsAndHashCode
@NoArgsConstructor
public abstract class Product {

    /**
     * Unique identifier for the product (primary key).
     * Automatically generated by the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    /**
     * The name of the product.
     */
    @Column(name = "name")
    private String name;

    /**
     * The price of the product.
     */
    @Column(name = "price")
    private Double price;

    /**
     * The available stock of the product.
     */
    @Column(name = "stock")
    private Integer stock;

    /**
     * The category of the product, determining its type (e.g., Vinyl, Headphone).
     * Stored as an enumeration.
     */
    @Column(name = "product_category")
    @Enumerated(EnumType.STRING)
    private PRODUCT_CATEGORY productCategory;

    /**
     * Constructor to initialize a product with its name, price, and stock quantity.
     *
     * @param name            The product's name.
     * @param price           The product's price.
     * @param stock           The available stock.
     */
    public Product(String name, Double price, Integer stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
}
