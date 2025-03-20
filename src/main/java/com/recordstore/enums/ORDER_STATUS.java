package com.recordstore.enums;

/**
 * Enum that represents the different statuses of an order in the record store.
 * This enum is used in the {@link com.recordstore.model.Order} entity to define the current status of an order.
 * The status of the order reflects its progress in the purchasing and shipping process.
 * 
 * Available statuses include:
 * <ul>
 *   <li>PENDING - The order has been created but has not been processed yet.</li>
 *   <li>PAID - The order has been paid for but has not been shipped yet.</li>
 *   <li>SHIPPED - The order has been shipped and is in transit.</li>
 *   <li>COMPLETED - The order has been delivered and successfully completed.</li>
 *   <li>CANCELLED - The order has been cancelled and will not be processed.</li>
 *   <li>REFUNDED - The order has been refunded after being cancelled or returned.</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Order order = new Order();
 * order.setStatus(ORDER_STATUS.PAID);
 * </pre>
 */
public enum ORDER_STATUS  {
    /** The order has been created but has not been processed yet */
    PENDING,

    /** The order has been paid for but has not been shipped yet */
    PAID,

    /** The order has been shipped and is in transit */
    SHIPPED,

    /** The order has been delivered and successfully completed */
    COMPLETED,

    /** The order has been cancelled and will not be processed */
    CANCELLED,

    /** The order has been refunded after being cancelled or returned */
    REFUNDED
}
