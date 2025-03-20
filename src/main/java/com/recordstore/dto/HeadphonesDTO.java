package com.recordstore.dto;

import com.recordstore.enums.HEADPHONES_TYPE;
import com.recordstore.enums.NOISE_CANCELING;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor

public class HeadphonesDTO extends AudioEquipmentDTO  {
    private String brand;
    private Double price;
    private Boolean wireless;
    private HEADPHONES_TYPE headphonesType;
    private NOISE_CANCELING anc;
}