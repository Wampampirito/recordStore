package com.recordstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Album;

/**
 * Repository interface for {@link Album} entity.
 * This interface extends {@link JpaRepository} to provide basic CRUD operations 
 * and custom queries for albums in the record store.
 */
public interface AlbumRepository extends JpaRepository<Album, Double> {

    /**
     * Finds all albums by artist, ignoring case.
     * 
     * @param artist The name of the artist.
     * @return A list of albums by the specified artist.
     */
    List<Album> findByArtistIgnoreCase(String artist);

    /**
     * Finds all albums within a range of release years.
     * 
     * @param startYear The starting year of the range.
     * @param endYear The ending year of the range.
     * @return A list of albums released between the specified years.
     */
    List<Album> findByYearBetween(int startYear, int endYear);

    /**
     * Finds all albums by genre.
     * 
     * @param genre The genre of the albums to search for.
     * @return A list of albums of the specified genre.
     */
    List<Album> findByGenre(ALBUM_GENRE genre);

    /**
     * Finds all albums by format.
     * 
     * @param format The format of the albums (e.g., LP, EP).
     * @return A list of albums of the specified format.
     */
    List<Album> findByFormat(ALBUM_FORMAT format);

    /**
     * Finds all albums whose price is within a specified range.
     * 
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of albums within the specified price range.
     */
    List<Album> findByPriceBetween(Double minPrice, Double maxPrice);

    /**
     * Finds all albums whose duration is within a specified range.
     * 
     * @param minDuration The minimum duration (in "mm:ss" format).
     * @param maxDuration The maximum duration (in "mm:ss" format).
     * @return A list of albums within the specified duration range.
     */
    List<Album> findByDurationBetween(String minDuration, String maxDuration);

    /**
     * Finds all albums by product category.
     * 
     * @param category The product category to filter albums by.
     * @return A list of albums of the specified product category.
     */
    List<Album> findByProductCategory(PRODUCT_CATEGORY category);

    /**
     * Finds all albums that are in stock (stock greater than 0).
     * 
     * @param i The stock level.
     * @return A list of albums with stock greater than the specified value.
     */
    List<Album> findByStockGreaterThan(int i);

    /**
     * Counts the number of albums by artist, ignoring case.
     * 
     * @param artist The name of the artist.
     * @return The number of albums by the specified artist.
     */
    Integer countByArtistIgnoreCase(String artist);

    /**
     * Counts the number of albums by genre.
     * 
     * @param genre The genre of the albums.
     * @return The number of albums of the specified genre.
     */
    Integer countByGenre(ALBUM_GENRE genre);

    /**
     * Counts the number of albums by format.
     * 
     * @param format The format of the albums.
     * @return The number of albums of the specified format.
     */
    Integer countByFormat(ALBUM_FORMAT format);

    /**
     * Finds albums by name, allowing partial search (case-insensitive).
     * 
     * @param name The name or part of the name of the album.
     * @return A list of albums whose name contains the specified string.
     */
    List<Album> findByNameContainingIgnoreCase(String name);

    /**
     * Counts the number of albums by artist, case-sensitive.
     * 
     * @param artist The name of the artist.
     * @return The number of albums by the specified artist.
     */
    Integer countByArtist(String artist);

    /**
     * Finds all albums by artist, case-sensitive.
     * 
     * @param artist The name of the artist.
     * @return A list of albums by the specified artist.
     */
    List<Album> findByArtist(String artist);

    void deleteById(Integer id);
}
