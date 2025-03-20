package com.recordstore.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.recordstore.enums.ORDER_STATUS;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class that represents an order in the record store.
 * It contains information about the user, products, status, and total amount.
 * 
 * @author Ivan Egued
 * 
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    /**
     * The identifier of the order.
     * Automatically generated by the database in an incremental fashion.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderId;

    /**
     * The unique tracking number of the order.
     * Follows the format RCD-<userId>-<userPrefix>-<datePart>-<orderNumber>.
     * @see com.recordstore.service.OrderService
     */
    @Column(unique = true, nullable = false)
    private String trackingNumber; // Unique tracking number

    /**
     * The user who placed the order.
     * A many-to-one relationship with the {@link com.recordstore.model.User} class, where an order 
     * belongs to a single user.
     * @see com.recordstore.model.User
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user; // User who placed the order

    /**
     * The products in the order.
     * A one-to-many relationship with the {@link com.recordstore.model.OrderProduct} class, where an order 
     * can contain multiple products.
     * Bidirectional relationship: {@link com.recordstore.model.OrderProduct} references this order.
     * @see com.recordstore.model.OrderProduct
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderProduct> listOrderProducts = new ArrayList<>();

    /**
     * The status of the order.
     * Represents the current status of the order, which can be a value from the enumeration 
     * {@link com.recordstore.enums.Order_STATUS}.
     * @see com.recordstore.enums.Order_STATUS
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ORDER_STATUS status;

    /**
     * The total amount of the order.
     * The amount is the result of summing the multiplications of the price by the 
     * quantity of each product in the order. This value is calculated automatically
     * by {@link com.recordstore.service.OrderService} when saving the order.
     * 
     * @see com.recordstore.service.OrderService
     */
    @Column(name = "totalAmount")
    private Double totalAmount;
}

