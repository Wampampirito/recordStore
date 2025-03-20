package com.recordstore.enums;

/**
 * Enum que representa diferentes tipos de resistencias.
 * Este enum puede ser utilizado en las clases {@link com.recordstore.model.Portable} y {@link com.recordstore.model.Speaker}
 * para clasificar el tipo de resistencia que poseen.
 * 
 * Los tipos de resistencia disponibles incluyen:
 * <ul>
 *   <li>WATER - Resistencia al agua</li>
 *   <li>SHOCK - Resistencia a golpes</li>
 *   <li>DUST - Resistencia al polvo</li>
 *   <li>WS - Resistencia al agua y golpes</li>
 *   <li>WD - Resistencia al agua y polvo</li>
 *   <li>SD - Resistencia a golpes y polvo</li>
 *   <li>WSD - Resistencia al agua, golpes y polvo</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Portable portable = new Portable();
 * portable.setResistance(RESISTANCE.WATER);
 * </pre>
 */

 
public enum RESISTANCE {

    /**Resistencia al agua */
    WATER,

    /**Resistencia a golpes */
    SHOCK,

    /**Resistencia al polvo */
    DUST,

    /**Resistencia al agua y golpes */
    WS,

    /**Resistencia al agua y polvo */
    WD,

    /**Resistencia a golpes y polvo */
    SD,

    /**Resistencia al agua, golpes y polvo */
    WSD

}
