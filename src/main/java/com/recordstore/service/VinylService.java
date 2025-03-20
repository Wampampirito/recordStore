package com.recordstore.service;

import com.recordstore.model.Order;
import com.recordstore.model.Vinyl;
import com.recordstore.model.Wishlist;
import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.enums.VINYL_RPM;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.VinylRepository;
import com.recordstore.repository.WishlistRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for managing vinyls in the system.
 * This service provides methods to get, save, update, and delete vinyls.
 */
@Service
public class VinylService {

    private final VinylRepository vinylRepository;
    private final OrderRepository orderRepository;
    private final WishlistRepository wishlistRepository;

    /**
     * Constructor for the service that injects the repositorys.
     * 
     * @param vinylRepository The vinyl repository to inject.
     * @param orderRepository The order repository to inject.
     * @param wishlistRepository The wishlist repository to inject.
     */
    @Autowired
    public VinylService(VinylRepository vinylRepository, OrderRepository orderRepository, WishlistRepository wishlistRepository) {
            this.vinylRepository = vinylRepository;
            this.orderRepository = orderRepository;
            this.wishlistRepository = wishlistRepository;
    }

    /**
     * Retrieves all vinyls available in the system.
     * 
     * @return List of all vinyls available in the system.
     */
    public List<Vinyl> getAllVinyls() {
        return vinylRepository.findAll();
    }

    /**
     * Retrieves an vinyl by its id (ID).
     * 
     * @param id The id of the vinyl to search for.
     * @return An {@link java.util.Optional Optional} with the vinyl found, or empty
     *         if not found.
     */
    public Optional<Vinyl> getVinylById(Double id) {
        return vinylRepository.findById(id);
    }

    /**
     * Retrieves all vinyls by a specific artist.
     * 
     * @param artist The name of the artist.
     * @return List of vinyls by the artist.
     */
    public List<Vinyl> getVinylsByArtist(String artist) {
        return vinylRepository.findByArtist(artist);
    }

    /**
     * Retrieves all vinyls released within a year range.
     * 
     * @param startYear The start year.
     * @param endYear   The end year.
     * @return List of vinyls released between the specified years.
     */
    public List<Vinyl> getVinylsByYearRange(int startYear, int endYear) {
        return vinylRepository.findByYearBetween(startYear, endYear);
    }

    /**
     * Retrieves all vinyls by a specific genre.
     * 
     * @param genre The genre of the vinyls to search for.
     * @return List of vinyls of the specified genre.
     */
    public List<Vinyl> getVinylsByGenre(ALBUM_GENRE genre) {
        return vinylRepository.findByGenre(genre);
    }

    /**
     * Retrieves all vinyls by a specific format.
     * 
     * @param format The format of the vinyls to search for (LP, EP, etc.).
     * @return List of vinyls of the specified format.
     */
    public List<Vinyl> getVinylsByFormat(ALBUM_FORMAT format) {
        return vinylRepository.findByFormat(format);
    }

    /**
     * Retrieves all vinyls whose price is within a specified range.
     * 
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return List of vinyls within the specified price range.
     */
    public List<Vinyl> getVinylsByPriceRange(Double minPrice, Double maxPrice) {
        return vinylRepository.findByPriceBetween(minPrice, maxPrice);
    }

    /**
     * Retrieves all vinyls whose duration is within a specified range.
     * 
     * @param minDuration The minimum duration (in "mm:ss" format).
     * @param maxDuration The maximum duration (in "mm:ss" format).
     * @return List of vinyls whose duration is within the specified range.
     */
    public List<Vinyl> getVinylsByDuration(String minDuration, String maxDuration) {
        return vinylRepository.findByDurationBetween(minDuration, maxDuration);
    }

    /**
     * Saves a new vinyl in the system.
     * 
     * @param vinyl The vinyl to save.
     * @return The saved vinyl with its assigned id.
     * @throws IllegalArgumentException If the vinyl's year or RPM is invalid.
     */
    public Vinyl saveVinyl(Vinyl vinyl) {
        validateYear(vinyl.getYear()); // Validate the vinyl's year before saving
        validateRpm(vinyl.getRpm()); // Validate the RPM before saving
        return vinylRepository.save(vinyl);
    }

    /**
     * Deletes an vinyl by its id (ID).
     * 
     * @param id The id of the vinyl to delete.
     */
    @Transactional
    public void deleteVinyl(Integer id) {
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

        // If it is not in any order or wishlist, delete the product
        vinylRepository.deleteById(id);
    }

