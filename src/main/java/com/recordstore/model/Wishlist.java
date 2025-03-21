package com.recordstore.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity representing a user's wishlist.
 * 
 * The {@link Wishlist} class is associated with a specific user and can contain
 * multiple products that the user has added to their wishlist. This class also maintains
 * a one-to-one relationship with the {@link User} entity and a one-to-many relationship with
 * the {@link WishlistProduct} entity, which represents the specific products in the wishlist.
 * 
 * The unique identifier of the wishlist is an auto-incrementing value that serves as the primary key
 * for the `wishlist` table. The wishlist is associated with a user via the `user_id` relationship field.
 * The products in the wishlist are managed through the {@link WishlistProduct} entity, which is linked
 * to the wishlist through the `wishlist` relationship.
 * 
 * The relationships and management of the products in the wishlist are configured
 * to allow for the automatic removal of products when the wishlist is deleted or when a product
 * is explicitly removed from the wishlist.
 * 
 * Example usage:
 * 
 * <pre>
 * Wishlist wishlist = new Wishlist(user);
 * wishlist.setUser(user);
 * </pre>
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "wishlist")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Wishlist {
    
    /**
     * Unique identifier for the wishlist.
     * <p>
     * This value is auto-incrementing and serves as the primary key for the `wishlist` table.
     * </p>
     */
    @Id
    @JoinColumn(name = "wishlist_Id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * One-to-one relationship with the user.
     * <p>
     * This field represents the user associated with the wishlist. The `user_id` foreign key
     * links the wishlist to the corresponding user.
     * </p>
     */
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * One-to-many relationship with the wishlist products.
     * <p>
     * This represents the products in the wishlist. The `wishlist` field in {@link WishlistProduct}
     * links the products to this wishlist. Products are automatically removed if orphaned.
     * </p>
     */
    @OneToMany(mappedBy = "wishlist", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishlistProduct> listWishlistProducts = new ArrayList<>();

    /**
     * Constructor for creating a new wishlist associated with a specific user.
     * 
     * This constructor initializes the wishlist with the provided user, allowing it
     * to be correctly associated with the user in the database.
     * 
     * @param user The user to whom the wishlist will be associated.
     */
    public Wishlist(User user) {
        this.user = user;
    }
}
