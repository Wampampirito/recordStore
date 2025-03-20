package com.recordstore.enums;

/**
 * Enum that represents the different album formats available in the record store.
 * <p>
 * Album format legend:
 * <ul>
 *   <li>LP - Long Play (vinyl with extended duration)</li>
 *   <li>EP - Extended Play (vinyl with extended duration)</li>
 *   <li>CD - Compact Disc</li>
 *   <li>CASSETTE - Cassette tape</li>
 *   <li>DVD - Digital Versatile Disc</li>
 *   <li>CD_DVD - Combo of CD and DVD</li>
 *   <li>BOXSET - Collector's box set, albums with more than four discs</li>
 * </ul>
 * 
 * This enum is used in the {@link com.recordstore.model.Album} entity to define the format of an album in the store.
 * The format influences the product's availability and price.
 * 
 * Example usage:
 * <pre>
 * Album album = new Album();
 * album.setFormat(ALBUM_FORMAT.LP);
 * </pre>
 * 
 * @see com.recordstore.model.Album
 */
public enum ALBUM_FORMAT {
    
    /** Long Play vinyl */
    LP,
    
    /** Extended Play vinyl */
    EP,
    
    /** For albums with one to four CDs */
    CD,
    
    /** Cassette tape */
    CASSETTE,
    
    /** Digital Versatile Disc */
    DVD,
    
    /** Combo of CD and DVD, up to 4 discs */
    CD_DVD,
    
    /** Collector's box set. Albums with more than four discs */
    BOXSET;
}
