package com.recordstore.dto;

import com.recordstore.model.Product;

/**
 * Concrete implementation of {@link com.recordstore.dto.ProductDTO} to represent a basic product.
 * <p>
 * Since {@link com.recordstore.dto.ProductDTO} is abstract, this class provides a
 * concrete implementation based on the entity {@link com.recordstore.model.Product}.
 * </p>
 *
 * @author Ivan Egued
 */
public class BasicProductDTO extends ProductDTO {

    /**
     * Constructor that initializes a {@code BasicProductDTO} from a
     * {@link com.recordstore.model.Product}.
     *
     * @param product the product from which the DTO is generated.
     */
    public BasicProductDTO(Product product) {
        super(product);
    }
}
