package com.recordstore.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Intermediate entity to track the quantity of each product in a wishlist.
 * 
 * The {@link WishlistProduct} class is used as a linking table between the {@link Wishlist}
 * and {@link Product} entities. This class allows storing additional information such as
 * the quantity of each product in the wishlist. It maintains many-to-one relationships
 * with both {@link Wishlist} and {@link Product}.
 * 
 * Example usage:
 * 
 * <pre>
 * WishlistProduct wishlistProduct = new WishlistProduct(wishlist, product);
 * </pre>
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class WishlistProduct {

    /**
     * Unique identifier for the wishlist product entry.
     * <p>
     * This value is auto-incrementable and serves as the primary key for the `wishlist_product` table.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Many-to-one relationship with the wishlist.
     * <p>
     * This field represents the wishlist associated with this product entry.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "wishlist_id", nullable = false)
    private Wishlist wishlist;

    /**
     * Many-to-one relationship with the product.
     * <p>
     * This field represents the product associated with this wishlist entry.
     * </p>
     */
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

}

