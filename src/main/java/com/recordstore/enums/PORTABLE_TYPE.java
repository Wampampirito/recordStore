package com.recordstore.enums;

/**
 * Enum that represents the different types of portable players available in the record store.
 * This enum is used in the {@link com.recordstore.model.Portable} entity to define the type of portable player.
 * Portable player types help classify and distinguish the different devices available.
 * 
 * Available types include:
 * <ul>
 *   <li>DIGITAL_P - Portable digital player (e.g., MP3 players, etc.).</li>
 *   <li>CASSETTE_P - Portable cassette player.</li>
 *   <li>CD_P - Portable CD player.</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Portable portable = new Portable();
 * portable.setType(PORTABLE_TYPE.CD_P);
 * </pre>
 */
public enum PORTABLE_TYPE {
    /** Portable digital player (e.g., MP3 players, etc.) */
    DIGITAL,

    /** Portable cassette player */
    CASSETTE,

    /** Portable CD player */
    CD
}
