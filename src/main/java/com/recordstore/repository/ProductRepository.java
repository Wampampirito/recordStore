package com.recordstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Product;

/**
 * Repository for accessing {@link com.recordstore.model.Product} entities.
 * <p>
 * This repository extends {@link org.springframework.data.jpa.repository.JpaRepository}, which provides basic CRUD operations.
 * Additionally, extra methods have been implemented to filter products by various criteria such as category, name,
 * price, stock, and more.
 * </p>
 */
public interface ProductRepository extends JpaRepository<Product, Integer> {

    /**
     * Finds products by category.
     * 
     * @param productCategory The category of the product to filter by.
     * @return A list of products that match the specified category.
     */
    List<Product> findByProductCategory(PRODUCT_CATEGORY productCategory);

    /**
     * Finds products by name (partially).
     * 
     * @param name The name of the product (can be partial).
     * @return A list of products whose name contains the provided string.
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Finds products within a price range.
     * 
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of products whose price is within the specified range.
     */
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    /**
     * Finds products with a stock quantity greater than or equal to 1.
     * 
     * @param stock The minimum stock quantity.
     * @return A list of products that have at least 1 unit in stock.
     */
    List<Product> findByStockGreaterThanEqual(Integer stock);

    /**
     * Finds products by name and category.
     * 
     * @param name The name of the product (can be partial).
     * @param productCategory The category of the product.
     * @return A list of products that match both criteria.
     */
    List<Product> findByNameContainingIgnoreCaseAndProductCategory(String name, PRODUCT_CATEGORY productCategory);

    /**
     * Finds products that belong to any of the specified categories.
     * 
     * @param productCategories A list of product categories.
     * @return A list of products that belong to any of the provided categories.
     */
    List<Product> findByProductCategoryIn(List<PRODUCT_CATEGORY> productCategories);

    /**
     * Counts the products that belong to a specific category.
     * 
     * @param productCategory The category of the products to count.
     * @return The number of products that belong to the specified category.
     */
    Double countByProductCategory(PRODUCT_CATEGORY productCategory);

    /**
     * Deletes all products from a specific category.
     * 
     * @param productCategory The category of the products to delete.
     */
    void deleteByProductCategory(PRODUCT_CATEGORY productCategory);

}
