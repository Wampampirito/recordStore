package com.recordstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recordstore.model.Product;
import com.recordstore.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing products in the record store.
 * 
 * Provides endpoints for CRUD operations on products.
 * 
 * Endpoints:
 *  GET /products/all - Get all products
 *  GET /products/{id} - Get product by ID
 *  DELETE /products/{id} - Delete a product
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructs a new {@code ProductController} with the specified product
     * service.
     *
     * @param productService the service responsible for handling product-related
     *                       business logic.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Retrieves a list of all available products.
     *
     * @return a list containing all products in the store.
     */
    @GetMapping ("/all")
    @Operation(summary = "Get all products", description = "Retrieves a list of all products available in the store.")
    @ApiResponse(responseCode = "200", description = "List of products retrieved successfully")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Retrieves a product by its identifier.
     *
     * @param id the ID of the product to be retrieved.
     * @return an {@link Optional} containing the product if found, or empty if not
     *         found.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get product by ID", description = "Retrieves a product by its unique ID")
    @ApiResponse(responseCode = "200", description = "Product found")
    public Optional<Product> getProductById(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    /**
     * Deletes a product by its identifier.
     *
     * @param id the ID of the product to be deleted.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product", description = "Removes a product from the store.")
    @ApiResponse(responseCode = "204", description = "Product deleted successfully")
    public ResponseEntity <String>  deleteProduct(@PathVariable Integer id) {
                try {
            productService.deleteProduct(id);
            return ResponseEntity.ok("Product successfully deleted with ID: " + id);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the product.");
        }
    }
}
