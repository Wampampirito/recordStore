package com.recordstore.dto;

import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract Data Transfer Object (DTO) class to represent products.
 * <p>
 * Provides a common structure for transferring product data, ensuring that the {@link com.recordstore.model.Product} 
 * entity is not directly exposed to other layers of the application.
 * </p>
 * 
 * This class serves as a base for other product-related DTOs, providing common fields like the product's
 * ID, name, price, stock, and category.
 * 
 * Example of use:
 * <pre>
 * ProductDTO productDTO = new ProductDTO(product);
 * </pre>
 * 
 * @author Ivan Egued
 */
@Data
@NoArgsConstructor
public abstract class ProductDTO {

    private Integer id;
    private String name;
    private Double price;
    private Integer stock;
    private PRODUCT_CATEGORY productCategory;

    /**
     * Constructor that initializes a {@code ProductDTO} from a {@link com.recordstore.model.Product} entity.
     *
     * @param product the {@code Product} entity from which the data is extracted.
     */
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.productCategory = product.getProductCategory();
    }
}
