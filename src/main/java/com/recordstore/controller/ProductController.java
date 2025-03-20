package com.recordstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.recordstore.model.Product;
import com.recordstore.service.ProductService;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar productos en la tienda de discos.
 * <p>
 * Proporciona endpoints para operaciones CRUD sobre productos.
 * </p>
 *
 * @author Ivan Egued
 */
@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    /**
     * Constructor que inyecta el servicio de productos.
     *
     * @param productService el servicio que maneja la logica de negocio de los
     *                       productos.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Obtiene la lista de todos los productos disponibles.
     *
     * @return una lista de productos.
     */
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Obtiene un producto por su identificador.
     *
     * @param id el ID del producto a buscar.
     * @return un {@link java.util.Optional Optional} con el producto encontrado o vacio si no existe.
     */
    @GetMapping("/{id}")
    public Optional<Product> getProductById(@PathVariable Double id) {
        return productService.getProductById(id);
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     *
     * @param product el producto a guardar.
     * @return el producto guardado.
     */
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    /**
     * Elimina un producto por su identificador.
     *
     * @param id el ID del producto a eliminar.
     */
    @DeleteMapping("delete/{id}")
    public void deleteProduct(@PathVariable Double id) {
        productService.deleteProduct(id);
    }
}
