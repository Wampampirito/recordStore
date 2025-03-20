package com.recordstore.enums;

/**
 * Enum that represents the different types of noise cancellation in headphones.
 * This enum is used in the {@link com.recordstore.model.Headphone} entity to define the type of noise cancellation in the headphones.
 * The type of noise cancellation affects the headphones' ability to block out external sound.
 * 
 * Available types include:
 * <ul>
 *   <li>NONE_NC - No noise cancellation.</li>
 *   <li>PASSIVE_NC - Passive noise cancellation, achieved through the physical design of the headphones.</li>
 *   <li>ACTIVE_NC - Active noise cancellation, which uses electronic technology to reduce ambient noise.</li>
 *   <li>ACTIVE_AND_PASSIVE_NC - A combination of both active and passive noise cancellation.</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Headphone headphone = new Headphone();
 * headphone.setNoiseCanceling(NOISE_CANCELING.ACTIVE_NC);
 * </pre>
 */
public enum NOISE_CANCELING {
    /** No noise cancellation */
    NONE,

    /** Passive noise cancellation */
    PASSIVE,

    /** Active noise cancellation */
    ACTIVE,

    /** Combination of both active and passive noise cancellation */
    ACTIVE_AND_PASSIVE
}
