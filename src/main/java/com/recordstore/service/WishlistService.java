package com.recordstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recordstore.dto.WishlistDTO;
import com.recordstore.model.Product;
import com.recordstore.model.Wishlist;
import com.recordstore.model.WishlistProduct;
import com.recordstore.repository.ProductRepository;
import com.recordstore.repository.WishlistRepository;

import jakarta.transaction.Transactional;

/**
 * Service for managing wishlists in the system.
 * <p>
 * This service provides methods to retrieve, add, and remove products from wishlists.
 * </p>
 */
@Service
public class WishlistService {
    /**
     * Dependencies needed for the WishlistService.
     */
    private WishlistRepository wishlistRepository; // Repository for accessing Wishlist entities
    private ProductRepository productRepository; // Repository for accessing Product entities

    /**
     * Constructor to initialize the WishlistService with dependencies.
     * @param wishlistRepository Repository for accessing Wishlist entities
     * @param productRepository Repository for accessing Product entities
     */
    @Autowired
    public WishlistService(WishlistRepository wishlistRepository, ProductRepository productRepository) {
        this.wishlistRepository = wishlistRepository;
        this.productRepository = productRepository;
    }
    /**
     * Retrieves a wishlist by the user's ID.
     *
     * @param userId The ID of the user whose wishlist is to be retrieved.
     * @return A {@link WishlistDTO} representing the user's wishlist.
     */
    public WishlistDTO getWishlistByUserId(Integer userId) {
        return wishlistRepository.getWishlistByUserId(userId);
    }


    /**
     * Adds a product to a wishlist.
     * <p>
     * This method retrieves the specified wishlist and product from the database, 
     * creates an association between them, and updates the wishlist.
     * </p>
     *
     * @param wishlistId The ID of the wishlist to which the product will be added.
     * @param productId  The ID of the product to be added to the wishlist.
     */
    @Transactional
    public void addProductToWishlist(Integer wishlistId, Integer productId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        WishlistProduct wishlistProduct = new WishlistProduct();
        wishlistProduct.setWishlist(wishlist);
        wishlistProduct.setProduct(product);

        wishlist.getListWishlistProducts().add(wishlistProduct);
        wishlistRepository.save(wishlist);
    }

    /**
     * Removes a product from a wishlist.
     * <p>
     * This method retrieves the specified wishlist and removes the product 
     * from the wishlist if it exists.
     * </p>
     *
     * @param wishlistId The ID of the wishlist from which the product will be removed.
     * @param productId  The ID of the product to be removed from the wishlist.
     */
    public void removeProductFromWishlist(Double wishlistId, Double productId) {
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));

        wishlist.getListWishlistProducts()
                .removeIf(wishlistProduct -> wishlistProduct.getProduct().getId().equals(productId.intValue()));

        wishlistRepository.save(wishlist);
    }
}
