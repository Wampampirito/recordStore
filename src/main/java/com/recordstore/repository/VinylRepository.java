package com.recordstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.model.Vinyl;

public interface VinylRepository extends JpaRepository<Vinyl, Double> {

    List<Vinyl> findByYearBetween(int startYear, int endYear);

    List<Vinyl> findByGenre(ALBUM_GENRE genre);

    List<Vinyl> findByFormat(ALBUM_FORMAT format);

    List<Vinyl> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Vinyl> findByDurationBetween(String minDuration, String maxDuration);

    List<Vinyl> findByProductCategory(PRODUCT_CATEGORY category);

    List<Vinyl> findByStockGreaterThan(Integer i);

    Double countByArtist(String artist);

    Double countByGenre(ALBUM_GENRE genre);

    Double countByFormat(ALBUM_FORMAT format);

    List<Vinyl> findByNameContainingIgnoreCase(String name);

    List<Vinyl> findByArtist(String artist);

    @NonNull
    Optional<Vinyl> findById(@NonNull Double id);

    void deleteById(Integer id);

}
