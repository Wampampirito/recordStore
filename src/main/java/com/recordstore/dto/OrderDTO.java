package com.recordstore.dto;

import java.util.List;

import com.recordstore.model.OrderProduct;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) that represents an order in the system.
 * This DTO is used to transfer data related to an order between the layers of the application.
 * It contains relevant information about the order, including the associated user, the order's products, 
 * the order's status, and the total amount.
 * 
 * <p>The order fields include:</p>
 * <ul>
 *   <li><b>id</b>: The unique ID of the order.</li>
 *   <li><b>trackingNumber</b>: The tracking number of the order.</li>
 *   <li><b>user</b>: The user associated with the order, represented as a {@link com.recordstore.dto.UserDTO}.</li>
 *   <li><b>listOrderProducts</b>: The list of order products, represented as a list of {@link com.recordstore.model.OrderProduct}.</li>
 *   <li><b>status</b>: The current status of the order (e.g., "pending", "shipped", "delivered").</li>
 *   <li><b>totalAmount</b>: The total amount of the order.</li>
 * </ul>
 * 
 * <p>Example usage:</p>
 * <pre>
 * OrderDTO orderDTO = new OrderDTO();
 * orderDTO.setId(1L);
 * orderDTO.setTrackingNumber("ABC123");
 * orderDTO.setStatus("Pending");
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