package com.recordstore.enums;

/**
 * Enum that represents the different types of headphones available in the record store.
 * This enum is used in the {@link com.recordstore.model.Headphone} entity to define the type of headphone.
 * The headphone type helps classify products based on their design and use.
 * 
 * Available headphone types include:
 * <ul>
 *   <li>IN_EAR_HP - In-ear headphones (inside the ear)</li>
 *   <li>ON_EAR_HP - On-ear headphones (resting on the ear)</li>
 *   <li>OVER_EAR_HP - Over-ear headphones (covering the entire ear)</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Headphone headphone = new Headphone();
 * headphone.setType(HEADPHONES_TYPE.IN_EAR_HP);
 * </pre>
 */
public enum HEADPHONES_TYPE {
    /** In-ear headphones (inside the ear) */
    IN_EAR,

    /** On-ear headphones (resting on the ear) */
    ON_EAR,

    /** Over-ear headphones (covering the entire ear) */
    OVER_EAR
}
