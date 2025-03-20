package com.recordstore.enums;

/**
 * Enum that represents the different product categories available in the record store.
 * This enum is used in the child entities of {@link com.recordstore.model.Product} 
 * ({@link com.recordstore.model.Album}, {@link com.recordstore.model.Vinyl}, 
 * {@link com.recordstore.model.Headphone}, {@link com.recordstore.model.Speaker}, 
 * {@link com.recordstore.model.Player}, {@link com.recordstore.model.Portable}, 
 * {@link com.recordstore.model.Turntable}) to classify products into specific categories.
 * The category helps organize and filter products within the store.
 * Prefixes are used to denote child classes: (P_ for Player child classes, A_ for Album child classes, 
 * and AE_ for Audio Equipment child classes).
 * 
 * Available categories include:
 * <ul>
 *   <li>ALBUM - Music albums.</li>
 *   <li>A_VINYL - Vinyl records.</li>
 *   <li>AE_HEADPHONES - Headphones.</li>
 *   <li>AE_SPEAKER - Speakers.</li>
 *   <li>PLAYER - Players.</li>
 *   <li>P_PORTABLE - Portable players.</li>
 *   <li>P_TURNTABLE - Turntables.</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Product product = new Product();
 * product.setCategory(PRODUCT_CATEGORY.ALBUM);
 * </pre>
 */
public enum PRODUCT_CATEGORY {
    /** Music albums */
    ALBUM,

    /** Vinyl records */
    A_VINYL,

    /** Audio equipment */
    AUDIO_EQUIPMENT,

    /** Headphones */
    AE_HEADPHONES,

    /** Speakers */
    AE_SPEAKER,

    /** Players */
    PLAYER,

    /** Portable players */
    P_PORTABLE,

    /** Turntables */
    P_TURNTABLE
}
