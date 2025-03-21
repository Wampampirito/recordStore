package com.recordstore.dto;

import com.recordstore.enums.HEADPHONES_TYPE;
import com.recordstore.enums.NOISE_CANCELING;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) that represents a pair of headphones in the system.
 * <p>
 * This DTO is used to transfer headphone-related data between different layers of the application,
 * such as the service and the controller. It contains the essential attributes for headphones,
 * including brand, price, wireless capability, headphones type, and noise canceling feature.
 * </p>
 * 
 * It extends from {@link AudioEquipmentDTO} to include common attributes for audio equipment.
 * 
 * Example of use:
 * <pre>
 * HeadphonesDTO headphonesDTO = new HeadphonesDTO();
 * headphonesDTO.setBrand("Sony");
 * headphonesDTO.setPrice(199.99);
 * headphonesDTO.setWireless(true);
 * headphonesDTO.setHeadphoneType(HEADPHONES_TYPE.OVER_EAR);
 * headphonesDTO.setAnc(NOISE_CANCELING.ACTIVE);
 * </pre>
 * 
 * @see AudioEquipmentDTO
 * @see HEADPHONES_TYPE
 * @see NOISE_CANCELING
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class HeadphoneDTO extends AudioEquipmentDTO {
    private String brand;
    private Double price;
    private Boolean wireless;
    private HEADPHONES_TYPE headphonesType;
    private NOISE_CANCELING anc;
}
