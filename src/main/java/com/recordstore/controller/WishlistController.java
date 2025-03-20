package com.recordstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.recordstore.dto.WishlistDTO;
import com.recordstore.service.WishlistService;

/**
 * Controlador que gestiona las operaciones relacionadas con la lista de deseos de un usuario.
 * 
 * La clase {@link WishlistController} expone varios puntos finales de la API REST que permiten a los usuarios 
 * interactuar con su lista de deseos. Estos incluyen obtener la lista de deseos, agregar productos y eliminar productos 
 * de la lista. La clase hace uso de la capa de servicio {@link WishlistService} para ejecutar las operaciones necesarias 
 * sobre los datos.
 * 
 * Los endpoints definidos son:
 * <ul>
 *   <li><b>GET /{userId}</b>: Obtiene la lista de deseos asociada a un usuario.</li>
 *   <li><b>POST /{userId}/product/{productId}</b>: Agrega un producto a la lista de deseos del usuario.</li>
 *   <li><b>DELETE /{userId}/product/{productId}</b>: Elimina un producto de la lista de deseos del usuario.</li>
 * </ul>
 * 
 * Los códigos de respuesta son:
 * <ul>
 *   <li><b>200 OK</b>: Cuando se obtiene con éxito la lista de deseos.</li>
 *   <li><b>201 Created</b>: Cuando un producto es agregado con éxito a la lista.</li>
 *   <li><b>204 No Content</b>: Cuando un producto es eliminado con éxito de la lista.</li>
 * </ul>
 */
@RestController
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;

    /**
     * Obtiene la lista de deseos de un usuario basado en su identificador.
     * 
     * Este método se invoca cuando se realiza una solicitud GET a la URL `/{userId}`. Recupera la lista de deseos 
     * asociada al usuario correspondiente y devuelve la informacion en formato {@link WishlistDTO}.
     * 
     * @param userId El identificador del usuario cuyo listado de deseos se desea obtener.
     * @return La lista de deseos del usuario en formato {@link WishlistDTO}.
     */
    @GetMapping("/{userId}")
    public ResponseEntity<WishlistDTO> getWishlistByUserId(@PathVariable Integer userId) {
        WishlistDTO wishlistDTO = wishlistService.getWishlistByUserId(userId);
        return ResponseEntity.ok(wishlistDTO);
    }

    /**
     * Agrega un producto a la lista de deseos de un usuario.
     * 
     * Este método se invoca cuando se realiza una solicitud POST a la URL `/{userId}/product/{productId}`. 
     * Agrega un producto a la lista de deseos del usuario especificado.
     * 
     * @param userId El identificador del usuario al que se le agregara el producto.
     * @param productId El identificador del producto que se agregara a la lista de deseos.
     * @return Una respuesta con el código de estado 201 para indicar que el producto fue creado exitosamente en la lista.
     */
    @PostMapping("/{userId}/product/{productId}")
    public ResponseEntity<Void> addProductToWishlist(@PathVariable Integer userId, @PathVariable Integer productId) {
        wishlistService.addProductToWishlist(userId, productId);
        return ResponseEntity.status(HttpStatus.CREATED).build(); // Codigo 201 para indicar que se creo el recurso
    }

    /**
     * Elimina un producto de la lista de deseos de un usuario.
     * 
     * Este método se invoca cuando se realiza una solicitud DELETE a la URL `/{userId}/product/{productId}`. 
     * Elimina un producto de la lista de deseos del usuario especificado.
     * 
     * @param userId El identificador del usuario cuyo producto sera eliminado de la lista de deseos.
     * @param productId El identificador del producto que se eliminara de la lista de deseos.
     * @return Una respuesta con el código de estado 204 para indicar que la eliminación fue exitosa.
     */
    @DeleteMapping("/{userId}/product/{productId}")
    public ResponseEntity<Void> removeProductFromWishlist(@PathVariable Double userId, @PathVariable Double productId) {
        wishlistService.removeProductFromWishlist(userId, productId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Codigo 204 para indicar que la eliminacion fue exitosa
    }
}