package com.recordstore.enums;
/**
 * Enum que representa los diferentes pesos de discos de vinilo disponibles.
 * <p>
 * Leyenda de los pesos:
 * <ul>
 *   <li>W_40: 40 gramos (tipico para discos de 7 pulgadas)</li>
 *   <li>W_60: 60 gramos (tipico para discos de 7 pulgadas)</li>
 *   <li>W_80: 80 gramos (tipico para discos de 10 pulgadas)</li>
 *   <li>W_120: 120 gramos (tipico para discos de 12 pulgadas, estandar)</li>
 *   <li>W_140: 140 gramos (tipico para discos de 12 pulgadas, estandar)</li>
 *   <li>W_180: 180 gramos (tipico para discos de 12 pulgadas, heavyweight)</li>
 *   <li>W_200: 200 gramos o mas (tipico para discos de vinilo premium)</li>
 * </ul>
 * Este enum es utilizado en la entidad {@link com.recordstore.model.Vinyl} para definir el peso del disco de vinilo.
 * El peso del disco puede afectar la calidad de sonido, la durabilidad y el precio del vinilo.
 * 
 * Ejemplo de uso:
 * <pre>
 * Vinyl vinyl = new Vinyl();
 * vinyl.setWeight(VINYL_WEIGHT.W_180);
 * </pre>
 * 
 */


public enum VINYL_WEIGHT {
    /**40 gramos */
    W_40,   

    /**60 gramos */
    W_60,   

    /**80 gramos */
    W_80,   

    /**120 gramos */
    W_120,

    /**140 gramos */ 
    W_140,  

    /**180 gramos */
    W_180,  

    /**200 gramos o mas */
    W_200   

}
