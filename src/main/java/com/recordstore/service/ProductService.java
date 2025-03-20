package com.recordstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Product;
import com.recordstore.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing products within the system.
 * 
 * This service provides methods to retrieve, save, and delete products.
 * It also allows for retrieving products filtered by category.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructor for the service that injects the product repository.
     * 
     * @param productRepository The product repository to inject.
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Retrieves all products, including subclasses of
     * {@link com.recordstore.model.Product}.
     * 
     * @return A list of all products available in the system.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Retrieves a product by its ID (primary key).
     * 
     * @param id The ID of the product to retrieve.
     * @return An {@link java.util.Optional Optional} containing the found product,
     *         or empty if not found.
     */
    public Optional<Product> getProductById(Double id) {
        return productRepository.findById(id);
    }

    /**
     * Saves a product to the repository. If the product does not exist, a new one
     * is created.
     * 
     * @param product The product to save.
     * @return The saved product, with its assigned ID.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Deletes a product by its ID.
     * 
     * @param id The ID of the product to delete.
     */
    public void deleteProduct(Double id) {
        productRepository.deleteById(id);
    }

    /**
     * Retrieves products filtered by category.
     * 
     * @param productCategory The category of products to filter by.
     * @return A list of products matching the provided category.
     */
    public List<Product> getProductsByCategory(PRODUCT_CATEGORY productCategory) {
        return productRepository.findByProductCategory(productCategory);
    }

    /**
     * Retrieves products filtered by name (partial match).
     * 
     * @param name The product name (can be partial).
     * @return A list of products whose name contains the provided string.
     */
    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Retrieves products filtered by both name and category (partial match for
     * name).
     * 
     * @param name            The product name (can be partial).
     * @param productCategory The category of the product.
     * @return A list of products matching both criteria.
     */
    public List<Product> getProductsByNameAndCategory(String name, PRODUCT_CATEGORY productCategory) {
        return productRepository.findByNameContainingIgnoreCaseAndProductCategory(name, productCategory);
    }

    /**
     * Retrieves products filtered by price range.
     * 
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of products whose price is within the specified range.
     */
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Retrieves available products, i.e., those with a stock of 1 or more.
     * 
     * @return A list of products that have at least one unit in stock.
     */
    public List<Product> getAvailableProducts() {
        return productRepository.findByStockGreaterThanEqual(1);
    }
}
