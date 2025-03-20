package com.recordstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.recordstore.dto.OrderDTO;
import com.recordstore.model.Order;

/**
 * Component responsible for mapping between {@link com.recordstore.model.Order} entities and 
 * {@link com.recordstore.dto.OrderDTO} objects.
 * This class provides methods to convert an {@link com.recordstore.model.Order} entity to an 
 * {@link com.recordstore.dto.OrderDTO} and vice versa. Additionally, it offers a method to convert 
 * a list of orders into a list of DTOs.
 * 
 * <p>Main methods include:</p>
 * <ul>
 *   <li><b>toDTO</b>: Converts an {@link com.recordstore.model.Order} entity to an {@link com.recordstore.dto.OrderDTO} object.</li>
 *   <li><b>toEntity</b>: Converts an {@link com.recordstore.dto.OrderDTO} object to an {@link com.recordstore.model.Order} entity.</li>
 *   <li><b>toDTOList</b>: Converts a list of {@link com.recordstore.model.Order} entities into a list of {@link com.recordstore.dto.OrderDTO} objects.</li>
 * </ul>
 * 
 * <p>Usage example:</p>
 * <pre>
 * // Convert an order to DTO
 * OrderDTO orderDTO = orderMapper.toDTO(order);
 * 
 * // Convert a DTO to entity
 * Order orderEntity = orderMapper.toEntity(orderDTO);
 * </pre>
 */
@Component
public class OrderMapper {
    
    private final UserMapper userMapper;
    
    /**
     * Constructor to inject the userMapper.
     * 
     * @param userMapper The user mapper that converts between {@link com.recordstore.dto.UserDTO} and {@link com.recordstore.model.User}.
     */
    public OrderMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Converts an {@link com.recordstore.model.Order} entity to an {@link com.recordstore.dto.OrderDTO}.
     * 
     * @param order The {@link com.recordstore.model.Order} entity to be converted.
     * @return The resulting {@link com.recordstore.dto.OrderDTO} object.
     */
    public OrderDTO toDTO(Order order) {
        if (order == null) {
            return null;
        }
        
        OrderDTO dto = new OrderDTO();
        dto.setOrderId(order.getOrderId());
        dto.setTrackingNumber(order.getTrackingNumber());
        dto.setUser(userMapper.toDTO(order.getUser()));
        dto.setListOrderProducts(order.getListOrderProducts()); 
        dto.setStatus(order.getStatus().name());
        dto.setTotalAmount(order.getTotalAmount());
        
        return dto;
    }

    /**
     * Converts an {@link com.recordstore.dto.OrderDTO} object to an {@link com.recordstore.model.Order} entity.
     * 
     * @param dto The {@link com.recordstore.dto.OrderDTO} object to be converted.
     * @return The resulting {@link com.recordstore.model.Order} entity.
     */
    public Order toEntity(OrderDTO dto) {
        if (dto == null) {
            return null;
        }
        
        Order order = new Order();
        order.setOrderId(dto.getOrderId());
        order.setTrackingNumber(dto.getTrackingNumber());
        order.setUser(userMapper.toEntity(dto.getUser()));
        order.setListOrderProducts(dto.getListOrderProducts()); 
        order.setTotalAmount(dto.getTotalAmount());
        
        return order;
    }

    /**
     * Converts a list of {@link com.recordstore.model.Order} entities to a list of {@link com.recordstore.dto.OrderDTO} objects.
     * 
     * @param orders The list of {@link com.recordstore.model.Order} entities to be converted.
     * @return The resulting list of {@link com.recordstore.dto.OrderDTO} objects.
     */
    public List<OrderDTO> toDTOList(List<Order> orders) {
        return orders.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
