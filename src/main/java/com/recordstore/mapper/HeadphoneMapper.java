package com.recordstore.mapper;

import org.springframework.stereotype.Component;

import com.recordstore.dto.HeadphoneDTO;
import com.recordstore.model.Headphone;

/**
 * Mapper class responsible for converting {@link Headphone} entities to {@link HeadphoneDTO} and vice versa.
 * This class handles the mapping of properties between the entity and the DTO, allowing smooth data transfer
 * between the persistence layer (entity) and the presentation layer (DTO).
 */
@Component
public class HeadphoneMapper {

    /**
     * Converts a {@link Headphone} entity to a {@link HeadphoneDTO}.
     *
     * @param headphone The {@link Headphone} entity to be converted.
     * @return The {@link HeadphoneDTO} representation of the provided entity, or {@code null} if the input is {@code null}.
     */
    public HeadphoneDTO toDTO (Headphone headphone) {
        if (headphone == null) {
            return null;
        }
        
        HeadphoneDTO headphonesDTO = new HeadphoneDTO();
        // Mapping properties from Headphone entity to HeadphonesDTO
        headphonesDTO.setBrand(headphone.getBrand());
        headphonesDTO.setName(headphone.getName());
        headphonesDTO.setPrice(headphone.getPrice());
        headphonesDTO.setWireless(headphone.getWireless());
        headphonesDTO.setBatteryLife(headphone.getBatteryLife());
        headphonesDTO.setWarranty(headphone.getWarranty());
        headphonesDTO.setBluetooth(headphone.getBluetooth());
        headphonesDTO.setAux(headphone.getAux());
        headphonesDTO.setMicrophoneBuiltIn(headphone.getMicrophoneBuiltIn());
        headphonesDTO.setColor(headphone.getColor());
        headphonesDTO.setHeadphonesType(headphone.getHeadphoneType());
        headphonesDTO.setAnc(headphone.getAnc());
        headphonesDTO.setStock(headphone.getStock());
        headphonesDTO.setId(headphone.getId());
        
        return headphonesDTO;
    }

    /**
     * Converts a {@link HeadphoneDTO} to a {@link Headphone} entity.
     *
     * @param headphonesDTO The {@link HeadphoneDTO} to be converted.
     * @return The {@link Headphone} entity representation of the provided DTO, or {@code null} if the input is {@code null}.
     */
    public Headphone toEntity(HeadphoneDTO headphonesDTO) {
        if (headphonesDTO == null) {
            return null;
        }
        
        Headphone headphone = new Headphone();
        // Mapping properties from HeadphonesDTO to Headphone entity
        headphone.setBrand(headphonesDTO.getBrand());
        headphone.setName(headphonesDTO.getName());
        headphone.setPrice(headphonesDTO.getPrice());
        headphone.setWireless(headphonesDTO.getWireless());
        headphone.setBatteryLife(headphonesDTO.getBatteryLife());
        headphone.setWarranty(headphonesDTO.getWarranty());
        headphone.setBluetooth(headphonesDTO.getBluetooth());
        headphone.setAux(headphonesDTO.getAux());
        headphone.setMicrophoneBuiltIn(headphonesDTO.getMicrophoneBuiltIn());
        headphone.setColor(headphonesDTO.getColor());
        headphone.setHeadphoneType(headphonesDTO.getHeadphonesType());
        headphone.setAnc(headphonesDTO.getAnc());
        headphone.setStock(headphonesDTO.getStock());
        headphone.setId(headphonesDTO.getId());
        
        return headphone;
    }
}
