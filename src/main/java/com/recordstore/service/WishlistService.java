package com.recordstore.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recordstore.dto.WishlistDTO;
import com.recordstore.model.Product;
import com.recordstore.model.Wishlist;
import com.recordstore.model.WishlistProduct;
import com.recordstore.repository.ProductRepository;
import com.recordstore.repository.WishlistRepository;

import jakarta.transaction.Transactional;
/**
 * Servicio para gestionar wishlists en el sistema.
 * 
 * Este servicio proporciona metodos para obtener, guardar y eliminar wishlists.
 * 
 */
@Service // Asegúrate de que la clase es reconocida como un servicio
public class WishlistService {
    
    // Inyección de dependencias para los repositorios necesarios
    @Autowired
    private WishlistRepository wishlistRepository;  // Para acceder a la entidad Wishlist
    @Autowired
    private ProductRepository productRepository;    // Para acceder a la entidad Product

    /**
     *  Metodo para obtener una wishlist por su id de usuario
     * @param userId

     */

     public WishlistDTO getWishlistByUserId(Integer userId) {

        return wishlistRepository.getWishlistByUserId(userId);

     }


    //TODO Revisar funcionamiento de addProductToWishlist y removeProductFromWishlist

    /**
     * Método para agregar un producto a una wishlist.
     * 
     * @param wishlistId El id de la wishlist a la que se agregará el producto.
     * @param productId El id del producto que se agregará a la wishlist.
     */
    @Transactional
    public void addProductToWishlist(Integer wishlistId, Integer productId) {
        
        // Obtener la wishlist y el producto de la base de datos mediante sus IDs
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist no encontrada"));  // Lanzar una excepción si no se encuentra la wishlist
        
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado")); // Lanzar una excepción si no se encuentra el producto
        
        // Crear un nuevo objeto de la clase intermedia WishlistProduct
        WishlistProduct wishlistProduct = new WishlistProduct();  
        
        // Asignar la wishlist y el producto a la clase intermedia WishlistProduct
        wishlistProduct.setWishlist(wishlist);
        wishlistProduct.setProduct(product);
        
        // Agregar el nuevo producto a la lista de productos de la wishlist
        wishlist.getListWishlistProducts().add(wishlistProduct);
        
        // Guardar la wishlist actualizada con el nuevo producto en la base de datos
        wishlistRepository.save(wishlist);
    }

    /**
     * Método para eliminar un producto de una wishlist.
     * 
     * @param wishlistId El id de la wishlist a la que se elimina el producto.
     * @param productId El id del producto que se elimina de la wishlist.
     */
    public void removeProductFromWishlist(Double wishlistId, Double productId) {
        // Obtener la wishlist de la base de datos mediante su ID
        Wishlist wishlist = wishlistRepository.findById(wishlistId)
                .orElseThrow(() -> new RuntimeException("Wishlist no encontrada"));  // Lanzar una excepción si no se encuentra la wishlist
        
        // Eliminar el producto de la wishlist mediante su ID
        wishlist.getListWishlistProducts().removeIf(wishlistProduct -> wishlistProduct.getProduct().getId().equals(productId.intValue()));
        
        // Guardar la wishlist actualizada en la base de datos
        wishlistRepository.save(wishlist);
    }
}
