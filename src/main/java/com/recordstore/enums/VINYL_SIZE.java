
package com.recordstore.enums;
/**
 * Enum que representa los diferentes tamanios de discos de vinilo disponibles.
 * <p>
 * Leyenda de los tamanios:
 * <ul>
 *   <li>S_7: Disco de vinilo de 7 pulgadas</li>
 *   <li>S_10: Disco de vinilo de 10 pulgadas</li>
 *   <li>S_12: Disco de vinilo de 12 pulgadas</li>
 * </ul>
 * Este enum es utilizado en la entidad {@link com.recordstore.model.Vinyl} para definir el tamanio del disco de vinilo.
 * El tamanio del disco afecta la duracion y el tipo de reproduccion del vinilo.
 * 
 * Ejemplo de uso:
 * <pre>
 * Vinyl vinyl = new Vinyl();
 * vinyl.setSize(VINYL_SIZE.S_12);
 * </pre>
 * 
 */


public enum VINYL_SIZE {
    /**Disco de vinilo de 7 pulgadas */
    S_7,

    /**Disco de vinilo de 10 pulgadas */
    S_10,

    /**Disco de vinilo de 12 pulgadas */
    S_12

}
