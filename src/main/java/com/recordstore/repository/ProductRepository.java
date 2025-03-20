package com.recordstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Product;

/**
 * Repositorio para acceder a las entidades {@link com.recordstore.model.Product}.
 * <p>
 * Este repositorio extiende de {@link org.springframework.data.jpa.repository.JpaRepository}, lo que proporciona operaciones CRUD basicas.
 * Ademas, se han implementado metodos adicionales para filtrar productos por diferentes criterios como categoria, nombre,
 * precio, stock y mas.
 * </p>
 */
public interface ProductRepository extends JpaRepository<Product, Double> {

    /**
     * Busca productos por categoria.
     * 
     * @param productCategory La categoria del producto por la cual filtrar.
     * @return Lista de productos que coinciden con la categoria especificada.
     */
    List<Product> findByProductCategory(PRODUCT_CATEGORY productCategory);

    /**
     * Busca productos por nombre (de forma parcial).
     * 
     * @param name El nombre del producto (puede ser parcial).
     * @return Lista de productos cuyo nombre contenga la cadena proporcionada.
     */
    List<Product> findByNameContainingIgnoreCase(String name);

    /**
     * Busca productos dentro de un rango de precios.
     * 
     * @param minPrice El precio minimo.
     * @param maxPrice El precio maximo.
     * @return Lista de productos cuyo precio este dentro del rango especificado.
     */
    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    /**
     * Busca productos con una cantidad de stock mayor o igual a 1.
     * 
     * @param stock La cantidad minima de stock.
     * @return Lista de productos que tienen al menos 1 unidad en stock.
     */
    List<Product> findByStockGreaterThanEqual(Integer stock);

    /**
     * Busca productos por nombre y categoria.
     * 
     * @param name El nombre del producto (puede ser parcial).
     * @param productCategory La categoria del producto.
     * @return Lista de productos que coinciden con ambos criterios.
     */
    List<Product> findByNameContainingIgnoreCaseAndProductCategory(String name, PRODUCT_CATEGORY productCategory);

    /**
     * Busca productos que pertenezcan a alguna de las categorias especificadas.
     * 
     * @param productCategories Lista de categorias de productos.
     * @return Lista de productos que pertenecen a alguna de las categorias proporcionadas.
     */
    List<Product> findByProductCategoryIn(List<PRODUCT_CATEGORY> productCategories);

    /**
     * Cuenta los productos que pertenecen a una categoria especifica.
     * 
     * @param productCategory La categoria de los productos que se contaran.
     * @return El numero de productos que pertenecen a la categoria especificada.
     */
    Double countByProductCategory(PRODUCT_CATEGORY productCategory);

    /**
     * Elimina todos los productos de una categoria especifica.
     * 
     * @param productCategory La categoria de los productos que se eliminaran.
     */
    void deleteByProductCategory(PRODUCT_CATEGORY productCategory);

    Optional<Product> findById(Integer id);
   // Optional<Wishlist> findWishlistById(Integer productId);
}
