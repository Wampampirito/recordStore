package com.recordstore.enums;

/**
 * Enum que representa los diferentes tipos de traccion de una tornamesa.
 * Este enum se utiliza en la clase {@link com.recordstore.model.Turntable}para determinar el mecanismo de rotacion del plato.
 * 
 * Los tipos de traccion disponibles incluyen:
 * <ul>
 *   <li>BELT_DRIVE - Traccion por correa</li>
 *   <li>DIRECT_DRIVE - Traccion directa</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Turntable turntable = new Turntable();
 * turntable.setTraction(TRACTION.BELT_DRIVE);
 * </pre>
 */


public enum TRACTION {
    /**Traccion por correa */
    BELT_DRIVE,

    /**Traccion directa */
    DIRECT_DRIVE
}
