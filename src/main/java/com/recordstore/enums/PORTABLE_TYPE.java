package com.recordstore.enums;

/**
 * Enum que representa los diferentes tipos de reproductores portatiles disponibles en la tienda de discos.
 * Este enum es utilizado en la entidad {@link com.recordstore.model.Portable} para definir el tipo de reproductor portatil.
 * Los tipos de reproductores portatiles ayudan a clasificar y distinguir los diferentes dispositivos disponibles.
 * 
 * Los tipos disponibles son:
 * <ul>
 *   <li>DIGITAL_P - Reproductor digital portatil (como reproductores MP3, etc.).</li>
 *   <li>CASSETTE_P - Reproductor de casetes portatil.</li>
 *   <li>CD_P - Reproductor de CD portatil.</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Portable portable = new Portable();
 * portable.setType(PORTABLE_TYPE.CD_P);
 * </pre>
 */


public enum PORTABLE_TYPE {
    /** Reproductor digital portatil (como reproductores MP3, etc.) */
DIGITAL,

/** Reproductor de casetes portatil */
CASSETTE,

/** Reproductor de CD portatil */
CD
}
