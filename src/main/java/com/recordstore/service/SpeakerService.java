package com.recordstore.service;

import com.recordstore.model.Order;
import com.recordstore.model.Speaker;
import com.recordstore.model.Wishlist;
import com.recordstore.dto.SpeakerDTO;
import com.recordstore.mapper.SpeakerMapper;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.SpeakerRepository;
import com.recordstore.repository.WishlistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service class that handles operations related to the Speaker entity.
 * Provides methods for adding, updating, deleting, and retrieving speakers from
 * the database.
 */
@Service
public class SpeakerService {

    private final SpeakerRepository speakerRepository;
    private final SpeakerMapper speakerMapper;
    private OrderRepository orderRepository;
    private WishlistRepository wishlistRepository;

    @Autowired
    public SpeakerService(SpeakerRepository speakerRepository, SpeakerMapper speakerMapper,
            OrderRepository orderRepository, WishlistRepository wishlistRepository) {
        this.speakerRepository = speakerRepository;
        this.speakerMapper = speakerMapper;
        this.orderRepository = orderRepository;
        this.wishlistRepository = wishlistRepository;
    }

    /**
     * Fetch all speakers available in the store.
     *
     * @return a list of all speakers in DTO format.
     */
    public List<SpeakerDTO> getAllSpeakers() {
        List<Speaker> speakers = speakerRepository.findAll();
        return speakers.stream()
                .map(speakerMapper::toDTO)
                .toList();
    }

    /**
     * Fetch a speaker by its unique id.
     *
     * @param id the unique identifier (id) of the speaker.
     * @return an Optional containing the speaker in DTO format if found, or empty
     *         if not.
     */
    public Optional<SpeakerDTO> getSpeakerById(Integer id) {
        Optional<Speaker> speaker = speakerRepository.findById(id);
        return speaker.map(speakerMapper::toDTO);
    }

    /**
     * Add a new speaker to the store.
     *
     * @param speakerDTO the speaker to be added in DTO format.
     * @return the saved speaker in DTO format.
     */
    public SpeakerDTO addSpeaker(SpeakerDTO speakerDTO) {
        Speaker speaker = speakerMapper.toEntity(speakerDTO);
        Speaker savedSpeaker = speakerRepository.save(speaker);
        return speakerMapper.toDTO(savedSpeaker);
    }

    /**
     * Update an existing speaker's information.
     *
     * @param id         the id of the speaker to be updated.
     * @param speakerDTO the new speaker data to update in DTO format.
     * @return the updated speaker in DTO format.
     * @throws Exception if the speaker with the given id does not exist.
     */
    public SpeakerDTO updateSpeaker(Integer id, SpeakerDTO speakerDTO) throws Exception {
        if (!speakerRepository.existsById(id)) {
            throw new Exception("Speaker not found for id: " + id);
        }
        Speaker speaker = speakerMapper.toEntity(speakerDTO);
        speaker.setId(id); // Ensure the id is preserved
        Speaker updatedSpeaker = speakerRepository.save(speaker);
        return speakerMapper.toDTO(updatedSpeaker);
    }

    /**
     * Delete a speaker by its unique id.
     *
     * @param id the id of the speaker to delete.
     */
    public void deleteSpeaker(Integer id) {
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
        speakerRepository.deleteById(id);
    }
}
