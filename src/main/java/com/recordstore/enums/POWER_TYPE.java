package com.recordstore.enums;


/**
 * Enum que representa los diferentes tipos de alimentacion electrica para dispositivos.
 * Este enum es utilizado en las entidades {@link com.recordstore.model.Portable} y {@link com.recordstore.model.Speaker} para definir el tipo de energia que utilizan.
 * Los tipos de alimentacion ayudan a categorizar los dispositivos segun la fuente de energia que requieren.
 * 
 * Los tipos disponibles son:
 * <ul>
 *   <li>DIRECT_CURRENT - Corriente continua, tipicamente usada en dispositivos portatiles.</li>
 *   <li>ALTERNATING_CURRENT - Corriente alterna, comunmente usada en dispositivos de mayor tamanio como altavoces.</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Portable portable = new Portable();
 * portable.setPowerType(POWER_TYPE.DIRECT_CURRENT);
 * </pre>
 */

public enum POWER_TYPE {
        /**Corriente continua, tipicamente usada en dispositivos portatiles. */
        DC,
        /**Corriente alterna, comunmente usada en dispositivos de mayor tamanio como altavoces. */
        AC
}
