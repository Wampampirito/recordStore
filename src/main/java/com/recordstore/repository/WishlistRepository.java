package com.recordstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recordstore.dto.WishlistDTO;
import com.recordstore.model.Wishlist;
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Double> {

    /**
     * Finds a wishlist by the user's ID.
     *
     * @param userId the ID of the user whose wishlist is to be found
     * @return the wishlist associated with the user
     */
    Wishlist findByUserId(Integer userId);

    /**
     * Gets a wishlist DTO by the user's ID.
     *
     * @param userId the ID of the user whose wishlist DTO is to be fetched
     * @return the wishlist DTO associated with the user
     */
    WishlistDTO getWishlistByUserId(Integer userId);

    /**
     * Finds a wishlist by its ID.
     *
     * @param id the ID of the wishlist to find
     * @return an Optional containing the wishlist if found
     */
    Optional<Wishlist> findById(Integer id);

    /**
     * Finds all wishlists that contain a specific product by the product's ID.
     *
     * @param id the ID of the product
     * @return a list of wishlists containing the specified product
     */
    @Query("SELECT w FROM Wishlist w " +
    "JOIN w.listWishlistProducts wp " +
    "JOIN wp.product p " +
    "WHERE p.id = :id")
    List<Wishlist> findWishlistsByProductId(@Param("id") Integer id);

    boolean existsByListWishlistProducts_Product_Id(Integer id);

}
