package com.recordstore.enums;

/**
 * Enum that represents the different needle positioning mechanisms in a turntable.
 * This enum is used in the {@link com.recordstore.model.Turntable} entity to define the needle positioning system.
 * The mechanism determines how the needle moves in relation to the vinyl records.
 * 
 * Available mechanisms include:
 * <ul>
 *   <li>MANUAL - The user manually positions the needle on the record.</li>
 *   <li>AUTOMATIC - The system automatically positions the needle on the record.</li>
 *   <li>SEMI_AUTOMATIC - The system automatically positions the needle on the record but requires user intervention for certain actions.</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Turntable turntable = new Turntable();
 * turntable.setMechanism(MECHANISM.MANUAL);
 * </pre>
 */
public enum MECHANISM {
    /** The user manually positions the needle on the record */
    MANUAL,

    /** The system automatically positions the needle on the record */
    AUTOMATIC,

    /** The system automatically positions the needle on the record, but it can also be manually positioned */
    SEMI_AUTOMATIC
}
