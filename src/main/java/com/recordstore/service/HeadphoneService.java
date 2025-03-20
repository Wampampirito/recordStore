package com.recordstore.service;

import com.recordstore.dto.HeadphonesDTO;
import com.recordstore.enums.HEADPHONES_TYPE;
import com.recordstore.enums.NOISE_CANCELING;
import com.recordstore.mapper.HeadphoneMapper;
import com.recordstore.model.Headphone;
import com.recordstore.repository.HeadphoneRepository;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.WishlistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service that handles the business logic related to Headphones.
 * This service provides methods to perform CRUD operations and other specific
 * logic for Headphones.
 */
@Service
public class HeadphoneService {

    /**
     * Dependencies needed for the HeadphoneService.
     */
    private final HeadphoneRepository headphoneRepository;
    private final HeadphoneMapper headphoneMapper;
    private OrderRepository orderRepository;
    private WishlistRepository wishlistRepository;

    /**
     * Constructor to initialize the HeadphoneService with dependencies.
     * 
     * @param headphoneRepository The repository for accessing Headphone entities.
     * @param headphoneMapper    The mapper for converting between Headphone and
     * @param orderRepository    The repository for accessing Order entities.
     * @param wishlistRepository The repository for accessing Wishlist entities.
     */
    @Autowired
    public HeadphoneService(HeadphoneRepository headphoneRepository, HeadphoneMapper headphoneMapper,
            OrderRepository orderRepository, WishlistRepository wishlistRepository) {
        this.headphoneRepository = headphoneRepository;
        this.headphoneMapper = headphoneMapper;
        this.orderRepository = orderRepository;
        this.wishlistRepository = wishlistRepository;
    }

