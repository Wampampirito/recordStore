package com.recordstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recordstore.dto.WishlistDTO;
import com.recordstore.model.Wishlist;

/**
 * Repository interface for managing {@link Wishlist} entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for performing CRUD operations on 
 * {@link Wishlist} entities, as well as custom queries related to user wishlists and product associations.
 * </p>
 */
@Repository
public interface WishlistRepository extends JpaRepository<Wishlist, Double> {

    /**
     * Finds a wishlist associated with the given user ID.
     *
     * @param userId the ID of the user whose wishlist is to be fetched
     * @return the {@link Wishlist} associated with the given user ID
     */
    Wishlist findByUserId(Integer userId);

    /**
     * Retrieves a {@link WishlistDTO} for the wishlist associated with the given user ID.
     *
     * @param userId the ID of the user whose wishlist DTO is to be retrieved
     * @return the {@link WishlistDTO} associated with the user
     */
    WishlistDTO getWishlistByUserId(Integer userId);

    /**
     * Finds a wishlist by its ID.
     *
     * @param id the ID of the wishlist to be found
     * @return an {@link Optional} containing the wishlist if found, or empty if not
     */
    Optional<Wishlist> findById(Integer id);

    /**
     * Finds all wishlists that contain a specific product identified by its ID.
     *
     * @param id the ID of the product to search for in the wishlists
     * @return a list of {@link Wishlist} entities that contain the specified product
     */
    @Query("SELECT w FROM Wishlist w " +
           "JOIN w.listWishlistProducts wp " +
           "JOIN wp.product p " +
           "WHERE p.id = :id")
    List<Wishlist> findWishlistsByProductId(@Param("id") Integer id);

    /**
     * Checks if any wishlist contains a product identified by its ID.
     *
     * @param id the ID of the product to check for in the wishlists
     * @return {@code true} if any wishlist contains the product, {@code false} otherwise
     */
    boolean existsByListWishlistProducts_Product_Id(Integer id);
}

