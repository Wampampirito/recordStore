package com.recordstore.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Represents a user in the system.
 * <p>
 * This class contains the basic information about a user, including personal details such as name, phone,
 * email, password, and address. It also maintains relationships with the user's orders and wishlist.
 * </p>
 * <p>
 * The user can have multiple orders and a single wishlist. Orders are managed in a one-to-many relationship,
 * and the wishlist is in a one-to-one relationship with the user.
 * </p>
 * 
 * @see Order
 * @see Wishlist
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    
    /**
     * Unique identifier for the user (primary key).
     * <p>
     * This is auto-generated by the database.
     * </p>
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    /**
     * Name of the user.
     */
    @Column(name = "name")
    private String name;

    /**
     * Phone number of the user.
     */
    @Column(name = "phone")
    private String phone;

    /**
     * Email address of the user.
     */
    @Column(name = "mail")
    private String email;

    /**
     * Password of the user.
     * <p>
     * This field should be encrypted for security purposes.
     * </p>
     */
    @Column(name = "password")
    @JsonIgnore
    private String password;

    /**
     * Address of the user.
     */
    @Column(name = "address")
    private String address;

    /**
     * List of orders placed by the user.
     * <p>
     * This represents a one-to-many relationship between a user and orders.
     * Orders are automatically removed if orphaned, and changes are cascaded.
     * </p>
     */
    
    @OneToMany(mappedBy = "user", 
               cascade = CascadeType.ALL, 
               orphanRemoval = true)
    @JsonManagedReference
    private List<Order> listOrder = new ArrayList<>();

    /**
     * Wishlist of the user.
     * <p>
     * This represents a one-to-one relationship between a user and their wishlist.
     * The wishlist is automatically removed if orphaned, and changes are cascaded.
     * </p>
     */
    @OneToOne(mappedBy = "user", 
              cascade = CascadeType.ALL, 
              orphanRemoval = true)
    private Wishlist listWishlist;

    /**
     * Parameterized constructor to initialize the user with their basic details.
     * 
     * @param name     The name of the user.
     * @param phone    The phone number of the user.
     * @param email    The email address of the user.
     * @param password The password of the user.
     * @param address  The address of the user.
     */
    public User(String name, String phone, String email, String password, String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.address = address;
    }
}

