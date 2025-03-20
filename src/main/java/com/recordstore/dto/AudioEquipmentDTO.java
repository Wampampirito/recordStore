package com.recordstore.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) that represents an audio equipment in the system.
 * <p>
 * This DTO is used to transfer audio equipment-related data between different layers of the application,
 * such as the service and the controller. It contains the essential attributes for audio equipment, including 
 * the brand, color, battery life, warranty, microphone status, and connectivity options (wireless, Bluetooth, USB, AUX).
 * </p>
 * 
 * It extends from {@link ProductDTO} to include common attributes for products, while adding specific
 * fields for audio equipment such as headphones, speakers, etc.
 * 
 * Example of use:
 * <pre>
 * AudioEquipmentDTO audioEquipmentDTO = new AudioEquipmentDTO();
 * audioEquipmentDTO.setBrand("Sony");
 * audioEquipmentDTO.setColor("Black");
 * audioEquipmentDTO.setBatteryLife(30);
 * audioEquipmentDTO.setWarranty(12);
 * audioEquipmentDTO.setMicrophoneBuiltIn(true);
 * audioEquipmentDTO.setWireless(true);
 * audioEquipmentDTO.setBluetooth(true);
 * audioEquipmentDTO.setUsb(true);
 * audioEquipmentDTO.setAux(false);
 * </pre>
 * 
 * @see ProductDTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class AudioEquipmentDTO extends ProductDTO {
    private String brand;
    private String color;
    private Integer batteryLife; // In Hours
    private Integer warranty; // In Months
    private Boolean microphoneBuiltIn;
    private Boolean wireless;
    private Boolean bluetooth;
    private Boolean usb;
    private Boolean aux;
}
