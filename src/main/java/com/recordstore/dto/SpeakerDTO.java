package com.recordstore.dto;

import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.RESISTANCE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) that represents a speaker in the system.
 * <p>
 * This DTO is used to transfer speaker-related data between different layers of the application,
 * such as the service and the controller. It includes the essential attributes for a speaker,
 * such as brand, model, price, power, resistance, radio support, impedance, frequency range,
 * and weight.
 * </p>
 * 
 * It extends from {@link AudioEquipmentDTO} to inherit common attributes for audio equipment
 * while adding specific fields for speakers.
 * 
 * Example of use:
 * <pre>
 * SpeakerDTO speakerDTO = new SpeakerDTO();
 * speakerDTO.setBrand("Sony");
 * speakerDTO.setModel("SRS-XB33");
 * speakerDTO.setPrice(199.99);
 * speakerDTO.setPower(30);
 * speakerDTO.setPowerType(POWER_TYPE.BATTERY);
 * speakerDTO.setResistance(RESISTANCE.EIGHT_OHM);
 * speakerDTO.setRadio(true);
 * speakerDTO.setImpedance(8);
 * speakerDTO.setMinFrequency(50);
 * speakerDTO.setMaxFrequency(20000);
 * speakerDTO.setWeight(2);
 * </pre>
 * 
 * @see AudioEquipmentDTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class SpeakerDTO extends AudioEquipmentDTO {

    private String brand;
    private String model;
    private Double price;
    private Integer power; // In watts
    private POWER_TYPE powerType;
    private RESISTANCE resistance;
    private Boolean radio;
    private Integer impedance;
    private Integer minFrequency;
    private Integer maxFrequency;
    private Integer weight;
}
