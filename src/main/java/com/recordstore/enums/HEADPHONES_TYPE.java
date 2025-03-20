package com.recordstore.enums;
/**
 * Enum que representa los diferentes tipos de audifonos disponibles en la tienda de discos.
 * Este enum es utilizado en la entidad {@link com.recordstore.model.Headphone} para definir el tipo de audifono.
 * El tipo de audifono ayuda a clasificar los productos segun su diseño y uso.
 * 
 * Los tipos de audifonos disponibles incluyen:
 * <ul>
 *   <li>IN_EAR_HP - Audifonos intrauditivos (dentro del oido)</li>
 *   <li>ON_EAR_HP - Audifonos supraaurales (reposan sobre el oido)</li>
 *   <li>OVER_EAR_HP - Audifonos circumaurales (cubren el oido completamente)</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Headphone headphone = new Headphone();
 * headphone.setType(HEADPHONES_TYPE.IN_EAR_HP);
 * </pre>
 */


public enum HEADPHONES_TYPE {
    /** Audifonos intrauditivos  (dentro del oido) */
    IN_EAR,

    /** Audifonos supraaurales (reposan sobre el oido) */
    ON_EAR,

    /** Audifonos circumaurales (cubren el oido completamente) */
    OVER_EAR
}
