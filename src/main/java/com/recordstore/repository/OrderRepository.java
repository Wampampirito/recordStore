package com.recordstore.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.recordstore.model.Order;
import com.recordstore.model.OrderProduct;
import com.recordstore.model.User;

/**
 * Repository that handles data access operations for the {@link com.recordstore.model.Order} entity.
 * Extends {@link JpaRepository} to provide basic CRUD operations as well as custom queries.
 * 
 * <p>The methods defined in this interface allow:</p>
 * <ul>
 *   <li><b>Count orders by user:</b> {@link #countByUser(User) countByUser}.</li>
 *   <li><b>Find an order with its associated products:</b> {@link #findByIdWithProducts(Integer) findByIdWithProducts}.</li>
 *   <li><b>Find products associated with an order:</b> {@link #findByListOrderProducts(Order) findByListOrderProducts}.</li>
 * </ul>
 * 
 * <p>Usage example:</p>
 * <pre>
 * int orderCount = orderRepository.countByUser(user);
 * Optional&lt;com.recordstore.order.Order&gt; orderWithProducts = orderRepository.findByIdWithProducts(1);
 * List&lt;com.recordstore.order.OrderProduct&gt; products = orderRepository.findByListOrderProducts(order);
 * </pre>
 */

public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * Counts the number of orders for a given user.
     *
     * @param user the user whose orders are to be counted
     * @return the number of orders for the specified user
     */
    int countByUser(User user);

    /**
     * Finds an order by its ID, including its associated products.
     *
     * @param id the ID of the order to find
     * @return an Optional containing the order with its products, if found
     */
    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.listOrderProducts WHERE o.id = :id")
    Optional<Order> findByIdWithProducts(@Param("id") Integer id);

    /**
     * Finds the list of order products for a given order.
     *
     * @param order the order whose products are to be found
     * @return a list of order products for the specified order
     */
    @Query("SELECT op FROM OrderProduct op WHERE op.order = :order")
    List<OrderProduct> findByListOrderProducts(@Param("order") Order order);

    /**
     * Finds orders that contain a specific product by the product's ID.
     *
     * @param id the ID of the product
     * @return a list of orders containing the specified product
     */
    @Query("SELECT o FROM Order o " +
    "JOIN o.listOrderProducts op " +
    "JOIN op.product p " +
    "WHERE p.id = :id")
    List<Order> findOrdersByProductId(@Param("id") Integer id);

    /**
     * Finds the most recent order for a given user.
     *
     * @param user the user whose most recent order is to be found
     * @return an Optional containing the most recent order for the specified user, if found
     */
    Optional<Order> findTopByUserOrderByOrderIdDesc(User user);

    /**
     * Finds an order by its order ID.
     *
     * @param orderId the ID of the order to find
     * @return an Optional containing the order with the specified ID, if found
     */
    Optional<Order> findByOrderId(Integer orderId);

    /**
     * Finds all orders for a given user.
     *
     * @param user the user whose orders are to be found
     * @return a list of orders for the specified user
     */
    List<Order> findByUser(User user);

    Optional<Order> findTopByUserIdOrderByCreatedAtDesc(Integer userId);
}
