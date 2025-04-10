package com.recordstore.service;

import com.recordstore.model.Album;
import com.recordstore.dto.AlbumDTO;
import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.mapper.AlbumMapper;
import com.recordstore.repository.AlbumRepository;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.WishlistRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for managing albums in the system.
 * This service provides methods to get, save, update, and delete albums.
 */
@Service
public class AlbumService {

    private final AlbumRepository albumRepository;
    private final AlbumMapper albumMapper;
    private final OrderRepository orderRepository;
    private final WishlistRepository wishlistRepository;

    /**
     * Constructor for the service that injects the album repository.
     * 
     * @param albumRepository The album repository to inject.
     * @param albumMapper     The album mapper to inject.
     * @param orderRepository The order repository to inject.
     * @param wishlistRepository The wishlist repository to inject.
     */
    @Autowired
    public AlbumService(AlbumRepository albumRepository, AlbumMapper albumMapper, OrderRepository orderRepository,
            WishlistRepository wishlistRepository) {
        this.albumRepository = albumRepository;
        this.albumMapper = albumMapper;
        this.orderRepository = orderRepository;
        this.wishlistRepository = wishlistRepository;
    }

    /**
     * Retrieves all albums available in the system.
     * 
     * @return List of all albums available in the system.
     */
    public List<AlbumDTO> getAllAlbums() {
        return albumRepository.findAll()
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an album by its id (ID).
     * 
     * @param id The id of the album to search for.
     * @return An {@link java.util.Optional Optional} with the album found, or empty
     *         if not found.
     */
    public Optional<Album> getAlbumById(Integer id) {
        return albumRepository.findById(id);
    }

    /**
     * Retrieves all albums by a specific artist.
     * 
     * @param artist The name of the artist.
     * @return List of albums by the artist.
     */
    public List<AlbumDTO> getAlbumsByArtist(String artist) {
        return albumRepository.findByArtist(artist)
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all albums released within a year range.
     * 
     * @param startYear The start year.
     * @param endYear   The end year.
     * @return List of albums released between the specified years.
     */
    public List<AlbumDTO> getAlbumsByYearRange(int startYear, int endYear) {
        return albumRepository.findByYearBetween(startYear, endYear)
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all albums by a specific genre.
     * 
     * @param genre The genre of the albums to search for.
     * @return List of albums of the specified genre.
     */
    public List<AlbumDTO> getAlbumsByGenre(ALBUM_GENRE genre) {
        return albumRepository.findByGenre(genre)
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all albums by a specific format.
     * 
     * @param format The format of the albums to search for (LP, EP, etc.).
     * @return List of albums of the specified format.
     */
    public List<AlbumDTO> getAlbumsByFormat(ALBUM_FORMAT format) {
        return albumRepository.findByFormat(format)
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all albums whose price is within a specified range.
     * 
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return List of albums within the specified price range.
     */
    public List<AlbumDTO> getAlbumsByPriceRange(Double minPrice, Double maxPrice) {
        return albumRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all albums whose duration is within a specified range.
     * 
     * @param minDuration The minimum duration (in "mm:ss" format).
     * @param maxDuration The maximum duration (in "mm:ss" format).
     * @return List of albums whose duration is within the specified range.
     */
    public List<AlbumDTO> getAlbumsByDuration(String minDuration, String maxDuration) {
        return albumRepository.findByDurationBetween(minDuration, maxDuration)
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new album in the system.
     * 
     * @param album The album to save.
     * @return The saved album with its assigned id.
     */
    public AlbumDTO saveAlbum(Album album) {
        validateYear(album.getYear()); // Validate the album before saving
        return albumMapper.toDTO(albumRepository.save(album));
    }

    /**
     * Deletes an album by its id (ID).
     * 
     * @param id The id of the album to delete.
     */
    @Transactional
    public void deleteAlbum(Integer id) {

      // Check if the turntable is associated with any order
      boolean isInOrders = orderRepository.existsByListOrderProducts_Product_Id(id);
      if (isInOrders) {
          throw new IllegalStateException("Cannot delete the product because it is associated with an order.");
      }
  
      // Check if the turntable is in any wishlist
      boolean isInWishlists = wishlistRepository.existsByListWishlistProducts_Product_Id(id);
      if (isInWishlists) {
          throw new IllegalStateException("Cannot delete the product because it is in a wishlist.");
      }

      // If not in use, delete the album
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
    public AlbumDTO updateAlbum(Integer id, Album updatedAlbum) {
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
            if (updatedAlbum.getPrice() != null) {
                existingAlbum.setPrice(updatedAlbum.getPrice());
            }
            if (updatedAlbum.getStock() != null) {
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
            return albumMapper.toDTO(albumRepository.save(existingAlbum));
        } else {
            throw new IllegalArgumentException("Album with id " + id + " not found.");
        }
    }

    /**
     * Validates album release year before saving or updating.
     * 
     * @param year The year to validate.
     * @throws IllegalArgumentException If the data is not valid.
     */
    public void validateYear(int year) throws IllegalArgumentException {
        if (year < 1860 || year > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("The year must be between 1860 and the current year.");// Its 1860
                                                                                                      // because the
                                                                                                      // first sound
                                                                                                      // recording was
                                                                                                      // in 1860
        }

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
    public List<AlbumDTO> getAlbumsByCategory(PRODUCT_CATEGORY category) {
        return albumRepository.findByProductCategory(category)
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all albums that are in stock (at least 1 unit available).
     * 
     * @return List of albums that are in stock.
     */
    public List<AlbumDTO> getAlbumsInStock() {
        return albumRepository.findByStockGreaterThan(0)
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Counts the number of albums by a specific artist.
     * 
     * @param artist The name of the artist.
     * @return The number of albums by the artist.
     */
    public Integer countAlbumsByArtist(String artist) {
        return albumRepository.countByArtist(artist);
    }

    /**
     * Counts the number of albums by a specific genre.
     * 
     * @param genre The genre of the albums.
     * @return The number of albums of that genre.
     */
    public Integer countAlbumsByGenre(ALBUM_GENRE genre) {
        return albumRepository.countByGenre(genre);
    }

    /**
     * Counts the number of albums by a specific format.
     * 
     * @param format The format of the albums.
     * @return The number of albums of that format.
     */
    public Integer countAlbumsByFormat(ALBUM_FORMAT format) {
        return albumRepository.countByFormat(format);
    }

    /**
     * Finds albums by name, allowing partial search.
     * 
     * @param name The name of the album (can be partial).
     * @return List of albums whose name contains the provided string.
     */
    public List<AlbumDTO> findAlbumsByName(String name) {
        return albumRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(albumMapper::toDTO)
                .collect(Collectors.toList());
    }
}
