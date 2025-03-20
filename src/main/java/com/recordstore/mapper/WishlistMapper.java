package com.recordstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.recordstore.dto.BasicProductDTO;
import com.recordstore.dto.UserDTO;
import com.recordstore.dto.WishlistDTO;
import com.recordstore.model.Wishlist;

/**
 * Mapper for converting {@link Wishlist} entities to {@link WishlistDTO} and vice versa.
 * This class is responsible for converting a {@link Wishlist} entity to a {@link WishlistDTO},
 * including the transformation of its associated products into {@link BasicProductDTO} objects.
 */
@Component
public class WishlistMapper {

    /**
     * Converts a {@link Wishlist} entity to a {@link WishlistDTO}.
     *
     * This method takes a {@link Wishlist} entity and converts it to a {@link WishlistDTO}, 
     * including the transformation of each product in the wishlist to a {@link BasicProductDTO}.
     * 
     * @param wishlist The {@link Wishlist} entity to be converted.
     * @return A {@link WishlistDTO} object containing the converted properties of the {@link Wishlist}.
     */
    public WishlistDTO toDTO(Wishlist wishlist) {
        // Convert each WishlistProduct to a BasicProductDTO
        List<BasicProductDTO> productDTOs = wishlist.getListWishlistProducts().stream()
            .map(wishlistProduct -> new BasicProductDTO(wishlistProduct.getProduct())) // Convert the Product from WishlistProduct to BasicProductDTO
            .collect(Collectors.toList());
        
        return new WishlistDTO(wishlist.getId(), new UserDTO(wishlist.getUser()), productDTOs);
    }
}
