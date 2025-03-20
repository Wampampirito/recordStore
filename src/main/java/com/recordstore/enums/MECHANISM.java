package com.recordstore.enums;

/**
 * Enum que representa los diferentes mecanismos de posicionamiento de la aguja en una tornamesa.
 * Este enum es utilizado en la entidad {@link com.recordstore.model.Turntable}. para definir el sistema de posicionamiento de la aguja.
 * El mecanismo determina como se mueve la aguja en relacion con los discos de vinilo.
 * 
 * Los mecanismos disponibles incluyen:
 * <ul>
 *   <li>MANUAL - El usuario posiciona manualmente la aguja en el disco.</li>
 *   <li>AUTOMATIC - El sistema posiciona automaticamente la aguja en el disco.</li>
 *   <li>SEMI_AUTOMATIC - El sistema posiciona la aguja en el disco, pero requiere intervencion del usuario para ciertas acciones.</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Turntable turntable = new Turntable();
 * turntable.setMechanism(MECHANISM.MANUAL);
 * </pre>
 */

 
public enum MECHANISM {
    /** El usuario posiciona manualmente la aguja en el disco */ 
    MANUAL,

    /** El sistema posiciona automaticamente la aguja en el disco */
    AUTOMATIC,

    /** El sistema posiciona automaticamnente la aguja en el disco, pero tambien se puede posicionar manualmente */
    SEMI_AUTOMATIC
}
