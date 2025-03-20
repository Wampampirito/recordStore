package com.recordstore.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.recordstore.dto.UserDTO;
import com.recordstore.enums.ORDER_STATUS;
import com.recordstore.model.Order;
import com.recordstore.model.OrderProduct;
import com.recordstore.model.Product;
import com.recordstore.model.User;
import com.recordstore.repository.OrderRepository;
import com.recordstore.mapper.UserMapper;

/**
 * Service for managing orders in the record store.
 * Provides methods for creating, updating, and retrieving orders,
 * as well as calculating the total amount and generating tracking numbers.
 */
@Service
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final UserMapper userMapper;

    /**
     * Constructor with dependency injection for the order repository and user mapper.
     *
     * @param orderRepository Order repository.
     * @param userMapper User mapper for converting User entities to DTOs.
     */
    @Autowired
    public OrderService(OrderRepository orderRepository, UserMapper userMapper) {
        this.orderRepository = orderRepository;
        this.userMapper = userMapper;
    }

    // Creation methods

    /**
     * Creates a new order for a user with a list of products.
     *
     * @param user User placing the order.
     * @param orderProducts List of products in the order.
     * @return The created order saved in the database.
     */
    @Transactional
    public Order createOrder(User user, List<OrderProduct> orderProducts) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
    
        if (orderProducts == null || orderProducts.isEmpty()) {
            throw new IllegalArgumentException("The product list cannot be empty.");
        }

        Order order = new Order();
        order.setUser(user); // Assign user
        order.setStatus(ORDER_STATUS.PENDING); // Initial status
        order.setTrackingNumber(generateTrackingNumber(user)); // Generate tracking number
        
        orderRepository.save(order); // Save order before adding products
    
        // Associate products with the order
        for (OrderProduct orderProduct : orderProducts) {
            orderProduct.setOrder(order); // Assign order to each OrderProduct
            order.getListOrderProducts().add(orderProduct); // Add product to the order's product list
        }
    
        calculateTotal(order); // Recalculate the order total
        return orderRepository.save(order); // Save the order with the updated total
    }

    /**
     * Adds a product to an existing order.
     *
     * @param order Order to which the product will be added.
     * @param product Product to add.
     * @param quantity Quantity of the product.
     */
    @Transactional
    public void addProduct(Order order, Product product, int quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException("Product cannot be null and quantity must be greater than 0.");
        }

        OrderProduct newOrderProduct = new OrderProduct();
        newOrderProduct.setOrder(order);
        newOrderProduct.setProduct(product);
        newOrderProduct.setQuantity(quantity);
        order.getListOrderProducts().add(newOrderProduct);

        calculateTotal(order); // Update total after adding product
    }

    /**
     * Adds multiple products to an existing order.
     *
     * @param order Order to which the products will be added.
     * @param orderProducts List of products to add.
     */
    @Transactional
    public void addProducts(Order order, List<OrderProduct> orderProducts) {
        if (orderProducts == null || orderProducts.isEmpty()) {
            throw new IllegalArgumentException("The product list cannot be empty.");
        }

        for (OrderProduct orderProduct : orderProducts) {
            addProduct(order, orderProduct.getProduct(), orderProduct.getQuantity());
        }
    }

    // Update methods

    /**
     * Saves or updates an order in the database.
     *
     * @param order Order to be saved.
     * @return The saved order with the total calculated.
     */
    @Transactional
    public Order saveOrder(Order order) {
        if (order.getStatus() == null) {
            order.setStatus(ORDER_STATUS.PENDING); // Assign default status if null
        }
        if (order.getTrackingNumber() == null) {
            order.setTrackingNumber(generateTrackingNumber(order.getUser())); // Generate tracking number if null
        }

        // Verify if the order exists, if it does, update the total
        Optional<Order> orderWithProducts = orderRepository.findByIdWithProducts(order.getOrderId());
        if (orderWithProducts.isPresent()) {
            order = orderWithProducts.get();
            calculateTotal(order);
        }

        return orderRepository.save(order);
    }

    // Retrieval methods

    /**
     * Retrieves all stored orders.
     *
     * @return List of all orders.
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    /**
     * Retrieves an order by its ID.
     *
     * @param orderId Order ID.
     * @return Order corresponding to the ID, if it exists.
     */
    public Optional<Order> getOrderById(Integer orderId) {
        return orderRepository.findByOrderId(orderId);
    }

    /**
     * Retrieves the latest order for a given user.
     * This method fetches the most recent order placed by the user based on the order date.
     *
     * @param user The user whose latest order is to be retrieved.
     * @return An {@link Optional} containing the latest order if found, otherwise empty.
     */
    public Optional<Order> getLatestOrder(User user) {
        return orderRepository.findTopByUserOrderByOrderIdDesc(user);
    }

    /**
     * Retrieves all orders of a specific user.
     * This method fetches a list of all orders placed by the given user.
     *
     * @param user The user whose orders are to be retrieved.
     * @return A list of orders associated with the user.
     */
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }

    // Deletion methods

    /**
     * Deletes an order by its ID.
     *
     * @param id ID of the order to delete.
     */
    @Transactional
    public void deleteOrder(Integer id) {
        orderRepository.deleteById(id);
    }

    // Utility methods

    /**
     * Generates a tracking number for an order.
     *
     * Tracking number format: RCD-{userId}-{userPrefix}-{datePart}-{orderNumber}
     * <ul>
     *   <li>RCD = Record Store Delivery</li>
     *   <li>userId = User ID with 3 digits</li>
     *   <li>userPrefix = First 3 letters of the user's name in uppercase</li>
     *   <li>datePart = Date in ddMMyy format</li>
     *   <li>orderNumber = Incremental order number for the user</li>
     * </ul>
     *
     * @param user User for whom the tracking number is generated.
     * @return Generated tracking number.
     */
    public String generateTrackingNumber(User user) {
        // Usar el ID directamente como Integer y formatearlo como un número de 3 dígitos
        String userId = String.format("%03d", user.getId()); // ID con 3 dígitos
        String userPrefix = user.getName().substring(0, 3).toUpperCase(); // Primeras 3 letras del nombre
        String datePart = LocalDate.now().format(DateTimeFormatter.ofPattern("ddMMyy")); // Fecha en formato ddMMyy
        int orderCount = orderRepository.countByUser(user) + 1; // Número incremental
        String orderNumber = String.format("%03d", orderCount); // Formato de 3 dígitos
        
        return String.format("RCD-%s-%s-%s-%s", userId, userPrefix, datePart, orderNumber);
    }

    /**
     * Calculates the total amount of an order based on the products and their quantities.
     *
     * @param order Order whose total amount will be calculated.
     */
    @Transactional
    public void calculateTotal(Order order) {
        // Load the order products into a list
        List<OrderProduct> fetchedOrderProducts = orderRepository.findByListOrderProducts(order);

        // Update the product list
        order.getListOrderProducts().clear(); 
        order.getListOrderProducts().addAll(fetchedOrderProducts); 
      
        // Calculate the total
        Double total = 0.0;
        for (OrderProduct op : order.getListOrderProducts()) {
            total += op.getProduct().getPrice() * op.getQuantity();
        }
        // Round the total to two decimal places
        BigDecimal bd = new BigDecimal(total);
        total = bd.setScale(2, RoundingMode.HALF_UP).doubleValue();

        order.setTotalAmount(total);
    }

    /**
     * Converts the order's user to a UserDTO.
     *
     * @param order The order containing the user to be converted.
     * @return A UserDTO representing the order's user.
     */
    public UserDTO getUserDTOFromOrder(Order order) {
        return userMapper.toDTO(order.getUser());
    }
}
