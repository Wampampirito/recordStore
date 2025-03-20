package com.recordstore.dto;

import java.util.List;

import com.recordstore.model.Wishlist;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) representing a user's wishlist for data transfer.
 * 
 * The {@link WishlistDTO} class is used to send a user's wishlist data across the 
 * presentation layer. It includes the wishlist identifier, user information (represented 
 * by a {@link UserDTO}), and a list of products (represented by a list of 
 * {@link BasicProductDTO}) that are in the wishlist.
 * 
 * This class is useful to avoid direct exposure of the {@link Wishlist} entity in the 
 * API, promoting a clean and efficient architecture. The data sent in the REST response body 
 * is organized using this DTO.
 * 
 * Example usage:
 * <pre>
 * WishlistDTO wishlistDTO = new WishlistDTO(wishlistId, userDTO, productDTOs);
 * </pre>
 */
@Data
@NoArgsConstructor
public class WishlistDTO {

    private int wishlistId;
    private UserDTO user;
    private List<BasicProductDTO> products;

    /**
     * Constructor to create a new wishlist DTO with the provided data.
     * 
     * This constructor initializes a {@link WishlistDTO} object with the wishlist 
     * identifier, user information, and corresponding products.
     * 
     * @param wishlistId The unique identifier of the wishlist.
     * @param userDTO The {@link UserDTO} of the user associated with the wishlist.
     * @param productDTOs The list of products in the wishlist.
     */
    public WishlistDTO(int wishlistId, UserDTO userDTO, List<BasicProductDTO> productDTOs) {
        this.wishlistId = wishlistId;
        this.user = userDTO;
        this.products = productDTOs;
    }
}
