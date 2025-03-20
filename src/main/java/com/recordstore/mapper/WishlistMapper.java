package com.recordstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.recordstore.dto.BasicProductDTO;
import com.recordstore.dto.UserDTO;
import com.recordstore.dto.WishlistDTO;
import com.recordstore.model.Wishlist;

@Component
public class WishlistMapper {

    public WishlistDTO toDTO(Wishlist wishlist) {
        // Convertir cada WishlistProduct a un BasicProductDTO
        List<BasicProductDTO> productDTOs = wishlist.getListWishlistProducts().stream()
            .map(wishlistProduct -> new BasicProductDTO(wishlistProduct.getProduct())) // Convertimos el Product de WishlistProduct a BasicProductDTO
            .collect(Collectors.toList());
        
        return new WishlistDTO(wishlist.getId(), new UserDTO(wishlist.getUser()), productDTOs);
    }
}