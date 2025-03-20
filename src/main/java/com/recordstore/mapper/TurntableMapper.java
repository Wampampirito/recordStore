package com.recordstore.mapper;

import org.springframework.stereotype.Component;

import com.recordstore.dto.TurntableDTO;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Turntable;

/**
 * Mapper class for converting between {@link Turntable} entities and {@link TurntableDTO} objects.
 * <p>
 * This class is responsible for mapping fields between the entity and the DTO,
 * ensuring proper data transformation when handling turntable-related operations.
 * </p>
 * <p>
 * It inherits fields from the {@link com.recordstore.model.Product} and {@link com.recordstore.model.Player} classes, and also 
 * includes specific attributes related to turntables.
 * </p>
 * <p>
 * Main methods:
 * </p>
 * <ul>
 *   <li><b>toDTO</b>: Converts a {@link Turntable} entity into a {@link TurntableDTO} object.</li>
 *   <li><b>toEntity</b>: Converts a {@link TurntableDTO} object into a {@link Turntable} entity.</li>
 * </ul>
 * 
 * <p>Usage example:</p>
 * <pre>
 * // Convert a Turntable entity to a TurntableDTO
 * TurntableDTO turntableDTO = turntableMapper.toDTO(turntable);
 * 
 * // Convert a TurntableDTO to a Turntable entity
 * Turntable turntableEntity = turntableMapper.toEntity(turntableDTO);
 * </pre>
 */
@Component
public class TurntableMapper {

    /**
     * Converts a {@link Turntable} entity to a {@link TurntableDTO}.
     *
     * @param turntable The {@link Turntable} entity to be converted.
     * @return The corresponding {@link TurntableDTO} object.
     */
    public TurntableDTO toDTO(Turntable turntable) {
        TurntableDTO dto = new TurntableDTO();

        // Fields inherited from Product
        dto.setId(turntable.getId());
        dto.setName(turntable.getName());
        dto.setPrice(turntable.getPrice());
        dto.setStock(turntable.getStock());
        dto.setProductCategory(PRODUCT_CATEGORY.P_TURNTABLE);

        // Fields inherited from Player
        dto.setBrand(turntable.getBrand());
        dto.setColor(turntable.getColor());
        dto.setWarranty(turntable.getWarranty());
        dto.setBluetooth(turntable.getBluetooth());
        dto.setUsb(turntable.getUsb());
        dto.setRadio(turntable.getRadio());
        dto.setAux(turntable.getAux());
        dto.setRca(turntable.getRca());
        dto.setBuiltInSpeaker(turntable.getBuiltInSpeaker());

        // Fields specific to Turntable
        dto.setHasBuiltInPreAmp(turntable.getHasBuiltInPreAmp());
        dto.setRpm(turntable.getRpm());
        dto.setTraction(turntable.getTraction());
        dto.setMechanism(turntable.getMechanism());

        return dto;
    }

    /**
     * Converts a {@link TurntableDTO} to a {@link Turntable} entity.
     *
     * @param dto The {@link TurntableDTO} to be converted.
     * @return The corresponding {@link Turntable} entity.
     */
    public Turntable toEntity(TurntableDTO dto) {
        Turntable turntable = new Turntable();

        // Fields inherited from Product
        turntable.setId(dto.getId());
        turntable.setName(dto.getName());
        turntable.setPrice(dto.getPrice());
        turntable.setStock(dto.getStock());
        turntable.setProductCategory(PRODUCT_CATEGORY.P_TURNTABLE);

        // Fields inherited from Player
        turntable.setBrand(dto.getBrand());
        turntable.setColor(dto.getColor());
        turntable.setWarranty(dto.getWarranty());
        turntable.setBluetooth(dto.getBluetooth());
        turntable.setUsb(dto.getUsb());
        turntable.setRadio(dto.getRadio());
        turntable.setAux(dto.getAux());
        turntable.setRca(dto.getRca());
        turntable.setBuiltInSpeaker(dto.getBuiltInSpeaker());

        // Fields specific to Turntable
        turntable.setHasBuiltInPreAmp(dto.getHasBuiltInPreAmp());
        turntable.setRpm(dto.getRpm());
        turntable.setTraction(dto.getTraction());
        turntable.setMechanism(dto.getMechanism());

        return turntable;
    }
}
