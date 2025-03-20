package com.recordstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Vinyl;

/**
 * Repository interface for managing {@link Vinyl} entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for performing basic CRUD operations 
 * as well as custom queries on {@link Vinyl} entities. The repository includes methods for filtering 
 * vinyl records based on various attributes such as genre, format, price range, duration, and more.
 * </p>
 */
public interface VinylRepository extends JpaRepository<Vinyl, Integer> {

    /**
     * Finds vinyl records released between the specified years.
     * 
     * @param startYear The starting year of the range.
     * @param endYear The ending year of the range.
     * @return A list of {@link Vinyl} records released between the specified years.
     */
    List<Vinyl> findByYearBetween(int startYear, int endYear);

    /**
     * Finds vinyl records by their genre.
     * 
     * @param genre The genre of the vinyl records to search for.
     * @return A list of {@link Vinyl} records that match the specified genre.
     */
    List<Vinyl> findByGenre(ALBUM_GENRE genre);

    /**
     * Finds vinyl records by their format.
     * 
     * @param format The format of the vinyl records (e.g., LP, EP).
     * @return A list of {@link Vinyl} records that match the specified format.
     */
    List<Vinyl> findByFormat(ALBUM_FORMAT format);

    /**
     * Finds vinyl records within a specified price range.
     * 
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of {@link Vinyl} records whose price falls within the specified range.
     */
    List<Vinyl> findByPriceBetween(Double minPrice, Double maxPrice);

    /**
     * Finds vinyl records with a duration between the specified values.
     * 
     * @param minDuration The minimum duration (in minutes).
     * @param maxDuration The maximum duration (in minutes).
     * @return A list of {@link Vinyl} records with a duration within the specified range.
     */
    List<Vinyl> findByDurationBetween(String minDuration, String maxDuration);

    /**
     * Finds vinyl records by product category.
     * 
     * @param category The product category of the vinyl records to search for.
     * @return A list of {@link Vinyl} records that match the specified category.
     */
    List<Vinyl> findByProductCategory(PRODUCT_CATEGORY category);

    /**
     * Finds vinyl records with stock greater than the specified value.
     * 
     * @param i The minimum stock quantity.
     * @return A list of {@link Vinyl} records with stock greater than the specified value.
     */
    List<Vinyl> findByStockGreaterThan(Integer i);

    /**
     * Counts the number of vinyl records by artist.
     * 
     * @param artist The artist whose vinyl records are to be counted.
     * @return The number of vinyl records by the specified artist.
     */
    Integer countByArtist(String artist);

    /**
     * Counts the number of vinyl records by genre.
     * 
     * @param genre The genre whose vinyl records are to be counted.
     * @return The number of vinyl records in the specified genre.
     */
    Integer countByGenre(ALBUM_GENRE genre);

    /**
     * Counts the number of vinyl records by format.
     * 
     * @param format The format whose vinyl records are to be counted.
     * @return The number of vinyl records in the specified format.
     */
    Integer countByFormat(ALBUM_FORMAT format);

    /**
     * Finds vinyl records by name, case insensitive.
     * 
     * @param name The name of the vinyl records to search for (partial matches allowed).
     * @return A list of {@link Vinyl} records whose name contains the specified string (case insensitive).
     */
    List<Vinyl> findByNameContainingIgnoreCase(String name);

    /**
     * Finds vinyl records by artist.
     * 
     * @param artist The artist of the vinyl records to search for.
     * @return A list of {@link Vinyl} records by the specified artist.
     */
    List<Vinyl> findByArtist(String artist);
}
