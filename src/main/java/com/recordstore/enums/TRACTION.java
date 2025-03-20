package com.recordstore.enums;

/**
 * Enum that represents the different types of drive for a turntable.
 * This enum is used in the {@link com.recordstore.model.Turntable} class to determine the platter rotation mechanism.
 * 
 * Available drive types include:
 * <ul>
 *   <li>BELT_DRIVE - Belt drive</li>
 *   <li>DIRECT_DRIVE - Direct drive</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Turntable turntable = new Turntable();
 * turntable.setTraction(TRACTION.BELT_DRIVE);
 * </pre>
 */
public enum TRACTION {
    /** Belt drive */
    BELT_DRIVE,

    /** Direct drive */
    DIRECT_DRIVE
}
