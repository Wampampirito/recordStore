package com.recordstore.dto;

import java.util.List;

import com.recordstore.model.OrderProduct;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) que representa una orden en el sistema.
 * Este DTO se utiliza para transferir datos relacionados con una orden entre las capas de la aplicacion.
 * Contiene la informacion relevante de la orden, incluyendo el usuario asociado, los productos de la orden, 
 * el estado de la orden y el monto total.
 * 
 * <p>Los campos de la orden incluyen:</p>
 * <ul>
 *   <li><b>id</b>: El ID unico de la orden.</li>
 *   <li><b>trackingNumber</b>: El numero de seguimiento de la orden.</li>
 *   <li><b>user</b>: El usuario asociado con la orden, representado como un {@link com.recordstore.dto.UserDTO}.</li>
 *   <li><b>listOrderProducts</b>: La lista de productos de la orden, representada como una lista de {@link com.recordstore.model.OrderProduct}.</li>
 *   <li><b>status</b>: El estado actual de la orden (por ejemplo, "pendiente", "enviado", "entregado").</li>
 *   <li><b>totalAmount</b>: El monto total de la orden.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * OrderDTO orderDTO = new OrderDTO();
 * orderDTO.setId(1L);
 * orderDTO.setTrackingNumber("ABC123");
 * orderDTO.setStatus("Pendiente");
 * orderDTO.setTotalAmount(150.50);
 * </pre>
 */
 
@Data
@NoArgsConstructor
public class OrderDTO {
    private Integer orderId;
    private String trackingNumber;
    private UserDTO user;
    private List<OrderProduct> listOrderProducts;
    private String status;
    private Double totalAmount;
}