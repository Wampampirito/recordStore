package com.recordstore.enums;

/**
 * Enumeration that represents the different playback speeds (RPM) for vinyl records.
 * <p>
 * This enum is used in the {@link com.recordstore.model.Vinyl} and {@link com.recordstore.model.Turntable} classes.
 * In {@link com.recordstore.model.Vinyl}, a single playback speed can be assigned to the specific record.
 * In {@link com.recordstore.model.Turntable}, multiple speeds can be supported, allowing the playback of records with different RPMs.
 * </p>
 * <p>
 * The available speeds are:
 * </p>
 * <ul>
 *   <li><b>RPM_33</b>: 33 1/3 revolutions per minute. Commonly used for long-playing (LP) records.</li>
 *   <li><b>RPM_45</b>: 45 revolutions per minute. Typically used for singles and EPs.</li>
 *   <li><b>RPM_78</b>: 78 revolutions per minute. Standard speed for older records, usually made of shellac.</li>
 *   <li><b>RPM_33_45</b>: Compatible with both 33 1/3 and 45 RPM records. Useful for turntables that allow switching between these speeds without manual adjustments.</li>
 *   <li><b>RPM_33_45_78</b>: Compatible with 33 1/3, 45, and 78 RPM records. Provides the most flexibility, allowing playback of all these speeds without complications.</li>
 * </ul>
 * <p>
 * <b>Note:</b> Turntables that support multiple speeds make it easier to play a wider variety of records. For example, models like the Audio-Technica LP120X allow switching between 33 1/3, 45, and 78 RPM, offering versatility for collectors and audiophiles.
 * </p>
 * <p>
 * Example usage in {@link com.recordstore.model.Vinyl}:
 * </p>
 * <pre>
 * Vinyl vinylRecord = new Vinyl();
 * vinylRecord.setRpm(VINYL_RPM.RPM_33);
 * </pre>
 * <p>
 * Example usage in {@link com.recordstore.model.Turntable}:
 * </p>
 * <pre>
 * Turntable turntable = new Turntable();
 * turntable.setSupportedRpm(Arrays.asList(VINYL_RPM.RPM_33, VINYL_RPM.RPM_45, VINYL_RPM.RPM_78));
 * </pre>
 */
public enum VINYL_RPM {
    /** 33 1/3 revolutions per minute */
    RPM_33,

    /** 45 revolutions per minute */
    RPM_45,

    /** 78 revolutions per minute */
    RPM_78,

    /** Compatible with 33 1/3 and 45 RPM records */
    RPM_33_45,

    /** Compatible with 33 1/3, 45, and 78 RPM records */
    RPM_33_45_78
}
