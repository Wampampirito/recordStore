package com.recordstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.model.User;

/**
 * Repository for managing {@link User} entities, providing access to database operations.
 * <p>
 * The {@link UserRepository} interface extends {@link JpaRepository}, which allows performing basic CRUD 
 * operations on the {@link User} entity without the need to write manual implementations.
 * </p>
 * 
 * Additional methods:
 * <ul>
 *   <li><b>existsByEmail(String email)</b>: Checks if a user with a specific email already exists.</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * Boolean exists = userRepository.existsByEmail("email@example.com");
 * </pre>
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Checks if a user with the provided email exists.
     * <p>
     * This method is used to prevent email duplication in the database when registering new users.
     * </p>
     * 
     * @param email The email address to check for existence.
     * @return {@code true} if the email is already registered, {@code false} otherwise.
     */
    Boolean existsByEmail(String email);

    /**
     * Finds a user by their email.
     * <p>
     * This method retrieves a user based on the provided email.
     * </p>
     * 
     * @param email The email address of the user to retrieve.
     * @return An {@link Optional} containing the user if found, or empty if not found.
     */
    Optional<User> findByEmail(String email);
}
