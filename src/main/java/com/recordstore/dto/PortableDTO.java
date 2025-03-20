package com.recordstore.dto;

import com.recordstore.enums.PORTABLE_TYPE;
import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.RESISTANCE;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * Data Transfer Object (DTO) that represents a portable player in the system.
 * <p>
 * This DTO is used to transfer portable player-related data between different layers of the application,
 * such as from the service to the controller. It contains essential attributes for a portable player,
 * including type, power type, battery life, and resistance.
 * </p>
 * 
 * It extends from {@link PlayerDTO} to include common attributes for players while adding
 * specific fields relevant to portable players.
 * 
 * Example of use:
 * <pre>
 * PortableDTO portableDTO = new PortableDTO();
 * portableDTO.setPortableType(PORTABLE_TYPE.CD);
 * portableDTO.setPowerType(POWER_TYPE.BATTERY);
 * portableDTO.setBatteryLife(12);
 * portableDTO.setResistance(RESISTANCE.HIGH);
 * </pre>
 * 
 * @see PlayerDTO
 * @see PORTABLE_TYPE
 * @see POWER_TYPE
 * @see RESISTANCE
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PortableDTO extends PlayerDTO {

    private PORTABLE_TYPE portableType;
    private POWER_TYPE powerType;
    private int batteryLife; // In hours
    private RESISTANCE resistance;
}
