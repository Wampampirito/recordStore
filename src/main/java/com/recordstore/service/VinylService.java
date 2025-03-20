package com.recordstore.service;

import com.recordstore.model.Vinyl;
import com.recordstore.dto.VinylDTO;
import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.enums.VINYL_RPM;
import com.recordstore.mapper.VinylMapper;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.VinylRepository;
import com.recordstore.repository.WishlistRepository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service for managing vinyls in the system.
 * This service provides methods to get, save, update, and delete vinyls.
 */
@Service
public class VinylService {

    private final VinylRepository vinylRepository;
    private final VinylMapper vinylMapper;
    private final OrderRepository orderRepository;
    private final WishlistRepository wishlistRepository;

    /**
     * Constructor for the service that injects the repositorys.
     * 
     * @param vinylRepository    The vinyl repository to inject.
     * @param orderRepository    The order repository to inject.
     * @param wishlistRepository The wishlist repository to inject.
     */
    @Autowired
    public VinylService(VinylRepository vinylRepository, VinylMapper vinylMapper, OrderRepository orderRepository,
            WishlistRepository wishlistRepository) {
        this.vinylRepository = vinylRepository;
        this.orderRepository = orderRepository;
        this.wishlistRepository = wishlistRepository;
        this.vinylMapper = vinylMapper;
    }

    /**
     * Retrieves all vinyls available in the system.
     * 
     * @return List of all vinyls available in the system.
     */
    public List<VinylDTO> getAllVinyls() {
        return vinylRepository.findAll()
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves an vinyl by its id (ID).
     * 
     * @param id The id of the vinyl to search for.
     * @return An {@link java.util.Optional Optional} with the vinyl found, or empty
     *         if not found.
     */
    public Optional<VinylDTO> getVinylById(Double id) {
        return vinylRepository.findById(id).map(vinylMapper::toDTO);
    }

    /**
     * Retrieves all vinyls by a specific artist.
     * 
     * @param artist The name of the artist.
     * @return List of vinyls by the artist.
     */
    public List<VinylDTO> getVinylsByArtist(String artist) {
        return vinylRepository.findByArtist(artist)
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all vinyls released within a year range.
     * 
     * @param startYear The start year.
     * @param endYear   The end year.
     * @return List of vinyls released between the specified years.
     */
    public List<VinylDTO> getVinylsByYearRange(int startYear, int endYear) {
        return vinylRepository.findByYearBetween(startYear, endYear)
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all vinyls by a specific genre.
     * 
     * @param genre The genre of the vinyls to search for.
     * @return List of vinyls of the specified genre.
     */
    public List<VinylDTO> getVinylsByGenre(ALBUM_GENRE genre) {
        return vinylRepository.findByGenre(genre)
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all vinyls by a specific format.
     * 
     * @param format The format of the vinyls to search for (LP, EP, etc.).
     * @return List of vinyls of the specified format.
     */
    public List<VinylDTO> getVinylsByFormat(ALBUM_FORMAT format) {
        return vinylRepository.findByFormat(format)
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all vinyls whose price is within a specified range.
     * 
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return List of vinyls within the specified price range.
     */
    public List<VinylDTO> getVinylsByPriceRange(Double minPrice, Double maxPrice) {
        return vinylRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all vinyls whose duration is within a specified range.
     * 
     * @param minDuration The minimum duration (in "mm:ss" format).
     * @param maxDuration The maximum duration (in "mm:ss" format).
     * @return List of vinyls whose duration is within the specified range.
     */
    public List<VinylDTO> getVinylsByDuration(String minDuration, String maxDuration) {
        return vinylRepository.findByDurationBetween(minDuration, maxDuration)
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Saves a new vinyl in the system.
     * 
     * @param vinyl The vinyl to save.
     * @return The saved vinyl with its assigned id.
     * @throws IllegalArgumentException If the vinyl's year or RPM is invalid.
     */
    public VinylDTO saveVinyl(Vinyl vinyl) {
        validateYear(vinyl.getYear()); // Validate the vinyl's year before saving
        validateRpm(vinyl.getRpm()); // Validate the RPM before saving
        return vinylMapper.toDTO(vinylRepository.save(vinyl));
    }

    /**
     * Deletes an vinyl by its id (ID).
     * 
     * @param id The id of the vinyl to delete.
     */
    @Transactional
    public void deleteVinyl(Integer id) {
        // Check if the product is associated with any order
        boolean isInOrders = orderRepository.existsByListOrderProducts_Product_Id(id);
        if (isInOrders) {
            throw new IllegalStateException("Cannot delete the product because it is associated with an order.");
        }

        // Check if the product is in any wishlist
        boolean isInWishlists = wishlistRepository.existsByListWishlistProducts_Product_Id(id);
        if (isInWishlists) {
            throw new IllegalStateException("Cannot delete the product because it is in a wishlist.");
        }

        // If not in use, delete the product
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
    public VinylDTO updateVinyl(Double id, Vinyl updatedVinyl) {
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

            return vinylMapper.toDTO(vinylRepository.save(existingVinyl));
        } else {
            throw new IllegalArgumentException("Vinyl with id " + id + " not found.");
        }
    }

    /**
     * Validates vinyl release year before saving or updating.
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
                                                                                                      // made in 1860
        }
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
    public List<VinylDTO> getVinylsByCategory(PRODUCT_CATEGORY category) {
        return vinylRepository.findByProductCategory(category)
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());

    }

    /**
     * Retrieves all vinyls that are in stock (at least 1 unit available).
     * 
     * @return List of vinyls that are in stock.
     */
    public List<VinylDTO> getVinylsInStock() {
        return vinylRepository.findByStockGreaterThan(0)
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());

    }

    /**
     * Counts the number of vinyls by a specific artist.
     * 
     * @param artist The name of the artist.
     * @return The number of vinyls by the artist.
     */
    public Integer countVinylsByArtist(String artist) {
        return vinylRepository.countByArtist(artist);
    }

    /**
     * Counts the number of vinyls by a specific genre.
     * 
     * @param genre The genre of the vinyls.
     * @return The number of vinyls of that genre.
     */
    public Integer countVinylsByGenre(ALBUM_GENRE genre) {
        return vinylRepository.countByGenre(genre);
    }

    /**
     * Counts the number of vinyls by a specific format.
     * 
     * @param format The format of the vinyls.
     * @return The number of vinyls of that format.
     */
    public Integer countVinylsByFormat(ALBUM_FORMAT format) {
        return vinylRepository.countByFormat(format);
    }

    /**
     * Finds vinyls by name, allowing partial search.
     * 
     * @param name The name of the vinyl (can be partial).
     * @return List of vinyls whose name contains the provided string.
     */
    public List<VinylDTO> findVinylsByName(String name) {
        return vinylRepository.findByNameContainingIgnoreCase(name)
                .stream()
                .map(vinylMapper::toDTO)
                .collect(Collectors.toList());

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
