package com.recordstore.enums;

/**
 * Enum that represents the different vinyl record sizes available.
 * <p>
 * Size legend:
 * <ul>
 *   <li>S_7: 7-inch vinyl record</li>
 *   <li>S_10: 10-inch vinyl record</li>
 *   <li>S_12: 12-inch vinyl record</li>
 * </ul>
 * This enum is used in the {@link com.recordstore.model.Vinyl} entity to define the size of the vinyl record.
 * The size of the record affects the duration and type of playback for the vinyl.
 * 
 * Example usage:
 * <pre>
 * Vinyl vinyl = new Vinyl();
 * vinyl.setSize(VINYL_SIZE.S_12);
 * </pre>
 */
public enum VINYL_SIZE {
    /** 7-inch vinyl record */
    S_7,

    /** 10-inch vinyl record */
    S_10,

    /** 12-inch vinyl record */
    S_12
}
