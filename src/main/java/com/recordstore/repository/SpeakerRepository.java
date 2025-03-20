package com.recordstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.model.Speaker;
/**
 * Repository interface for managing {@link Speaker} entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for performing basic CRUD operations
 * and custom queries on {@link Speaker} entities. It allows interaction with the database for actions
 * such as checking existence, deleting, and finding speakers by their ID.
 * </p>
 */
public interface SpeakerRepository extends JpaRepository<Speaker, Double> {

    /**
     * Checks if a speaker with the given ID exists in the database.
     * 
     * @param id the ID of the speaker to check for existence.
     * @return {@code true} if a speaker with the given ID exists, {@code false} otherwise.
     */
    boolean existsById(Integer id);

    /**
     * Deletes the speaker with the specified ID from the database.
     * 
     * @param id the ID of the speaker to delete.
     */
    void deleteById(Integer id);

    /**
     * Finds a speaker by its ID.
     * 
     * @param id the ID of the speaker to retrieve.
     * @return an {@link Optional} containing the speaker if found, or an empty {@link Optional} if not found.
     */
    Optional<Speaker> findById(Integer id);

}
