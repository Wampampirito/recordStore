package com.recordstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recordstore.dto.OrderDTO;

import com.recordstore.mapper.OrderMapper;
import com.recordstore.model.Order;
import com.recordstore.model.OrderProduct;
import com.recordstore.model.User;
import com.recordstore.service.OrderService;
import com.recordstore.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST Controller that handles operations related to purchase orders in the record store.
 * Provides endpoints to create, retrieve, update, and delete orders, as well as add products to orders.
 * This controller interacts with the service {@link com.recordstore.service.OrderService} and the mapper {@link com.recordstore.mapper.OrderMapper} to perform business operations.
 * 
 * <p>Available endpoints:</p>
 * <ul>
 *   <li><b>GET /orders</b>: Retrieves all orders.</li>
 *   <li><b>GET /orders/{id}</b>: Retrieves an order by its ID.</li>
 *   <li><b>POST /orders</b>: Creates a new order.</li>
 *   <li><b>POST /orders/{id}/products</b>: Adds products to an existing order.</li>
 *   <li><b>DELETE /orders/{id}</b>: Deletes an order by its ID.</li>
 *   <li><b>PUT /orders/{id}</b>: Updates an existing order.</li>
 *   <li><b>GET /orders/user/{userId}</b>: Retrieves all orders for a user.</li>
 *   <li><b>GET /orders/user/{userId}/latest</b>: Retrieves the latest order for a user.</li>
 * </ul>
 */
@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Endpoints for managing orders")
public class OrderController {
    
    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UserService userService;



    public OrderController(OrderService orderService, OrderMapper orderMapper, UserService userService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.userService = userService;
    
    }

    @Operation(summary = "Retrieve all orders", description = "Returns a list of all orders.")
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.toDTOList(orders));
    }

    @Operation(summary = "Retrieve an order by ID", description = "Returns an order based on its ID.")
    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId)
                .map(order -> ResponseEntity.ok(orderMapper.toDTO(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new order", description = "Creates and returns a new order.")
    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        Order createdOrder = orderService.createOrder(order.getUser(), order.getListOrderProducts());
        return ResponseEntity.ok(orderMapper.toDTO(createdOrder));
    }
    
    @Operation(summary = "Add products to an existing order", description = "Adds products to an existing order by ID.")
    @PostMapping("/{id}/products")
    public ResponseEntity<OrderDTO> addProductsToOrder(@PathVariable Integer orderId, @RequestBody List<OrderProduct> orderProducts) {
        return orderService.getOrderById(orderId)
                .map(order -> {
                    orderService.addProducts(order, orderProducts);
                    return ResponseEntity.ok(orderMapper.toDTO(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an order", description = "Deletes an order by ID.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    //TODO PRobar getOrdersByUser y getLatestOrder

    @Operation(summary = "Retrieve orders by user", description = "Returns all orders for a given user ID.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Double userId) {
        Optional<User> userOptional = userService.getUserById(userId); // Obtener el User desde la base de datos
        if (userOptional.isPresent()) {
            User user = userOptional.get(); // User gestionado por Hibernate
            List<Order> orders = orderService.getOrdersByUser(user);
            return ResponseEntity.ok(orderMapper.toDTOList(orders));
        } else {
            return ResponseEntity.notFound().build(); // User no encontrado
        }
    }
    
    

    @Operation(summary = "Retrieve the latest order for a user", description = "Returns the most recent order for a given user ID.")
    @GetMapping("/user/{userId}/latest")
    public ResponseEntity<OrderDTO> getLatestOrder(@PathVariable Double userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found")); 
        return orderService.getLatestOrder(user)
                .map(order -> ResponseEntity.ok(orderMapper.toDTO(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Update an order", description = "Updates an existing order by ID.")
    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Integer orderId, @RequestBody OrderDTO orderDTO) {
        if (!orderService.getOrderById(orderId).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        Order order = orderMapper.toEntity(orderDTO);
        order.setOrderId(orderId); 
        Order updatedOrder = orderService.saveOrder(order);
        
        return ResponseEntity.ok(orderMapper.toDTO(updatedOrder));
    }
}