package com.recordstore.model;

import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.enums.VINYL_RPM;
import com.recordstore.enums.VINYL_SIZE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a vinyl record in the record store. A vinyl is a type of album that has additional specific attributes
 * like size, RPM (revolutions per minute), and color, which are unique to vinyl records.
 * 
 * This class extends the {@link Album} class, so it inherits the properties of an album such as the name, price, stock,
 * artist, release year, format, genre, and duration. In addition to these inherited properties, a vinyl also has specific
 * fields such as size, RPM, and color.
 * 
 * The size of the vinyl is represented using the {@link VINYL_SIZE} enum, the RPM using the {@link VINYL_RPM} enum,
 * and the color is represented as a string.
 * 
 * Example usage:
 * <pre>
 * Vinyl vinyl = new Vinyl("Abbey Road", 29.99, 100, "The Beatles", 1969, ALBUM_FORMAT.LP, ALBUM_GENRE.ROCK, "47:23", 
 *                         VINYL_SIZE.LP, VINYL_RPM.RPM_33, "Black");
 * </pre>
 * 
 * @see Album
 * @see VINYL_SIZE
 * @see VINYL_RPM
 * @see PRODUCT_CATEGORY
 */
@Entity
@Table(name = "vinyl")
@Data
@NoArgsConstructor 
@AllArgsConstructor
@EqualsAndHashCode(callSuper=true)
public class Vinyl extends Album {

    /**
     * The size of the vinyl record (e.g., LP, 7-inch, etc.).
     */
    @Column(name = "size")
    private VINYL_SIZE size;

    /**
     * The RPM (revolutions per minute) of the vinyl. Valid values are defined in the {@link VINYL_RPM} enum.
     */
    @Column(name = "speed")
    private VINYL_RPM rpm;

    /**
     * The color of the vinyl.
     */
    @Column(name = "color")
    private String color;

    /**
     * Constructor to create a new Vinyl record with specified details.
     * 
     * @param name The name of the vinyl.
     * @param price The price of the vinyl.
     * @param stock The stock quantity of the vinyl.
     * @param artist The artist who released the vinyl.
     * @param year The release year of the vinyl.
     * @param format The format of the album (e.g., LP, EP) from the {@link ALBUM_FORMAT} enum.
     * @param genre The genre of the album from the {@link ALBUM_GENRE} enum.
     * @param duration The duration of the album in "mm:ss" format.
     * @param size The size of the vinyl, defined in the {@link VINYL_SIZE} enum.
     * @param rpm The RPM (revolutions per minute) of the vinyl, defined in the {@link VINYL_RPM} enum.
     * @param color The color of the vinyl.
     */
    public Vinyl(String name, Double price, Integer stock, String artist, Integer year, ALBUM_FORMAT format, ALBUM_GENRE genre,
                 String duration, VINYL_SIZE size, VINYL_RPM rpm, String color) {
        super(name, price, stock, artist, year, format, genre, duration);
        super.setProductCategory(PRODUCT_CATEGORY.A_VINYL);
        this.size = size;
        this.rpm = rpm;
        this.color = color;
    }
}
