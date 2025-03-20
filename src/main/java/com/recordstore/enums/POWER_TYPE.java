package com.recordstore.enums;

/**
 * Enum that represents the different types of power supply for devices.
 * This enum is used in the {@link com.recordstore.model.Portable} and {@link com.recordstore.model.Speaker} entities 
 * to define the type of energy they use.
 * Power types help categorize devices based on the energy source they require.
 * 
 * Available types include:
 * <ul>
 *   <li>DIRECT_CURRENT - Direct current, typically used in portable devices.</li>
 *   <li>ALTERNATING_CURRENT - Alternating current, commonly used in larger devices such as speakers.</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Portable portable = new Portable();
 * portable.setPowerType(POWER_TYPE.DIRECT_CURRENT);
 * </pre>
 */
public enum POWER_TYPE {
    /** Direct current, typically used in portable devices. */
    DC,

    /** Alternating current, commonly used in larger devices such as speakers. */
    AC
}
