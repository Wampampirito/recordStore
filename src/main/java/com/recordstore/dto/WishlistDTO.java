package com.recordstore.dto;

import java.util.List;

import com.recordstore.model.Wishlist;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO (Data Transfer Object) que representa la lista de deseos de un usuario para la transferencia de datos.
 * 
 * La clase {@link WishlistDTO} es utilizada para enviar los datos de la lista de deseos de un usuario a través de la 
 * capa de presentación. Incluye el identificador de la lista de deseos, la información del usuario asociada (representada 
 * por un {@link UserDTO}) y la lista de productos (representada por una lista de {@link BasicProductDTO}) que se encuentran 
 * en la lista de deseos.
 * 
 * Esta clase es útil para evitar la exposición directa de la entidad {@link Wishlist} en la API, promoviendo una arquitectura 
 * limpia y eficiente. Los datos que se envían en el cuerpo de las respuestas REST se organizan utilizando este DTO.
 * 
 * Ejemplo de uso:
 * <pre>
 * WishlistDTO wishlistDTO = new WishlistDTO(wishlistId, userDTO, productDTOs);
 * </pre>
 */
@Data
@NoArgsConstructor
public class WishlistDTO {

    private int wishlistId;
    private UserDTO user;
    private List<BasicProductDTO> products;

    /**
     * Constructor para crear un nuevo DTO de lista de deseos con los datos proporcionados.
     * 
     * Este constructor inicializa un objeto {@link WishlistDTO} con el identificador de la lista de deseos, la informacion 
     * del usuario y los productos correspondientes.
     * 
     * @param wishlistId El identificador unico de la lista de deseos.
     * @param userDTO El DTO del usuario asociado a la lista de deseos.
     * @param productDTOs La lista de productos en la lista de deseos.
     */
    public WishlistDTO(int wishlistId, UserDTO userDTO, List<BasicProductDTO> productDTOs) {
        this.wishlistId = wishlistId;
        this.user = userDTO;
        this.products = productDTOs;
    }
}
