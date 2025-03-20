package com.recordstore.mapper;

import com.recordstore.dto.AlbumDTO;
import com.recordstore.model.Album;
import com.recordstore.enums.PRODUCT_CATEGORY;

/**
 * Mapper class for converting {@link Album} entities to {@link AlbumDTO} and vice versa.
 * This class is responsible for mapping the properties from the entity to the DTO and
 * from the DTO back to the entity.
 */
public class AlbumMapper {

    /**
     * Converts an {@link Album} entity to an {@link AlbumDTO}.
     *
     * @param album The {@link Album} entity to be converted.
     * @return The {@link AlbumDTO} representation of the provided entity, or {@code null} if the input is {@code null}.
     */
    public AlbumDTO toDTO(Album album) {
        if (album == null) {
            return null;
        }
        AlbumDTO albumDTO = new AlbumDTO();
        
        // Mapping properties from Album entity to AlbumDTO
        albumDTO.setName(album.getName());
        albumDTO.setPrice(album.getPrice());
        albumDTO.setStock(album.getStock());
        albumDTO.setArtist(album.getArtist());
        albumDTO.setYear(album.getYear());
        albumDTO.setFormat(album.getFormat());
        albumDTO.setGenre(album.getGenre());
        albumDTO.setDuration(album.getDuration());
        albumDTO.setProductCategory(PRODUCT_CATEGORY.ALBUM);
        albumDTO.setId(album.getId());
        
        return albumDTO;
    }

    /**
     * Converts an {@link AlbumDTO} to an {@link Album} entity.
     *
     * @param dto The {@link AlbumDTO} to be converted.
     * @return The {@link Album} entity representation of the provided DTO, or {@code null} if the input is {@code null}.
     */
    public static Album toEntity(AlbumDTO dto) {
        if (dto == null) {
            return null;
        }
        
        // Mapping properties from AlbumDTO to Album entity
        return new Album(
                dto.getName(),
                dto.getPrice(),
                dto.getStock(),
                dto.getArtist(),
                dto.getYear(),
                dto.getFormat(),
                dto.getGenre(),
                dto.getDuration()
        );
    }
}
