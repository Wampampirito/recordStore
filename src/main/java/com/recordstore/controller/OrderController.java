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
 * REST Controller that manages operations related to purchase orders in the record store.
 * It provides endpoints for creating, retrieving, updating, and deleting orders, 
 * as well as adding products to existing orders.
 * 
 * <p>This controller interacts with the service {@link com.recordstore.service.OrderService} 
 * and the mapper {@link com.recordstore.mapper.OrderMapper} to handle business logic and data transformation.</p>
 * 
 * <p><b>Available endpoints:</b></p>
 * <ul>
 *   <li><b>GET /orders</b>: Retrieves all orders.</li>
 *   <li><b>GET /orders/{orderId}</b>: Retrieves an order by its ID.</li>
 *   <li><b>POST /orders/new</b>: Creates a new order.</li>
 *   <li><b>POST /orders/{orderId}/products</b>: Adds products to an existing order.</li>
 *   <li><b>DELETE /orders/delete/{orderId}</b>: Deletes an order by its ID.</li>
 *   <li><b>PUT /orders/update/{orderId}</b>: Updates an existing order.</li>
 *   <li><b>GET /orders/user/{userId}</b>: Retrieves all orders for a specific user.</li>
 *   <li><b>GET /orders/latest/{userId}</b>: Retrieves the latest order for a user.</li>
 * </ul>
 */
@RestController
@RequestMapping("/orders")
@Tag(name = "Orders", description = "Endpoints for managing orders")
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;
    private final UserService userService;

    /**
     * Constructs an OrderController with the required services and mapper.
     *
     * @param orderService the service handling order operations
     * @param orderMapper the mapper for converting between entity and DTO
     * @param userService the service handling user operations
     */
    public OrderController(OrderService orderService, OrderMapper orderMapper, UserService userService) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
        this.userService = userService;
    }

    /**
     * Retrieves all orders.
     *
     * @return a list of all orders in DTO format
     */
    @Operation(summary = "Retrieve all orders", description = "Returns a list of all orders.")
    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return ResponseEntity.ok(orderMapper.toDTOList(orders));
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId the ID of the order to retrieve
     * @return the order in DTO format, or 404 if not found
     */
    @Operation(summary = "Retrieve an order by ID", description = "Returns an order based on its ID.")
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId)
                .map(order -> ResponseEntity.ok(orderMapper.toDTO(order)))
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Creates a new order.
     *
     * @param orderDTO the order details in DTO format
     * @return the created order in DTO format
     */
    @Operation(summary = "Create a new order", description = "Creates and returns a new order.")
    @PostMapping("/new")
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDTO) {
        Order order = orderMapper.toEntity(orderDTO);
        Order createdOrder = orderService.saveOrder(order.getUser(), order.getListOrderProducts());
        return ResponseEntity.ok(orderMapper.toDTO(createdOrder));
    }

    /**
     * Adds products to an existing order.
     *
     * @param orderId the ID of the order to update
     * @param orderProducts the list of products to add
     * @return the updated order in DTO format, or 404 if the order is not found
     */
    @Operation(summary = "Add products to an existing order", description = "Adds products to an existing order by ID.")
    @PostMapping("/{orderId}/products")
    public ResponseEntity<OrderDTO> addProductsToOrder(@PathVariable Integer orderId, @RequestBody List<OrderProduct> orderProducts) {
        return orderService.getOrderById(orderId)
                .map(order -> {
                    orderService.addProducts(order, orderProducts);
                    return ResponseEntity.ok(orderMapper.toDTO(order));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Deletes an order by its ID.
     *
     * @param orderId the ID of the order to delete
     * @return a 204 No Content response if successful
     */
    @Operation(summary = "Delete an order", description = "Deletes an order by ID.")
    @DeleteMapping("delete/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Integer orderId) {
        orderService.deleteOrder(orderId);
        return ResponseEntity.noContent().build();
    }

    /**
     * Retrieves all orders for a specific user.
     *
     * @param userId the ID of the user whose orders are to be retrieved
     * @return a list of orders in DTO format, or 404 if the user is not found
     */
    @Operation(summary = "Retrieve orders by user", description = "Returns all orders for a given user ID.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<OrderDTO>> getOrdersByUser(@PathVariable Integer userId) {
        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Order> orders = orderService.getOrdersByUser(user);
            return ResponseEntity.ok(orderMapper.toDTOList(orders));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves the latest order for a specific user.
     *
     * @param userId the ID of the user whose latest order is to be retrieved
     * @return the latest order in DTO format, or 404 if no orders exist for the user
     */
    @Operation(summary = "Retrieve the latest order for a user", description = "Returns the most recent order for a given user ID.")
    @GetMapping("/latest/{userId}")
    public ResponseEntity<OrderDTO> getLatestOrder(@PathVariable Integer userId) {
        return orderService.getLatestOrder(userId)
                .map(orderMapper::toDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing order by its ID.
     *
     * @param orderId the ID of the order to update
     * @param orderDTO the updated order details in DTO format
     * @return the updated order in DTO format, or 404 if the order is not found
     */
    @Operation(summary = "Update an order", description = "Updates an existing order by ID.")
    @PutMapping("/update/{orderId}")
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
