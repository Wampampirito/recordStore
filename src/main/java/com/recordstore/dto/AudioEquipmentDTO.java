package com.recordstore.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class AudioEquipmentDTO extends ProductDTO {
    private String brand;
    private String color;
    private Integer batteryLife; // En horas
    private Integer warranty; // En Meses
    private Boolean microphoneBuiltIn;
    private Boolean wireless;
    private Boolean bluetooth;
    private Boolean usb;
    private Boolean aux;

}