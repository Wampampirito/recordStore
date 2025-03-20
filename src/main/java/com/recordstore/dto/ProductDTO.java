package com.recordstore.dto;

import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Product;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Abstract DTO (Data Transfer Object) class to represent products.
 * <p>
 * Provides a common structure for transferring product data,
 * avoiding direct exposure of the {@link com.recordstore.model.Product} entity.
 * </p>
 *
 * @author Ivan Egued
 */

@Data
@NoArgsConstructor
public abstract class ProductDTO {

    /**
     * Identificador unico del producto (id).
     */
    private Integer id;

    /**
     * Nombre del producto.
     */
    private String name;

    /**
     * Precio del producto.
     */
    private Double price;

    /**
     * Cantidad disponible en stock.
     */
    private Integer stock;

    /**
     * Categoria del producto.
     */
    private PRODUCT_CATEGORY productCategory;

    /**
     * Constructor que inicializa un {@code ProductDTO} a partir de un
     * {@link com.recordstore.model.Product}.
     *
     * @param product la entidad {@code Product} de la cual se obtienen los datos.
     */
    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.stock = product.getStock();
        this.productCategory = product.getProductCategory();
    }

}
