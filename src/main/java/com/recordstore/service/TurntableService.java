package com.recordstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recordstore.dto.TurntableDTO;
import com.recordstore.mapper.TurntableMapper;
import com.recordstore.model.Turntable;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.TurntableRepository;
import com.recordstore.repository.WishlistRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service class that handles operations related to the Turntable entity.
 * <p>
 * This service provides methods to perform CRUD operations on Turntable
 * objects, including
 * managing the attributes such as built-in preamp, allowed RPM, traction type,
 * and mechanism.
 * </p>
 * 
 * @see Turntable
 */
@Service
public class TurntableService {

    private final TurntableRepository turntableRepository;
    private final TurntableMapper turntableMapper;
    private final OrderRepository orderRepository;
    private final WishlistRepository wishlistRepository;
    
    @Autowired
    public TurntableService (TurntableRepository turntableRepository, TurntableMapper turntableMapper, OrderRepository orderRepository, WishlistRepository wishlistRepository) {
        this.turntableRepository = turntableRepository;
        this.turntableMapper = turntableMapper;
        this.orderRepository = orderRepository;
        this.wishlistRepository = wishlistRepository;
    }

        /**
     * Retrieves all turntables.
     *
     * @return List of all turntables as DTOs.
     */
    public List<TurntableDTO> getAllTurntables() {
        return turntableRepository.findAll()
                .stream()
                .map(turntableMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a turntable by its ID.
     *
     * @param id The ID of the turntable.
     * @return The corresponding TurntableDTO, if found.
     */
    public Optional<TurntableDTO> getTurntableById(Integer id) {
        return turntableRepository.findById(id)
                .map(turntableMapper::toDTO);
    }

    /**
     * Saves a new turntable.
     *
     * @param dto The DTO representing the turntable to save.
     * @return The saved TurntableDTO.
     */
    public TurntableDTO saveTurntable(TurntableDTO dto) {
        Turntable turntable = turntableMapper.toEntity(dto);
        return turntableMapper.toDTO(turntableRepository.save(turntable));
    }

    /**
     * Deletes a turntable by its ID.
     *
     * @param id The ID of the turntable to delete.
     */
    public void deleteTurntable(Integer id) {
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
    
        // If not in use, delete the turntable
        turntableRepository.deleteById(id);
    }
    

    /**
     * Retrieves turntables filtered by brand.
     *
     * @param brand The brand name to filter by.
     * @return List of turntables matching the brand.
     */
    public List<TurntableDTO> getTurntablesByBrand(String brand) {
        return turntableRepository.findByBrand(brand)
                .stream()
                .map(turntableMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves turntables filtered by color.
     *
     * @param color The color to filter by.
     * @return List of turntables matching the color.
     */
    public List<TurntableDTO> getTurntablesByColor(String color) {
        return turntableRepository.findByColor(color)
        .stream()
        .map(turntableMapper::toDTO)
        .collect(Collectors.toList());
    }

    /**
     * Retrieves turntables with a warranty greater than or equal to the specified value.
     *
     * @param warranty The minimum warranty period.
     * @return List of turntables matching the warranty criteria.
     */
    public List<TurntableDTO> getTurntablesByWarrantyGreaterThanEqual(Integer warranty) {
        return turntableRepository.findByWarrantyGreaterThanEqual(warranty)
        .stream()
        .map(turntableMapper::toDTO)
        .collect(Collectors.toList());
    }

    /**
     * Retrieves turntables filtered by Bluetooth availability.
     *
     * @param bluetooth Whether the turntable has Bluetooth.
     * @return List of turntables matching the Bluetooth criteria.
     */
    public List<TurntableDTO> getTurntablesByBluetooth(boolean bluetooth) {
        return turntableRepository.findByBluetooth(bluetooth)
        .stream()
        .map(turntableMapper::toDTO)
        .collect(Collectors.toList());
    }

    /**
     * Retrieves turntables filtered by USB availability.
     *
     * @param usb Whether the turntable has a USB port.
     * @return List of turntables matching the USB criteria.
     */
    public List<TurntableDTO> getTurntablesByUsb(boolean usb) {
        return turntableRepository.findByUsb(usb)
                .stream()
                .map(turntableMapper::toDTO)
                .collect(Collectors.toList());
    }
    /**
     * Creates a new Turntable in the database.
     * 
     * @param turntable The Turntable object to be saved.
     * @return The saved Turntable as a DTO.
     */
    public TurntableDTO saveTurntable (Turntable turntable) {
        return  turntableMapper.toDTO(turntableRepository.save(turntable));
    }

    /**
     * Updates an existing Turntable in the database with the given fields.
     * Only the fields provided in the input will be updated, and the rest will
     * remain unchanged.
     *
     * @param id        The ID of the Turntable to be updated.
     * @param turntable The Turntable object with updated fields.
     * @return The updated Turntable, or an empty Optional if the Turntable is not
     *         found.
     */
    public Optional<Turntable> updateTurntable(Integer id, Turntable turntable) {
        Optional<Turntable> existingTurntable = turntableRepository.findById(id);
        if (existingTurntable.isPresent()) {
            Turntable updatedTurntable = existingTurntable.get();

            // Update only the provided fields
            if (turntable.getName() != null) {
                updatedTurntable.setName(turntable.getName());
            }
            if (turntable.getPrice() != null) {
                updatedTurntable.setPrice(turntable.getPrice());
            }
            if (turntable.getStock() != null) {
                updatedTurntable.setStock(turntable.getStock());
            }
            if (turntable.getProductCategory() != null) {
                updatedTurntable.setProductCategory(turntable.getProductCategory());
            }
            if (turntable.getBrand() != null) {
                updatedTurntable.setBrand(turntable.getBrand());
            }
            if (turntable.getColor() != null) {
                updatedTurntable.setColor(turntable.getColor());
            }
            if (turntable.getWarranty() != null) {
                updatedTurntable.setWarranty(turntable.getWarranty());
            }

            // Update boolean fields only if they are explicitly provided (using Boolean
            // instead of boolean)
            if (turntable.getBluetooth() != null) {
                updatedTurntable.setBluetooth(turntable.getBluetooth());
            }
            if (turntable.getUsb() != null) {
                updatedTurntable.setUsb(turntable.getUsb());
            }
            if (turntable.getRadio() != null) {
                updatedTurntable.setRadio(turntable.getRadio());
            }
            if (turntable.getAux() != null) {
                updatedTurntable.setAux(turntable.getAux());
            }
            if (turntable.getRca() != null) {
                updatedTurntable.setRca(turntable.getRca());
            }
            if (turntable.getBuiltInSpeaker() != null) {
                updatedTurntable.setBuiltInSpeaker(turntable.getBuiltInSpeaker());
            }
            if (turntable.getHasBuiltInPreAmp() != null) {
                updatedTurntable.setHasBuiltInPreAmp(turntable.getHasBuiltInPreAmp());
            }

            // Update other fields if present
            if (turntable.getRpm() != null) {
                updatedTurntable.setRpm(turntable.getRpm());
            }
            if (turntable.getTraction() != null) {
                updatedTurntable.setTraction(turntable.getTraction());
            }
            if (turntable.getMechanism() != null) {
                updatedTurntable.setMechanism(turntable.getMechanism());
            }

            return Optional.of(turntableRepository.save(updatedTurntable));
        }
        return Optional.empty();
    }
}
