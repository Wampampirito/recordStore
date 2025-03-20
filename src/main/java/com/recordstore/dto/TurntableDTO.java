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

    private Boolean hasBuiltInPreAmp;
    private VINYL_RPM rpm;
    private TRACTION traction;
    private MECHANISM mechanism;
}
