package com.recordstore.mapper;

import org.springframework.stereotype.Component;

import com.recordstore.dto.HeadphonesDTO;
import com.recordstore.model.Headphone;

@Component
public class HeadphoneMapper {

    public HeadphonesDTO toDTO (Headphone headphone) {
        HeadphonesDTO headphonesDTO = new HeadphonesDTO();
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

    public Headphone toEntity(HeadphonesDTO headphonesDTO) {
        Headphone headphone = new Headphone();
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
