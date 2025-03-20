package com.recordstore.mapper;

import org.springframework.stereotype.Component;

import com.recordstore.dto.VinylDTO;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Vinyl;

/**
 * Mapper for converting {@link Vinyl} entities to {@link VinylDTO} and vice versa.
 * This class is responsible for mapping all the relevant properties from the entity to the DTO
 * and from the DTO back to the entity.
 */
@Component
public class VinylMapper {

    /**
     * Converts a {@link Vinyl} entity to a {@link VinylDTO}.
     *
     * @param vinyl The {@link Vinyl} entity to be converted.
     * @return The {@link VinylDTO} representation of the provided entity.
     */
    public VinylDTO toDTO(Vinyl vinyl) {
        VinylDTO vinylDTO = new VinylDTO();

        // Mapping properties from Vinyl entity to VinylDTO
        vinylDTO.setId(vinyl.getId());
        vinylDTO.setName(vinyl.getName());
        vinylDTO.setPrice(vinyl.getPrice());
        vinylDTO.setStock(vinyl.getStock());
        vinylDTO.setProductCategory(PRODUCT_CATEGORY.A_VINYL);
        
        // Mapping specific attributes for Vinyl
        vinylDTO.setSize(vinyl.getSize());
        vinylDTO.setRpm(vinyl.getRpm());
        vinylDTO.setColor(vinyl.getColor());
      
        // Mapping inherited properties from AlbumDTO (inherited in VinylDTO)
        vinylDTO.setArtist(vinyl.getArtist());
        vinylDTO.setYear(vinyl.getYear());
        vinylDTO.setFormat(vinyl.getFormat());
        vinylDTO.setGenre(vinyl.getGenre());
        vinylDTO.setDuration(vinyl.getDuration());

        return vinylDTO;
    }

    /**
     * Converts a {@link VinylDTO} to a {@link Vinyl} entity.
     *
     * @param vinylDTO The {@link VinylDTO} to be converted.
     * @return The {@link Vinyl} entity representation of the provided DTO.
     */
    public Vinyl toEntity(VinylDTO vinylDTO) {
        Vinyl vinyl = new Vinyl();

        // Mapping properties from VinylDTO to Vinyl entity
        vinyl.setId(vinylDTO.getId());
        vinyl.setName(vinylDTO.getName());
        vinyl.setPrice(vinylDTO.getPrice());
        vinyl.setStock(vinylDTO.getStock());
        vinyl.setProductCategory(PRODUCT_CATEGORY.A_VINYL);
        
        // Mapping specific attributes for Vinyl
        vinyl.setSize(vinylDTO.getSize());
        vinyl.setRpm(vinylDTO.getRpm());
        vinyl.setColor(vinylDTO.getColor());

        // Mapping inherited properties from AlbumDTO to Vinyl entity
        vinyl.setArtist(vinylDTO.getArtist());
        vinyl.setYear(vinylDTO.getYear());
        vinyl.setFormat(vinylDTO.getFormat());
        
        vinyl.setGenre(vinylDTO.getGenre());
        vinyl.setDuration(vinylDTO.getDuration());

        return vinyl;
    }
}
