package com.recordstore.dto;

import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) that represents an album in the system.
 * <p>
 * This DTO is used to transfer album-related data between different layers of the application,
 * such as the service and the controller. It contains the essential attributes of an album, including 
 * the artist, year of release, format, genre, and duration.
 * </p>
 * 
 * It extends from {@link ProductDTO} to include common attributes for products, while adding specific
 * fields for albums.
 * 
 * Example of use:
 * <pre>
 * AlbumDTO albumDTO = new AlbumDTO();
 * albumDTO.setArtist("The Beatles");
 * albumDTO.setYear(1967);
 * albumDTO.setFormat(ALBUM_FORMAT.VINYL);
 * albumDTO.setGenre(ALBUM_GENRE.ROCK);
 * albumDTO.setDuration("42:00");
 * </pre>
 * 
 * @see ProductDTO
 * @see ALBUM_FORMAT
 * @see ALBUM_GENRE
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AlbumDTO extends ProductDTO {
    private String artist;
    private int year;
    private ALBUM_FORMAT format;
    private ALBUM_GENRE genre;
    private String duration;
}

