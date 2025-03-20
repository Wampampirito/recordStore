package com.recordstore.service;

import com.recordstore.model.Album;
import com.recordstore.model.Order;
import com.recordstore.model.Wishlist;
import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.repository.AlbumRepository;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.WishlistRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing albums in the system.
 * This service provides methods to get, save, update, and delete albums.
 */
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private OrderRepository orderRepository;
    private WishlistRepository wishlistRepository;

    /**
     * Constructor for the service that injects the album repository.
     * 
     * @param albumRepository The album repository to inject.
     */
    @Autowired
    public AlbumService(AlbumRepository albumRepository, OrderRepository orderRepository, WishlistRepository wishlistRepository) {
        this.albumRepository = albumRepository;
        this.orderRepository = orderRepository;
        this.wishlistRepository = wishlistRepository;
    }

    /**
     * Retrieves all albums available in the system.
     * 
     * @return List of all albums available in the system.
     */
    public List<Album> getAllAlbums() {
        return albumRepository.findAll();
    }

    /**
     * Retrieves an album by its id (ID).
     * 
     * @param id The id of the album to search for.
     * @return An {@link java.util.Optional Optional} with the album found, or empty
     *         if not found.
     */
    public Optional<Album> getAlbumById(Double id) {
        return albumRepository.findById(id);
    }

    /**
     * Retrieves all albums by a specific artist.
     * 
     * @param artist The name of the artist.
     * @return List of albums by the artist.
     */
    public List<Album> getAlbumsByArtist(String artist) {
        return albumRepository.findByArtist(artist);
    }

    /**
     * Retrieves all albums released within a year range.
     * 
     * @param startYear The start year.
     * @param endYear   The end year.
     * @return List of albums released between the specified years.
     */
    public List<Album> getAlbumsByYearRange(int startYear, int endYear) {
        return albumRepository.findByYearBetween(startYear, endYear);
    }

    /**
     * Retrieves all albums by a specific genre.
     * 
     * @param genre The genre of the albums to search for.
     * @return List of albums of the specified genre.
     */
    public List<Album> getAlbumsByGenre(ALBUM_GENRE genre) {
        return albumRepository.findByGenre(genre);
    }

    /**
     * Retrieves all albums by a specific format.
     * 
     * @param format The format of the albums to search for (LP, EP, etc.).
     * @return List of albums of the specified format.
     */
    public List<Album> getAlbumsByFormat(ALBUM_FORMAT format) {
        return albumRepository.findByFormat(format);
    }

    /**
     * Retrieves all albums whose price is within a specified range.
     * 
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return List of albums within the specified price range.
     */
    public List<Album> getAlbumsByPriceRange(Double minPrice, Double maxPrice) {
        return albumRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Retrieves all albums whose duration is within a specified range.
     * 
     * @param minDuration The minimum duration (in "mm:ss" format).
     * @param maxDuration The maximum duration (in "mm:ss" format).
     * @return List of albums whose duration is within the specified range.
     */
    public List<Album> getAlbumsByDuration(String minDuration, String maxDuration) {
        return albumRepository.findByDurationBetween(minDuration, maxDuration);
    }

    /**
     * Saves a new album in the system.
     * 
     * @param album The album to save.
     * @return The saved album with its assigned id.
     */
    public Album saveAlbum(Album album) {
        validateYear(album.getYear()); // Validate the album before saving
        return albumRepository.save(album);
    }

    /**
     * Deletes an album by its id (ID).
     * 
     * @param id The id of the album to delete.
     */
    @Transactional
    public void deleteAlbum(Integer id) {

                // Check if the product is associated with any order
        Optional<Order> ordersWithProduct = orderRepository.findById(id);
        if (!ordersWithProduct.isEmpty()) {
            throw new IllegalArgumentException("Cannot delete the product because it is associated with an order.");
        }

        // Check if the product is associated with any wishlist
        List<Wishlist> wishlistsWithProduct = wishlistRepository.findWishlistsByProductId(id);
        if (!wishlistsWithProduct.isEmpty()) {
            throw new IllegalArgumentException("Cannot delete the product because it is in a wishlist.");
        }
        albumRepository.deleteById(id);
    }

    /**
     * Updates the details of an existing album, only modifying fields that have
     * been provided.
     * If a field is not provided, the existing value will remain unchanged.
     * 
     * @param id           The id of the album to be updated.
     * @param updatedAlbum The album with the updated details.
     * @return The updated album.
     */
    public Album updateAlbum(Double id, Album updatedAlbum) {
        // Check if the album exists
        Optional<Album> existingAlbumOpt = albumRepository.findById(id);
        if (existingAlbumOpt.isPresent()) {
            Album existingAlbum = existingAlbumOpt.get();

            // Validate and update the release year if it's provided
            if (updatedAlbum.getYear() != 0) { // Assuming year = 0 means it's not provided
                updateAlbumReleaseYear(existingAlbum, updatedAlbum.getYear());
    
            }

            // Update other fields only if they are provided (non-null or non-empty)
            if (updatedAlbum.getName() != null && !updatedAlbum.getName().isEmpty()) {
                existingAlbum.setName(updatedAlbum.getName());
            }
            if (updatedAlbum.getPrice() > 0) { // Assuming price > 0 means it's provided
                existingAlbum.setPrice(updatedAlbum.getPrice());
            }
            if (updatedAlbum.getStock() >= 0) { // Assuming stock >= 0 means it's provided
                existingAlbum.setStock(updatedAlbum.getStock());
            }
            if (updatedAlbum.getArtist() != null && !updatedAlbum.getArtist().isEmpty()) {
                existingAlbum.setArtist(updatedAlbum.getArtist());
            }
            if (updatedAlbum.getGenre() != null) {
                existingAlbum.setGenre(updatedAlbum.getGenre());
            }
            if (updatedAlbum.getFormat() != null) {
                existingAlbum.setFormat(updatedAlbum.getFormat());
            }
            if (updatedAlbum.getDuration() != null && !updatedAlbum.getDuration().isEmpty()) {
                existingAlbum.setDuration(updatedAlbum.getDuration());
            }

            // Save the updated album
            return albumRepository.save(existingAlbum);
        } else {
            throw new IllegalArgumentException("Album with id " + id + " not found.");
        }
    }

    /**
     * Validates album data before saving or updating.
     * 
     * @param album The album to validate.
     * @throws IllegalArgumentException If the data is not valid.
     */
    public void validateYear(int year) throws IllegalArgumentException {
        if (year < 1860 || year > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("The year must be between 1860 and the current year.");
        }
        // Additional validation logic can go here (e.g., for price, duration, etc.)
    }

    /**
     * Updates the release year of the album if it's within a valid range.
     * 
     * @param album The album instance to be updated.
     * @param year  The release year of the album.
     * @throws IllegalArgumentException If the year is not valid.
     */
    private void updateAlbumReleaseYear(Album album, int year) throws IllegalArgumentException {
        validateYear(year);
        album.setYear(year); // Update the year of the album
    }

    /**
     * Retrieves all albums of a specific product category.
     * 
     * @param category The product category to filter albums by.
     * @return List of albums of the specified category.
     */
    public List<Album> getAlbumsByCategory(PRODUCT_CATEGORY category) {
        return albumRepository.findByProductCategory(category);
    }

    /**
     * Retrieves all albums that are in stock (at least 1 unit available).
     * 
     * @return List of albums that are in stock.
     */
    public List<Album> getAlbumsInStock() {
        return albumRepository.findByStockGreaterThan(0);
    }

    /**
     * Counts the number of albums by a specific artist.
     * 
     * @param artist The name of the artist.
     * @return The number of albums by the artist.
     */
    public Double countAlbumsByArtist(String artist) {
        return albumRepository.countByArtist(artist);
    }

    /**
     * Counts the number of albums by a specific genre.
     * 
     * @param genre The genre of the albums.
     * @return The number of albums of that genre.
     */
    public Double countAlbumsByGenre(ALBUM_GENRE genre) {
        return albumRepository.countByGenre(genre);
    }

    /**
     * Counts the number of albums by a specific format.
     * 
     * @param format The format of the albums.
     * @return The number of albums of that format.
     */
    public Double countAlbumsByFormat(ALBUM_FORMAT format) {
        return albumRepository.countByFormat(format);
    }

    /**
     * Finds albums by name, allowing partial search.
     * 
     * @param name The name of the album (can be partial).
     * @return List of albums whose name contains the provided string.
     */
    public List<Album> findAlbumsByName(String name) {
        return albumRepository.findByNameContainingIgnoreCase(name);
    }
}