    /**
     * Get all headphones from the database.
     * 
     * @return List of all headphones as DTOs.
     */
    public List<HeadphonesDTO> getAllHeadphones() {
        List<Headphone> headphones = headphoneRepository.findAll();
        return headphones.stream()
                .map(headphoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get a specific headphone by its id.
     * 
     * @param id The id of the headphone.
     * @return The headphone DTO.
     */
    public HeadphonesDTO getHeadphoneById(Integer id) {
        Optional<Headphone> headphone = headphoneRepository.findById(id);
        if (headphone.isPresent()) {
            return headphoneMapper.toDTO(headphone.get());
        } else {
            throw new IllegalArgumentException("Headphone not found for id " + id);
        }
    }

    /**
     * Create a new headphone record.
     * 
     * @param headphonesDTO The headphone DTO to be created.
     * @return The created headphone DTO.
     */
    public HeadphonesDTO saveHeadphone(HeadphonesDTO headphonesDTO) {
        Headphone headphone = headphoneMapper.toEntity(headphonesDTO);
        Headphone createdHeadphone = headphoneRepository.save(headphone);
        return headphoneMapper.toDTO(createdHeadphone);
    }

    /**
     * Update an existing headphone record. If any field is not provided, the
     * previous value will be retained.
     * 
     * @param id            The id of the headphone to update.
     * @param headphonesDTO The new details of the headphone.
     * @return The updated headphone DTO.
     */
    public HeadphonesDTO updateHeadphone(Integer id, HeadphonesDTO headphonesDTO) {
        Optional<Headphone> optionalHeadphone = headphoneRepository.findById(id);
        if (optionalHeadphone.isPresent()) {
            Headphone headphone = optionalHeadphone.get();
    
            // Update only the non-null or valid fields
            if (headphonesDTO.getBrand() != null) {
                headphone.setBrand(headphonesDTO.getBrand());
            }
            if (headphonesDTO.getName() != null) {
                headphone.setName(headphonesDTO.getName());
            }
            if (headphonesDTO.getPrice() > 0) {
                headphone.setPrice(headphonesDTO.getPrice());
            }
            if (headphonesDTO.getWireless() != null) {
                headphone.setWireless(headphonesDTO.getWireless());
            }
            if (headphonesDTO.getBatteryLife() > 0) {
                headphone.setBatteryLife(headphonesDTO.getBatteryLife());
            }
            if (headphonesDTO.getWarranty() > 0) {
                headphone.setWarranty(headphonesDTO.getWarranty());
            }
            if (headphonesDTO.getMicrophoneBuiltIn() != headphone.getMicrophoneBuiltIn()) {
                headphone.setMicrophoneBuiltIn(headphonesDTO.getMicrophoneBuiltIn());
            }
            if (headphonesDTO.getBluetooth() != null) {
                headphone.setBluetooth(headphonesDTO.getBluetooth());
            }
            if (headphonesDTO.getUsb() != null) {
                headphone.setUsb(headphonesDTO.getUsb());
            }
            if (headphonesDTO.getAux() != null) {
                headphone.setAux(headphonesDTO.getAux());
            }
    
            Headphone updatedHeadphone = headphoneRepository.save(headphone);
            return headphoneMapper.toDTO(updatedHeadphone);
        } 
    
        throw new IllegalArgumentException("Headphone not found for id " + id);
    }

    /**
     * Delete a headphone by its id.
     * 
     * @param id The id of the headphone to delete.
     */
    public void deleteHeadphone(Integer id) {
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
            headphoneRepository.deleteById(id);
        }

    /**
     * Get headphones that have active noise cancellation.
     * 
     * @param anc True to find headphones with active noise cancellation.
     * @return A list of headphones with ANC.
     */
    public List<HeadphonesDTO> getHeadphonesWithANC(NOISE_CANCELING anc) {
        List<Headphone> headphones = headphoneRepository.findByAnc(anc);
        return headphones.stream()
                .map(headphoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get headphones that have Bluetooth.
     * 
     * @param bluetooth True to find headphones with Bluetooth.
     * @return A list of headphones with Bluetooth.
     */
    public List<HeadphonesDTO> getHeadphonesWithBluetooth(Boolean bluetooth) {
        List<Headphone> headphones = headphoneRepository.findByBluetooth(bluetooth);
        return headphones.stream()
                .map(headphoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get headphones that are wireless.
     * 
     * @param wireless True to find wireless headphones.
     * @return A list of wireless headphones.
     */
    public List<HeadphonesDTO> getHeadphonesWireless(Boolean wireless) {
        List<Headphone> headphones = headphoneRepository.findByWireless(wireless);
        return headphones.stream()
                .map(headphoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get headphones that have a specific warranty periodgreater than or equal to
     * the provided value.
     * 
     * @param warranty The mimimun warranty period to filter by.
     * @return A list of headphones with the specified warranty period greater than
     *         or equal to the specified value..
     */
    public List<HeadphonesDTO> getHeadphonesByWarranty(Integer warranty) {
        List<Headphone> headphones = headphoneRepository.findByWarrantyGreaterThanEqual(warranty);
        return headphones.stream()
                .map(headphoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get headphones that have a battery life greater than or equal to the provided
     * value.
     * 
     * @param batteryLife The minimum battery life to filter by.
     * @return A list of headphones with a battery life greater than or equal to the
     *         specified value.
     */
    public List<HeadphonesDTO> getHeadphonesByBatteryLife(Integer batteryLife) {
        List<Headphone> headphones = headphoneRepository.findByBatteryLifeGreaterThanEqual(batteryLife);
        return headphones.stream()
                .map(headphoneMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get headphones of a specific type.
     * 
     * @param headphoneType The type of headphones (e.g., over-ear, in-ear).
     * @return A list of headphones of the specified type.
     */
    public List<HeadphonesDTO> getHeadphonesByType(HEADPHONES_TYPE headphoneType) {
        List<Headphone> headphones = headphoneRepository.findByHeadphoneType(headphoneType);
        return headphones.stream()
                .map(headphoneMapper::toDTO)
                .collect(Collectors.toList());
    }
}
