package com.recordstore.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recordstore.dto.PortableDTO;
import com.recordstore.enums.PORTABLE_TYPE;
import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.RESISTANCE;
import com.recordstore.mapper.PortableMapper;
import com.recordstore.model.Portable;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.PortableRepository;
import com.recordstore.repository.WishlistRepository;

import jakarta.transaction.Transactional;

/**
 * Service class responsible for managing operations related to the Portable
 * entity.
 * Provides methods to create, update, delete, and retrieve portables from the
 * database.
 * This service also includes filtering functionality based on different
 * attributes of portables.
 * <p>
 * It interacts with the repository layer to perform CRUD operations and applies
 * necessary transformations
 * to DTOs using the {@link PortableMapper}.
 * </p>
 */
@Service
public class PortableService {
    /**
     * Dependencies needed for the PortableService.
     */
    private final PortableRepository portableRepository;
    private final PortableMapper portableMapper;
    private final OrderRepository orderRepository;
    private final WishlistRepository wishlistRepository;

    /**
     * Constructor to initialize the PortableService with dependencies.
     * 
     * @param portableRepository The repository for accessing Portable entities.
     * @param portableMapper   The mapper for converting between Portable and
     * @param orderRepository   The repository for accessing Order entities.
     * @param wishlistRepository The repository for accessing Wishlist entities.
     */
    @Autowired
    public PortableService(PortableRepository portableRepository, PortableMapper portableMapper,
            OrderRepository orderRepository, WishlistRepository wishlistRepository) {
        this.portableRepository = portableRepository;
        this.portableMapper = portableMapper;
        this.orderRepository = orderRepository;
        this.wishlistRepository = wishlistRepository;
    }

    /**
     * Creates a new Portable based on the provided PortableDTO.
     * Converts the DTO to a Portable entity, saves it, and returns the saved entity
     * as a DTO.
     *
     * @param portableDTO The PortableDTO containing the details of the portable to
     *                    be created.
     * @return The created PortableDTO containing the details of the saved portable.
     */
    public PortableDTO savePortable(PortableDTO portableDTO) {
        // Convert PortableDTO to Portable entity
        Portable portable = portableMapper.toEntity(portableDTO);
        // Save the Portable entity to the database
        portable = portableRepository.save(portable);
        // Convert the saved Portable entity to PortableDTO and return it
        return portableMapper.toDTO(portable);
    }

    /**
     * Updates an existing Portable based on the provided PortableDTO.
     * Only non-null fields from the DTO will be updated.
     * If the Portable does not exist, it returns null.
     *
     * @param id          The id of the portable to be updated.
     * @param portableDTO The PortableDTO containing the updated details.
     * @return The updated PortableDTO, or null if the Portable was not found.
     */
    public PortableDTO updatePortable(Integer id, PortableDTO portableDTO) {
        Optional<Portable> existingPortableOpt = portableRepository.findById(id);

        if (existingPortableOpt.isPresent()) {
            Portable existingPortable = existingPortableOpt.get();

            // Update only non-null fields from the PortableDTO
            if (portableDTO.getName() != null) {
                existingPortable.setName(portableDTO.getName());
            }
            if (portableDTO.getPrice() != null) {
                existingPortable.setPrice(portableDTO.getPrice());
            }
            if (portableDTO.getStock() != null) {
                existingPortable.setStock(portableDTO.getStock());
            }
            if (portableDTO.getBrand() != null) {
                existingPortable.setBrand(portableDTO.getBrand());
            }
            if (portableDTO.getColor() != null) {
                existingPortable.setColor(portableDTO.getColor());
            }
            if (portableDTO.getWarranty() != null) {
                existingPortable.setWarranty(portableDTO.getWarranty());
            }
            if (portableDTO.getBluetooth() != null) {
                existingPortable.setBluetooth(portableDTO.getBluetooth());
            }
            if (portableDTO.getUsb() != null) {
                existingPortable.setUsb(portableDTO.getUsb());
            }
            if (portableDTO.getRadio() != null) {
                existingPortable.setRadio(portableDTO.getRadio());
            }
            if (portableDTO.getAux() != null) {
                existingPortable.setAux(portableDTO.getAux());
            }
            if (portableDTO.getRca() != null) {
                existingPortable.setRca(portableDTO.getRca());
            }
            if (portableDTO.getBuiltInSpeaker() != null) {
                existingPortable.setBuiltInSpeaker(portableDTO.getBuiltInSpeaker());
            }

            // Save the updated Portable entity
            existingPortable = portableRepository.save(existingPortable);
            // Convert the updated Portable entity to PortableDTO and return it
            return portableMapper.toDTO(existingPortable);
        }

        // Return null if the portable with the given id doesn't exist
        return null;
    }

