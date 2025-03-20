package com.recordstore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) that represents a player in the system.
 * <p>
 * This DTO is used to transfer player-related data between different layers of the application,
 * such as the service and the controller. It contains the essential attributes for a player,
 * including brand, color, warranty, and various connectivity options like Bluetooth, USB, radio, 
 * AUX, RCA, and built-in speaker support.
 * </p>
 * 
 * It extends from {@link ProductDTO} to include common attributes for products while adding
 * specific fields for players.
 * 
 * Example of use:
 * <pre>
 * PlayerDTO playerDTO = new PlayerDTO();
 * playerDTO.setBrand("Pioneer");
 * playerDTO.setColor("Black");
 * playerDTO.setWarranty(24);
 * playerDTO.setBluetooth(true);
 * playerDTO.setUsb(true);
 * playerDTO.setRadio(true);
 * playerDTO.setAux(true);
 * playerDTO.setRca(true);
 * playerDTO.setBuiltInSpeaker(true);
 * </pre>
 * 
 * @see ProductDTO
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO extends ProductDTO {

    private String brand;
    private String color;
    private Integer warranty;
    private Boolean bluetooth;
    private Boolean usb;
    private Boolean radio;
    private Boolean aux;
    private Boolean rca;
    private Boolean builtInSpeaker;
}
