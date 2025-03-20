package com.recordstore.enums;



/**
 * Enum que representa las diferentes categorias de productos disponibles en la tienda de discos.
 * Este enum es utilizado en las entidades hijas de {@link com.recordstore.model.Product} ( {@link com.recordstore.model.Album}, {@link com.recordstore.model.Vinyl}, {@link com.recordstore.model.Headphone}, {@link com.recordstore.model.Speaker}, 
 * {@link com.recordstore.model.Player}, {@link com.recordstore.model.Portable}, {@link com.recordstore.model.Turntable}.)
 * para clasificar los productos en categorias especificas. La categoria ayuda a organizar y filtrar los productos dentro de la tienda.
 *  Se usan prefijos para denotar que la clase es hija. (P_ para las clases hijas de Player, A_ para las clases hijas de Album, 
 * y AE_ para las clases hijas de Audio Equipment.)
 * Las categorias disponibles incluyen:
 * <ul>
 *   <li>ALBUM - Albumes musicales.</li>
 *   <li>A_VINYL - Vinilos.</li>
 *   <li>AE_HEADPHONES - Auriculares.</li>
 *   <li>AE_SPEAKER - Altavoces.</li>
 *   <li>PLAYER - Reproductores.</li>
 *   <li>P_PORTABLE - Reproductores portatiles.</li>
 *   <li>P_TURNTABLE - Tornamesas.</li>
 * </ul>
 * 
 * Ejemplo de uso:
 * <pre>
 * Product product = new Product();
 * product.setCategory(PRODUCT_CATEGORY.ALBUM);
 * </pre>
 */

public enum PRODUCT_CATEGORY {
    /** Albumes musicales */
    ALBUM,

    /** Vinilos */
    A_VINYL,

    /** Equipamiento de audio */
    AUDIO_EQUIPMENT,

    /** Auriculares */
    AE_HEADPHONES,

    /** Altavoces */
    AE_SPEAKER,

    /**Reproductores */
    PLAYER,

    /** Reproductores portatiles */
    P_PORTABLE,   

    /** Tornamesas */
    P_TURNTABLE
    
}
