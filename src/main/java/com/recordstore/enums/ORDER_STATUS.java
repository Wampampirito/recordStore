package com.recordstore.enums;

/**
 * Enum que representa los diferentes estados de una orden en la tienda de discos.
 * Este enum es utilizado en la entidad {@link com.recordstore.model.Order} para definir el estado actual de una orden.
 * El estado de la orden refleja su progreso en el proceso de compra y envio.
 * 
 * Los estados disponibles son:
 * <ul>
 *   <li>PENDING - La orden ha sido creada pero aun no ha sido procesada.</li>
 *   <li>PAID - La orden ha sido pagada, pero aun no ha sido enviada.</li>
 *   <li>SHIPPED - La orden ha sido enviada y esta en transito.</li>
 *   <li>COMPLETED - La orden ha sido entregada y completada exitosamente.</li>
 *   <li>CANCELLED - La orden ha sido cancelada y no se procesara.</li>
 *   <li>REFUNDED - La orden ha sido reembolsada despues de ser cancelada o devuelta.</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Order order = new Order();
 * order.setStatus(ORDER_STATUS.PAID);
 * </pre>
 */

 
public enum ORDER_STATUS  {
    /** La orden ha sido creada pero aun no ha sido procesada */
    PENDING,

    /** La orden ha sido pagada, pero aun no ha sido enviada */
    PAID,

    /** La orden ha sido enviada y esta en transito */
    SHIPPED,

    /** La orden ha sido entregada y completada exitosamente */
    COMPLETED,

    /** La orden ha sido cancelada y no se procesara */
    CANCELLED,

    /** La orden ha sido reembolsada despues de ser cancelada o devuelta */
    REFUNDED
}        
