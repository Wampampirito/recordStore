package com.recordstore.enums;

/**
 * Enum que representa los diferentes tipos de cancelacion de ruido en los audifonos.
 * Este enum es utilizado en la entidad {@link com.recordstore.model.Headphone} para definir el tipo de cancelacion de ruido de los audifonos.
 * El tipo de cancelacion de ruido afecta la capacidad de los audifonos para bloquear el sonido externo.
 * 
 * Los tipos disponibles son:
 * <ul>
 *   <li>NONE_NC - No hay cancelacion de ruido.</li>
 *   <li>PASSIVE_NC - Cancelacion de ruido pasiva, que se logra mediante el diseño fisico de los audifonos.</li>
 *   <li>ACTIVE_NC - Cancelacion de ruido activa, que utiliza tecnologia electronica para reducir el ruido ambiental.</li>
 *   <li>ACTIVE_AND_PASSIVE_NC - Combinacion de cancelacion de ruido activa y pasiva.</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Headphone headphone = new Headphone();
 * headphone.setNoiseCanceling(NOISE_CANCELING.ACTIVE_NC);
 * </pre>
 */


public enum NOISE_CANCELING {
    /** No hay cancelacion de ruido */
    NONE,

    /** Cancelacion de ruido pasiva */
    PASSIVE,

    /** Cancelacion de ruido activa */
    ACTIVE,

    /** Combinacion de cancelacion de ruido activa y pasiva */
    ACTIVE_AND_PASSIVE

}