    /**
     * Deletes a Portable by its id.
     * Ensures that the Portable is not associated with any order or wishlist before
     * deletion.
     *
     * @param id The id of the Portable to be deleted.
     * @throws IllegalArgumentException If the Portable is associated with an order
     *                                  or a wishlist.
     */
    @Transactional
    public void deletePortable(Integer id) {
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
        portableRepository.deleteById(id);
    }

    /**
     * Retrieves a Portable by its id.
     * Returns an Optional containing the PortableDTO if found, otherwise an empty
     * Optional.
     *
     * @param id The id of the Portable to be retrieved.
     * @return An Optional containing the PortableDTO if found, or an empty
     *         Optional.
     */
    public Optional<PortableDTO> getPortableById(Integer id) {
        Optional<Portable> portableOpt = portableRepository.findById(id);
        return portableOpt.map(portableMapper::toDTO);
    }

    /**
     * Retrieves all Portables.
     * 
     * @return A list of PortableDTOs representing all available portables.
     */
    public List<PortableDTO> getAllPortables() {
        List<Portable> portable = portableRepository.findAll();
        return portable.stream()
                .map(portableMapper::toDTO)
                .collect(Collectors.toList());
    }

    // Filtering Methods

    /**
     * Retrieves a list of portables that have an aux port.
     * 
     * @param aux Whether the portable has an aux port.
     * @return A list of PortableDTOs representing portables with an aux port.
     */
    public List<PortableDTO> findByAux(Boolean aux) {
        return portableRepository.findByAux(aux)
                .stream()
                .map(portableMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables that have a USB port.
     * 
     * @param usb Whether the portable has a USB port.
     * @return A list of PortableDTOs representing portables with a USB port.
     */
    public List<PortableDTO> findByUsb(Boolean usb) {
        return portableRepository.findByUsb(usb)
                .stream()
                .map(portableMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables that have Bluetooth connectivity.
     * 
     * @param bluetooth Whether the portable has Bluetooth.
     * @return A list of PortableDTOs representing portables with Bluetooth
     *         connectivity.
     */
    public List<PortableDTO> findByBluetooth(Boolean bluetooth) {
        return portableRepository.findByBluetooth(bluetooth)
                .stream()
                .map(portableMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portable players with a warranty period equal to or
     * greater than the specified value.
     * 
     * @param warranty The warranty period in months.
     * @return A list of PortableDTOs representing portable players with a warranty
     *         period equal to or greater than the specified value.
     */
    public List<PortableDTO> findByWarranty(Integer warranty) {
        return portableRepository.findByWarrantyGreaterThanEqual(warranty)
                .stream()
                .map(portableMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables by brand.
     * <p>
     * This method queries the database for all portable devices that match the
     * specified brand
     * and returns them as a list of {@link PortableDTO}.
     * </p>
     * 
     * @param brand the brand of the portable devices to retrieve.
     * @return a list of {@link PortableDTO} objects representing the portables with
     *         the specified brand.
     */
    public List<PortableDTO> findByBrand(String brand) {
        return portableRepository.findByBrand(brand)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables by color.
     * <p>
     * This method retrieves portable devices from the database that match the
     * specified color
     * and returns them as a list of {@link PortableDTO}.
     * </p>
     * 
     * @param color the color of the portable devices to retrieve.
     * @return a list of {@link PortableDTO} objects representing the portables with
     *         the specified color.
     */
    public List<PortableDTO> findByColor(String color) {
        return portableRepository.findByColor(color)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables by available stock.
     * <p>
     * This method queries the database to retrieve portable devices with the
     * specified stock quantity
     * and returns them as a list of {@link PortableDTO}.
     * </p>
     * 
     * @param stock the available stock quantity of the portable devices to
     *              retrieve.
     * @return a list of {@link PortableDTO} objects representing the portables with
     *         the specified stock.
     */
    public List<PortableDTO> findByStock(Integer stock) {
        return portableRepository.findByStock(stock)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables within a specified price range.
     * <p>
     * This method queries the database to retrieve portable devices whose price
     * falls within the specified range
     * and returns them as a list of {@link PortableDTO}.
     * </p>
     * 
     * @param minPrice the minimum price of the portable devices to retrieve.
     * @param maxPrice the maximum price of the portable devices to retrieve.
     * @return a list of {@link PortableDTO} objects representing the portables
     *         within the specified price range.
     */
    public List<PortableDTO> findByPriceRange(Double minPrice, Double maxPrice) {
        return portableRepository.findByPriceBetween(minPrice, maxPrice)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables by model name.
     * <p>
     * This method retrieves portable devices from the database that contain the
     * specified model name
     * and returns them as a list of {@link PortableDTO}.
     * </p>
     * 
     * @param name the model name of the portable devices to retrieve.
     * @return a list of {@link PortableDTO} objects representing the portables with
     *         the specified model name.
     */
    public List<PortableDTO> findByName(String name) {
        return portableRepository.findByNameContaining(name)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables that have a radio.
     * <p>
     * This method retrieves portable devices from the database that have a built-in
     * radio
     * and returns them as a list of {@link PortableDTO}.
     * </p>
     * 
     * @param radio whether the portable devices should have a radio (true) or not
     *              (false).
     * @return a list of {@link PortableDTO} objects representing the portables that
     *         have a radio.
     */
    public List<PortableDTO> findByRadio(Boolean radio) {
        return portableRepository.findByRadio(radio)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables by portable type.
     * <p>
     * This method retrieves portable devices from the database that match the
     * specified portable type
     * and returns them as a list of {@link PortableDTO}.
     * </p>
     * 
     * @param portableType the type of the portable devices to retrieve (e.g.,
     *                     radio, speaker, etc.).
     * @return a list of {@link PortableDTO} objects representing the portables with
     *         the specified portable type.
     */
    public List<PortableDTO> findByPortableType(PORTABLE_TYPE portableType) {
        return portableRepository.findByPortableType(portableType)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables by power type.
     * <p>
     * This method queries the database to retrieve portable devices that match the
     * specified power type
     * (e.g., battery, USB) and returns them as a list of {@link PortableDTO}.
     * </p>
     * 
     * @param powerType the power type of the portable devices to retrieve.
     * @return a list of {@link PortableDTO} objects representing the portables with
     *         the specified power type.
     */
    public List<PortableDTO> findByPowerType(POWER_TYPE powerType) {
        return portableRepository.findByPowerType(powerType)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables with a minimum battery life.
     * <p>
     * This method retrieves portable devices from the database that have a battery
     * life greater than or equal
     * to the specified value and returns them as a list of {@link PortableDTO}.
     * </p>
     * 
     * @param batteryLife the minimum battery life of the portable devices to
     *                    retrieve.
     * @return a list of {@link PortableDTO} objects representing the portables with
     *         battery life greater than or equal to the specified value.
     */
    public List<PortableDTO> findByBatteryLife(Integer batteryLife) {
        return portableRepository.findByBatteryLifeGreaterThanEqual(batteryLife)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a list of portables by resistance.
     * <p>
     * This method retrieves portable devices from the database that match the
     * specified resistance
     * (e.g., built-in speakers resistance) and returns them as a list of
     * {@link PortableDTO}.
     * </p>
     * 
     * @param resistance the resistance type of the portable devices to retrieve.
     * @return a list of {@link PortableDTO} objects representing the portables with
     *         the specified resistance.
     */
    public List<PortableDTO> findByResistance(RESISTANCE resistance) {
        return portableRepository.findByResistance(resistance)
                .stream()
                .map(portable -> portableMapper.toDTO(portable))
                .collect(Collectors.toList());
    }

}
