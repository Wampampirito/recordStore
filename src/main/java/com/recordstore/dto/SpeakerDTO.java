package com.recordstore.dto;

import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.RESISTANCE;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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