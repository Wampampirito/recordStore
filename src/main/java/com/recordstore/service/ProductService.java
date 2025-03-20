package com.recordstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Product;
import com.recordstore.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

/**
 * Servicio para gestionar productos en el sistema.
 * 
 * Este servicio proporciona metodos para obtener, guardar y eliminar productos.
 * Tambien permite obtener productos filtrados por categoria.
 */
@Service
public class ProductService {

    private final ProductRepository productRepository;

    /**
     * Constructor del servicio que inyecta el repositorio de productos.
     * 
     * @param productRepository El repositorio de productos a inyectar.
     */
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Obtiene todos los productos, incluyendo las subclases de {@link com.recordstore.model.Product}.
     * 
     * @return Lista de todos los productos disponibles en el sistema.
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Obtiene un producto por su id (clave primaria).
     * 
     * @param id El id del producto a buscar.
     * @return Un {@link java.util.Optional Optional}con el producto encontrado, o vacio si no se encuentra.
     */
    public Optional<Product> getProductById(Double id) {
        return productRepository.findById(id);
    }

    /**
     * Guarda un producto en el repositorio. Si el producto no existe, se crea uno nuevo.
     * 
     * @param product El producto a guardar.
     * @return El producto guardado, con su id asignado.
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Elimina un producto por su id.
     * 
     * @param id El id del producto a eliminar.
     */
    public void deleteProduct(Double id) {
        productRepository.deleteById(id);
    }

    /**
     * Obtiene productos filtrados por categoria.
     * 
     * @param productCategory La categoria de los productos a filtrar.
     * @return Lista de productos que coinciden con la categoria proporcionada.
     */
    public List<Product> getProductsByCategory(PRODUCT_CATEGORY productCategory) {
        return productRepository.findByProductCategory(productCategory);
    }

    /**
     * Obtiene productos filtrados por nombre (de forma parcial).
     * 
     * @param name El nombre del producto (puede ser parcial).
     * @return Lista de productos cuyo nombre contenga la cadena proporcionada.
     */
    public List<Product> getProductsByName(String name) {
        return productRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Obtiene productos filtrados por categoria y nombre (de forma parcial).
     * 
     * @param name El nombre del producto (puede ser parcial).
     * @param productCategory La categoria del producto.
     * @return Lista de productos que coinciden con ambos criterios.
     */
    public List<Product> getProductsByNameAndCategory(String name, PRODUCT_CATEGORY productCategory) {
        return productRepository.findByNameContainingIgnoreCaseAndProductCategory(name, productCategory);
    }

    /**
     * Obtiene productos filtrados por precio dentro de un rango.
     * 
     * @param minPrice El precio minimo.
     * @param maxPrice El precio.maxcdn.
     * @return Lista de productos cuyo precio este dentro del rango especificado.
     */
    public List<Product> getProductsByPriceRange(Double minPrice, Double maxPrice) {
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Obtiene productos dsiponibles, es decir filtrados por stock mayor o igual a 1.
     * 
     * @return Lista de productos que tienen al menos 1 unidad en stock.
     */
    public List<Product> getAvailibleProducts() {
        return productRepository.findByStockGreaterThanEqual(1);
    }


}
