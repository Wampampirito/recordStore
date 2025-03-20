package com.recordstore.model;

import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents an album in the record store. An album is a product that contains information related
 * to the artist, release year, format, genre, and duration.
 * 
 * This class inherits from the {@link Product} class, so it shares properties like name, price, and stock.
 * Additionally, the album has extra fields like artist, release year, format, genre, and duration.
 * 
 * The album can be associated with a specific format and genre through the enumerations {@link ALBUM_FORMAT} and
 * {@link ALBUM_GENRE}, respectively.
 * 
 * Example of usage:
 * <pre>
 * Album album = new Album("Abbey Road", 29.99, 100, "The Beatles", 1969, ALBUM_FORMAT.LP, ALBUM_GENRE.ROCK, "47:23");
 * </pre>
 * 
 * @see Product
 * @see ALBUM_FORMAT
 * @see ALBUM_GENRE
 * @see PRODUCT_CATEGORY
 */
@Entity
@Table(name = "album")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Album extends Product {

    /**
     * The name of the artist who released the album.
     */
    @Column(name = "artist")
    private String artist;

    /**
     * The release year of the album. It must be between 1860 and the current year.
     */
    @Column(name = "release_year")
    private Integer year;

    /**
     * The format of the album (e.g., LP, EP, CD, etc.). It is represented by the enum {@link ALBUM_FORMAT}.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private ALBUM_FORMAT format;

    /**
     * The genre of the album (e.g., Rock, Pop, Jazz, etc.). It is represented by the enum {@link ALBUM_GENRE}.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private ALBUM_GENRE genre;

    /**
     * The total duration of the album in text format (e.g., "47:23").
     */
    @Column(name = "duration")
    private String duration;

    /**
     * Constructs a new Album with the specified details.
     * 
     * @param name The name of the album.
     * @param price The price of the album.
     * @param stock The stock quantity of the album.
     * @param artist The name of the artist who released the album.
     * @param year The release year of the album. It must be between 1860 and the current year.
     * @param format The format of the album (e.g., LP, EP, CD, etc.), represented by the enum {@link ALBUM_FORMAT}.
     * @param genre The genre of the album (e.g., Rock, Pop, Jazz, etc.), represented by the enum {@link ALBUM_GENRE}.
     * @param duration The total duration of the album in text format (e.g., "47:23").
     */
    public Album(String name, Double price, Integer stock, String artist, Integer year, ALBUM_FORMAT format, ALBUM_GENRE genre, String duration) {
        super(name, price, stock);
        super.setProductCategory(PRODUCT_CATEGORY.ALBUM);
        this.artist = artist;
        this.year = year;
        this.format = format;
        this.genre = genre;
        this.duration = duration;
    }

}
