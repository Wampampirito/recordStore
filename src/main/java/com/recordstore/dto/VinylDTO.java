package com.recordstore.dto;

import com.recordstore.enums.VINYL_RPM;
import com.recordstore.enums.VINYL_SIZE;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for representing a vinyl record.
 * <p>
 * This DTO is used to transfer data related to vinyl records, including specific
 * attributes such as size, RPM, and color. It extends {@link AlbumDTO} to inherit
 * general album properties, and adds vinyl-specific details.
 * </p>
 * 
 * 
 * Example of usage:
 * <pre>
 * VinylDTO vinylDTO = new VinylDTO();
 * vinylDTO.setSize(VINYL_SIZE.LP);
 * vinylDTO.setRpm(VINYL_RPM.RPM_33);
 * vinylDTO.setColor("Black");
 * </pre>
 * 
 * 
 * @see AlbumDTO
 * @see VINYL_SIZE
 * @see VINYL_RPM
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class VinylDTO extends AlbumDTO {

    private VINYL_SIZE size;
    private VINYL_RPM rpm;
    private String color;
}
