package com.recordstore.enums;

/**
 * Enum that represents different types of resistance.
 * This enum can be used in the classes {@link com.recordstore.model.Portable} and {@link com.recordstore.model.Speaker}
 * to classify the type of resistance they possess.
 * 
 * Available resistance types include:
 * <ul>
 *   <li>WATER - Water resistance</li>
 *   <li>SHOCK - Shock resistance</li>
 *   <li>DUST - Dust resistance</li>
 *   <li>WS - Water and shock resistance</li>
 *   <li>WD - Water and dust resistance</li>
 *   <li>SD - Shock and dust resistance</li>
 *   <li>WSD - Water, shock, and dust resistance</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Portable portable = new Portable();
 * portable.setResistance(RESISTANCE.WATER);
 * </pre>
 */
public enum RESISTANCE {

    /** Water resistance */
    WATER,

    /** Shock resistance */
    SHOCK,

    /** Dust resistance */
    DUST,

    /** Water and shock resistance */
    WS,

    /** Water and dust resistance */
    WD,

    /** Shock and dust resistance */
    SD,

    /** Water, shock, and dust resistance */
    WSD
}
