package com.recordstore.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.recordstore.dto.OrderDTO;
import com.recordstore.model.Order;
/**
 * Componente que se encarga de mapear entre las entidades {@link com.recordstore.model.Order} y los objetos DTO {@link com.recordstore.dto.OrderDTO}.
 * Esta clase proporciona metodos para convertir una orden de tipo {@link com.recordstore.model.Order} a un {@link com.recordstore.dto.OrderDTO} y viceversa.
 * Ademas, ofrece un metodo para convertir una lista de ordenes a una lista de DTOs.
 * 
 * <p>Los metodos principales son:</p>
 * <ul>
 *   <li><b>toDTO</b>: Convierte una entidad {@link com.recordstore.model.Order} en un objeto {@link com.recordstore.dto.OrderDTO}.</li>
 *   <li><b>toEntity</b>: Convierte un objeto {@link com.recordstore.dto.OrderDTO} en una entidad {@link com.recordstore.model.Order}.</li>
 *   <li><b>toDTOList</b>: Convierte una lista de entidades {@link com.recordstore.model.Order} en una lista de objetos {@link com.recordstore.dto.OrderDTO}.</li>
 * </ul>
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * / Convertir una orden a DTO
 * OrderDTO orderDTO = orderMapper.toDTO(order);
 * 
 * /Convertir un DTO a entidad
 * Order orderEntity = orderMapper.toEntity(orderDTO);
 * </pre>
 */

@Component
public class OrderMapper {
    
    private final UserMapper userMapper;
    
    /**
     * Constructor para inyectar el userMapper
     * 
     * @param userMapper El mapeador de usuarios que convierte entre {@link com.recordstore.dto.UserDTO} y {@link com.recordstore.model.User}.
     */
    public OrderMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * Convierte una entidad {@link com.recordstore.order.Order} en un objeto {@link com.recordstore.order.OrderDTO}.
     * @param order La entidad {@link com.recordstore.order.Order} a convertir.
     * @return  El objeto {@link com.recordstore.order.OrderDTO} resultante.
     */

     /**
      * Convierte una entidad {@link com.recordstore.model.Order} en un objeto {@link com.recordstore.dto.OrderDTO}.
      * @param order La entidad {@link com.recordstore.model.Order} a convertir.
      * @return El objeto {@link com.recordstore.dto.OrderDTO} resultante.
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
     * Convierte un objeto {@link com.recordstore.dto.OrderDTO} en una entidad {@link com.recordstore.model.Order}.
     * @param dto El objeto {@link com.recordstore.dto.OrderDTO} a convertir.
     * @return La entidad {@link com.recordstore.model.Order} resultante.
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
     * Convierte una lista de entidades {@link com.recordstore.model.Order} en una lista de objetos {@link com.recordstore.dto.OrderDTO}.
     * 
     * @param orders La lista de entidades {@link com.recordstore.model.Order} a convertir.
     * @return La lista de objetos {@link com.recordstore.dto.OrderDTO} resultante.
     */
    public List<OrderDTO> toDTOList(List<Order> orders) {
        return orders.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
