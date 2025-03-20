package com.recordstore.enums;
/**
 * Enumeracion que representa las diferentes velocidades de reproduccion (RPM) de los discos de vinilo.
 * <p>
 * Este enum se utiliza en las clases {@link com.recordstore.model.Vinyl} y {@link com.recordstore.model.Turntable}..
 * En {@link com.recordstore.model.Vinyl}., se puede asignar una sola velocidad de reproduccion que corresponda al disco especifico.
 * En {@link com.recordstore.model.Turntable}., se pueden admitir multiples velocidades, permitiendo la reproduccion de discos con diferentes RPM.
 * </p>
 * <p>
 * Las velocidades disponibles son:
 * </p>
 * <ul>
 *   <li><b>RPM_33</b>: 33 1/3 revoluciones por minuto. Comunmente utilizada para LPs de larga duracion.</li>
 *   <li><b>RPM_45</b>: 45 revoluciones por minuto. Usada tipicamente para sencillos y EPs.</li>
 *   <li><b>RPM_78</b>: 78 revoluciones por minuto. Velocidad estandar para discos antiguos, generalmente de shellac.</li>
 *   <li><b>RPM_33_45</b>: Compatible con discos de 33 1/3 y 45 RPM. Util en tornamesas que permiten cambiar entre estas velocidades sin necesidad de ajustes manuales.</li>
 *   <li><b>RPM_33_45_78</b>: Compatible con discos de 33 1/3, 45 y 78 RPM. Ofrece la mayor flexibilidad, permitiendo la reproduccion de discos de todas estas velocidades sin complicaciones.</li>
 * </ul>
 * <p>
 * <b>Nota:</b> Las tornamesas que admiten multiples velocidades facilitan la reproduccion de una variedad mas amplia de discos. Por ejemplo, modelos como el Audio-Technica LP120X permiten cambiar entre 33 1/3, 45 y 78 RPM, ofreciendo versatilidad para coleccionistas y audiofilos. :contentReference[oaicite:1]{index=1}
 * </p>
 * <p>
 * Ejemplo de uso en la clase {@link com.recordstore.model.Vinyl}:
 * </p>
 * <pre>
 * Vinyl vinylRecord = new Vinyl();
 * vinylRecord.setRpm(VINYL_RPM.RPM_33);
 * </pre>
 * <p>
 * Ejemplo de uso en {@link com.recordstore.model.Turntable}:
 * </p>
 * <pre>
 * Turntable turntable = new Turntable();
 * turntable.setSupportedRpm(Arrays.asList(VINYL_RPM.RPM_33, VINYL_RPM.RPM_45, VINYL_RPM.RPM_78));
 * </pre>
 */


public enum VINYL_RPM {
    /**33 1/3 revoluciones por minuto */
    RPM_33,

    /**45 revoluciones por minuto */
    RPM_45,

    /**78 revoluciones por minuto */
    RPM_78,

    /**Compatible con discos de 33 1/3 y 45 RPM */
    RPM_33_45,

    /**Compatible con discos de 33 1/3, 45 y 78 RPM */
    RPM_33_45_78

}
