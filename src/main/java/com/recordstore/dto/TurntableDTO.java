package com.recordstore.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.recordstore.enums.MECHANISM;
import com.recordstore.enums.TRACTION;
import com.recordstore.enums.VINYL_RPM;
import lombok.AllArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing a turntable.
 * <p>
 * This class extends {@link PlayerDTO} and includes additional attributes
 * specific to turntables, such as preamplifier capability, rotation speed, traction type, 
 * and mechanism type.
 * </p>
 * <p>
 * The DTO is primarily used for data exchange between the API and clients, ensuring 
 * proper serialization and deserialization of turntable-related data.
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TurntableDTO extends PlayerDTO {

    /**
     * Indicates whether the turntable has a built-in preamplifier.
     */
    private Boolean hasBuiltInPreAmp;

    /**
     * The allowed rotation speeds for the vinyl, in revolutions per minute (RPM).
     * <p>
     * This defines the playback speed of the vinyl records supported by the turntable.
     * </p>
     */
    private VINYL_RPM rpm;

    /**
     * The type of traction used by the turntable (e.g., direct drive or belt drive).
     * <p>
     * Traction type affects playback stability and maintenance requirements.
     * </p>
     */
    private TRACTION traction;

    /**
     * The mechanism used by the turntable to spin the vinyl.
     * <p>
     * Different mechanisms provide varying levels of automation and control over playback.
     * </p>
     */
    private MECHANISM mechanism;
}
