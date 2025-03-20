package com.recordstore.dto;

import com.recordstore.model.Product;

/**
 * Implementacion concreta de {@link com.recordstore.dto.ProductDTO} para representar un producto
 * basico.
 * <p>
 * Dado que {@link com.recordstore.dto.ProductDTO} es abstracta, esta clase proporciona una
 * implementacion
 * concreta basada en la entidad {@link com.recordstore.model.Product}.
 * </p>
 *
 * @author Ivan Egued
 */

public class BasicProductDTO extends ProductDTO {

    /**
     * Constructor que inicializa un {@code BasicProductDTO} a partir de un
     * {@link com.recordstore.model.Product}.
     *
     * @param product el producto del cual se genera el DTO.
     */
    public BasicProductDTO(Product product) {
        super(product);
    }
}