    /**
     * Updates the details of an existing vinyl, including both inherited and
     * specific fields.
     * 
     * @param id           The id of the vinyl to update.
     * @param updatedVinyl The vinyl with updated details.
     * @return The updated vinyl.
     * @throws IllegalArgumentException If the vinyl with the given id is not
     *                                  found.
     */
    public Vinyl updateVinyl(Double id, Vinyl updatedVinyl) {
        Optional<Vinyl> existingVinylOpt = vinylRepository.findById(id);
        if (existingVinylOpt.isPresent()) {
            Vinyl existingVinyl = existingVinylOpt.get();

            // Update inherited fields from Album
            if (updatedVinyl.getYear() != 0) {
                updateVinylReleaseYear(existingVinyl, updatedVinyl.getYear());
            }
            if (updatedVinyl.getName() != null && !updatedVinyl.getName().isEmpty()) {
                existingVinyl.setName(updatedVinyl.getName());
            }
            if (updatedVinyl.getPrice() > 0) {
                existingVinyl.setPrice(updatedVinyl.getPrice());
            }
            if (updatedVinyl.getStock() >= 0) {
                existingVinyl.setStock(updatedVinyl.getStock());
            }
            if (updatedVinyl.getArtist() != null && !updatedVinyl.getArtist().isEmpty()) {
                existingVinyl.setArtist(updatedVinyl.getArtist());
            }
            if (updatedVinyl.getGenre() != null) {
                existingVinyl.setGenre(updatedVinyl.getGenre());
            }
            if (updatedVinyl.getFormat() != null) {
                existingVinyl.setFormat(updatedVinyl.getFormat());
            }
            if (updatedVinyl.getDuration() != null && !updatedVinyl.getDuration().isEmpty()) {
                existingVinyl.setDuration(updatedVinyl.getDuration());
            }

            // Update specific fields of Vinyl
            if (updatedVinyl.getSize() != null) {
                existingVinyl.setSize(updatedVinyl.getSize());
            }
            if (updatedVinyl.getColor() != null && !updatedVinyl.getColor().isEmpty()) {
                existingVinyl.setColor(updatedVinyl.getColor());
            }
            if (updatedVinyl.getRpm() != null) {
                validateRpm(updatedVinyl.getRpm()); // Validate RPM before assigning
                existingVinyl.setRpm(updatedVinyl.getRpm());
            }

            return vinylRepository.save(existingVinyl);
        } else {
            throw new IllegalArgumentException("Vinyl with id " + id + " not found.");
        }
    }

    /**
     * Validates vinyl data before saving or updating.
     * 
     * @param vinyl The vinyl to validate.
     * @throws IllegalArgumentException If the data is not valid.
     */
    public void validateYear(int year) throws IllegalArgumentException {
        if (year < 1860 || year > java.time.Year.now().getValue()) {
            throw new IllegalArgumentException("The year must be between 1860 and the current year.");
        }
        // Additional validation logic can go here (e.g., for price, duration, etc.)
    }

    /**
     * Updates the release year of the vinyl if it's within a valid range.
     * 
     * @param vinyl The vinyl instance to be updated.
     * @param year  The release year of the vinyl.
     * @throws IllegalArgumentException If the year is not valid.
     */
    private void updateVinylReleaseYear(Vinyl vinyl, int year) throws IllegalArgumentException {
        validateYear(year);
        vinyl.setYear(year); // Update the year of the vinyl
    }

    /**
     * Retrieves all vinyls of a specific product category.
     * 
     * @param category The product category to filter vinyls by.
     * @return List of vinyls of the specified category.
     */
    public List<Vinyl> getVinylsByCategory(PRODUCT_CATEGORY category) {
        return vinylRepository.findByProductCategory(category);
    }

    /**
     * Retrieves all vinyls that are in stock (at least 1 unit available).
     * 
     * @return List of vinyls that are in stock.
     */
    public List<Vinyl> getVinylsInStock() {
        return vinylRepository.findByStockGreaterThan(0);
    }

    /**
     * Counts the number of vinyls by a specific artist.
     * 
     * @param artist The name of the artist.
     * @return The number of vinyls by the artist.
     */
    public Double countVinylsByArtist(String artist) {
        return vinylRepository.countByArtist(artist);
    }

    /**
     * Counts the number of vinyls by a specific genre.
     * 
     * @param genre The genre of the vinyls.
     * @return The number of vinyls of that genre.
     */
    public Double countVinylsByGenre(ALBUM_GENRE genre) {
        return vinylRepository.countByGenre(genre);
    }

    /**
     * Counts the number of vinyls by a specific format.
     * 
     * @param format The format of the vinyls.
     * @return The number of vinyls of that format.
     */
    public Double countVinylsByFormat(ALBUM_FORMAT format) {
        return vinylRepository.countByFormat(format);
    }

    /**
     * Finds vinyls by name, allowing partial search.
     * 
     * @param name The name of the vinyl (can be partial).
     * @return List of vinyls whose name contains the provided string.
     */
    public List<Vinyl> findVinylsByName(String name) {
        return vinylRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Validates the RPM value for a vinyl.
     * 
     * @param rpm The RPM to validate.
     * @throws IllegalArgumentException If the RPM is invalid.
     */
    public void validateRpm(VINYL_RPM rpm) throws IllegalArgumentException {
        if (rpm != VINYL_RPM.RPM_33 && rpm != VINYL_RPM.RPM_45 && rpm != VINYL_RPM.RPM_78) {
            throw new IllegalArgumentException("The vinyl can only have a speed of 33, 45, or 78 RPM.");
        }
    }
}
