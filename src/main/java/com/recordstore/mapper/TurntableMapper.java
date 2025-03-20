package com.recordstore.mapper;

import org.springframework.stereotype.Component;

import com.recordstore.dto.TurntableDTO;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Turntable;

/**
 * Mapper class for converting between {@link Turntable} entities and {@link TurntableDTO} objects.
 * <p>
 * This class is responsible for mapping the fields between the entity and the DTO,
 * ensuring proper data transformation when handling turntable-related operations.
 * </p>
 * <p>
 * It inherits fields from the {@link Product} and {@link Player} classes and also 
 * includes specific attributes related to turntables.
 * </p>
 */
@Component
public class TurntableMapper {

    /**
     * Converts a {@link Turntable} entity to a {@link TurntableDTO}.
     *
     * @param turntable The turntable entity to convert.
     * @return The corresponding DTO representation.
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
     * @param dto The DTO to convert.
     * @return The corresponding turntable entity.
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
