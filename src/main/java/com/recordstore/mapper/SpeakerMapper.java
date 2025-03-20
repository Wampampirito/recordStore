package com.recordstore.mapper;

import com.recordstore.model.Speaker;
import com.recordstore.dto.SpeakerDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper class that converts between Speaker entity and SpeakerDTO.
 */
@Component
public class SpeakerMapper {

    /**
     * Converts a Speaker entity to a SpeakerDTO.
     *
     * @param speaker the Speaker entity to convert.
     * @return the corresponding SpeakerDTO.
     */
    public SpeakerDTO toDTO(Speaker speaker) {
        SpeakerDTO speakerDTO = new SpeakerDTO();
        speakerDTO.setId(speaker.getId());
        speakerDTO.setBrand(speaker.getBrand());
        speakerDTO.setModel(speaker.getName());
        speakerDTO.setPrice(speaker.getPrice());
        speakerDTO.setPower(speaker.getPower());
        speakerDTO.setImpedance(speaker.getImpedance());
        speakerDTO.setMinFrequency(speaker.getMinFrequency());
        speakerDTO.setMaxFrequency(speaker.getMaxFrequency());
        speakerDTO.setWeight(speaker.getWeight());
        speakerDTO.setPowerType(speaker.getPowerType());
        speakerDTO.setResistance(speaker.getResistance());
        
        // Set AudioEquipment properties
        speakerDTO.setColor(speaker.getColor());
        speakerDTO.setBatteryLife(speaker.getBatteryLife());
        speakerDTO.setWarranty(speaker.getWarranty());
        speakerDTO.setMicrophoneBuiltIn(speaker.getMicrophoneBuiltIn());
        speakerDTO.setWireless(speaker.getWireless());
        speakerDTO.setBluetooth(speaker.getBluetooth());
        speakerDTO.setUsb(speaker.getUsb());
        speakerDTO.setAux(speaker.getAux());

        return speakerDTO;
    }

    /**
     * Converts a SpeakerDTO to a Speaker entity.
     *
     * @param speakerDTO the SpeakerDTO to convert.
     * @return the corresponding Speaker entity.
     */
    public Speaker toEntity(SpeakerDTO speakerDTO) {
        Speaker speaker = new Speaker();
        speaker.setId(speakerDTO.getId());
        speaker.setBrand(speakerDTO.getBrand());
        speaker.setName(speakerDTO.getName());
        speaker.setPrice(speakerDTO.getPrice());
        speaker.setPower(speakerDTO.getPower());
        speaker.setImpedance(speakerDTO.getImpedance());
        speaker.setMinFrequency(speakerDTO.getMinFrequency());
        speaker.setMaxFrequency(speakerDTO.getMaxFrequency());
        speaker.setWeight(speakerDTO.getWeight());
        speaker.setPowerType(speakerDTO.getPowerType());
        speaker.setResistance(speakerDTO.getResistance());

        // Set AudioEquipment properties
        speaker.setColor(speakerDTO.getColor());
        speaker.setBatteryLife(speakerDTO.getBatteryLife());
        speaker.setWarranty(speakerDTO.getWarranty());
        speaker.setMicrophoneBuiltIn(speakerDTO.getMicrophoneBuiltIn());
        speaker.setWireless(speakerDTO.getWireless());
        speaker.setBluetooth(speakerDTO.getBluetooth());
        speaker.setUsb(speakerDTO.getUsb());
        speaker.setAux(speakerDTO.getAux());

        return speaker;
    }
}
