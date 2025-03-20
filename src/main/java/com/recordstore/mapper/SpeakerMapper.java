package com.recordstore.mapper;

import com.recordstore.model.Speaker;
import com.recordstore.dto.SpeakerDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper class responsible for converting between {@link com.recordstore.model.Speaker} entity and 
 * {@link com.recordstore.dto.SpeakerDTO}.
 * <p>This class provides methods to convert a {@link com.recordstore.model.Speaker} entity to 
 * a {@link com.recordstore.dto.SpeakerDTO} and vice versa.</p>
 * 
 * <p>Main methods:</p>
 * <ul>
 *   <li><b>toDTO</b>: Converts a {@link com.recordstore.model.Speaker} entity into a {@link com.recordstore.dto.SpeakerDTO} object.</li>
 *   <li><b>toEntity</b>: Converts a {@link com.recordstore.dto.SpeakerDTO} object into a {@link com.recordstore.model.Speaker} entity.</li>
 * </ul>
 * 
 * <p>Usage example:</p>
 * <pre>
 * // Convert a Speaker entity to a SpeakerDTO
 * SpeakerDTO speakerDTO = speakerMapper.toDTO(speaker);
 * 
 * // Convert a SpeakerDTO to a Speaker entity
 * Speaker speakerEntity = speakerMapper.toEntity(speakerDTO);
 * </pre>
 */
@Component
public class SpeakerMapper {

    /**
     * Converts a {@link com.recordstore.model.Speaker} entity to a {@link com.recordstore.dto.SpeakerDTO}.
     *
     * @param speaker The {@link com.recordstore.model.Speaker} entity to be converted.
     * @return The corresponding {@link com.recordstore.dto.SpeakerDTO} object.
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
     * Converts a {@link com.recordstore.dto.SpeakerDTO} object to a {@link com.recordstore.model.Speaker} entity.
     *
     * @param speakerDTO The {@link com.recordstore.dto.SpeakerDTO} to be converted.
     * @return The corresponding {@link com.recordstore.model.Speaker} entity.
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
