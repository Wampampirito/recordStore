package com.recordstore.model;
import com.recordstore.validation.Min;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the intermediary entity that stores the quantity of each product in an order.
 * This class links a specific product to an order, along with the quantity of the product.
 * 
 * This class creates a many-to-one relationship with both the {@link Order} and {@link Product} entities,
 * allowing the tracking of multiple products within a single order, and the quantity of each product in the order.
 * 
 * @see Order
 * @see Product
 */
@Data
@NoArgsConstructor
@Entity
public class OrderProduct {

    /**
     * The unique identifier for the order product.
     * This is automatically generated by the database in an auto-incremental fashion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The many-to-one relationship with the Order entity.
     * This field establishes the connection between the product and the order it belongs to.
     * 
     * @see Order
     */
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    /**
     * The many-to-one relationship with the Product entity.
     * This field establishes the connection between the order product and the product it represents.
     * 
     * @see Product
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    /**
     * The quantity of the product in the order.
     * This value must be greater than 0, as the product must appear at least once in the order.
     */
    @Column(name = "quantity", nullable = false)
    @Min(1)
    private Integer quantity;

    /**
     * Constructor to initialize the relationship between OrderProduct and Product entities.
     * This constructor allows the linking of an order with a product and sets the quantity for that product in the order.
     * 
     * @param order   The order to which the product belongs.
     * @param product The product associated with the order.
     * @param quantity The quantity of the product in the order (minimum 1).
     */
    public OrderProduct(Order order, Product product, Integer quantity) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
    }
}
