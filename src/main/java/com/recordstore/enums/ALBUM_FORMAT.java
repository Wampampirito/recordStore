package com.recordstore.enums;
/**
 * Enum que representa los diferentes formatos de album disponibles en la tienda de discos.
 * <p>
 * Leyenda de los formatos de album:
 * <ul>
 *   <li>LP - Double Play (vinilo de larga duracion)</li>
 *   <li>EP - Extended Play (vinilo de duracion extendida)</li>
 *   <li>CD - Compact Disc</li>
 *   <li>CASSETTE - Casete</li>
 *   <li>DVD - Digital Versatile Disc</li>
 *   <li>CD_DVD - Combo de CD y DVD</li>
 *   <li>BOXSET - Caja de coleccion. Albumes con mas de cuatro discos.</li>
 * </ul>
 * 
 * Este enum es utilizado en la entidad {@link com.recordstore.model.Album} para definir el formato de un album en la tienda.
 * El formato influye en la disponibilidad del producto y su precio.
 * 
 * Ejemplo de uso:
 * <pre>
 * Album album = new Album();
 * album.setFormat(ALBUM_FORMAT.LP);
 * </pre>
 * 
 * @see com.recordstore.model.Album
 */

public enum ALBUM_FORMAT {
    
    /** Vinilo de larga duracion */
    LP,
    
    /** Vinilo de duracion extendida */
    EP,
    
    /** Para albumes con uno hasta 4 cds */
    CD,
    
    /** Casete de cinta magnetica */
    CASSETTE,
    
    /** Digital Versatile Disc */
    DVD,
    
    /** Combo de CD y DVD, hasta 4 discos */
    CD_DVD,
    
    /** Caja de coleccion. Albumes con mas de cuatro discos */
    BOXSET;
}
