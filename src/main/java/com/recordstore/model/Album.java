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
 * Additionally, the album has extra fields specific to musical albums, such as artist, release year, format,
 * genre, and duration. These additional attributes help to define an album's identity and provide details relevant 
 * for the store's inventory.
 * 
 * The album can be associated with a specific format and genre through the enumerations {@link ALBUM_FORMAT} and
 * {@link ALBUM_GENRE}, respectively. The duration of the album is stored as a string in the format "MM:SS" (minutes:seconds),
 * providing a quick way to understand the length of the album.
 * 
 * Example of usage:
 * <pre>
 * Album album = new Album("Abbey Road", 29.99, 100, "The Beatles", 1969, ALBUM_FORMAT.LP, ALBUM_GENRE.ROCK, "47:23");
 * </pre>
 * This example creates an album named "Abbey Road" by "The Beatles" in the format "LP", released in 1969, belonging 
 * to the "Rock" genre, and with a total duration of "47:23".
 * 
 * The {@link Product} class provides common product fields like name, price, and stock, while the {@link ALBUM_FORMAT}
 * and {@link ALBUM_GENRE} enums provide standardized values for format and genre.
 * 
 * This class is used to manage and represent albums within the record store, allowing users to add, view, and filter albums
 * based on their properties.
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
     * This is a textual field that holds the name of the performing artist or band.
     * Example: "The Beatles"
     */
    @Column(name = "artist")
    private String artist;

    /**
     * The release year of the album.
     * This field represents the year in which the album was officially released.
     * The value must be between 1860 and the current year.
     * Example: 1969
     */
    @Column(name = "release_year")
    private Integer year;

    /**
     * The format of the album (e.g., LP, EP, CD, etc.).
     * This field is represented by the enum {@link ALBUM_FORMAT}, which defines the possible formats an album can have.
     * Example: ALBUM_FORMAT.LP
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "format")
    private ALBUM_FORMAT format;

    /**
     * The genre of the album (e.g., Rock, Pop, Jazz, etc.).
     * This field is represented by the enum {@link ALBUM_GENRE}, which defines the possible musical genres an album can belong to.
     * Example: ALBUM_GENRE.ROCK
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "genre")
    private ALBUM_GENRE genre;

    /**
     * The total duration of the album in text format (e.g., "47:23").
     * This field represents the length of the album in minutes and seconds as a string.
     * Example: "47:23"
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
