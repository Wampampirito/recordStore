package com.recordstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.recordstore.dto.UserDTO;
import com.recordstore.mapper.UserMapper;
import com.recordstore.model.User;
import com.recordstore.repository.UserRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service class to manage {@link User} operations.
 * It provides methods for CRUD operations on the {@link User} entity, including user creation, updating, deletion, and password management.
 * Each method works with {@link UserDTO} for the data transfer object (DTO) representation of the {@link User}.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructs a {@link UserService} with the specified dependencies.
     * 
     * @param userRepository the {@link UserRepository} to access the data
     * @param userMapper the {@link UserMapper} to map between {@link User} and {@link UserDTO}
     * @param passwordEncoder the {@link PasswordEncoder} to encode passwords
     */
    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Retrieves a {@link UserDTO} by its ID.
     *
     * @param id the ID of the user to retrieve
     * @return the {@link UserDTO} of the user
     */
    public Optional<User> getUserById(Double userId) {
        return userRepository.findById(userId); // Devuelve un Optional<User>
    }

    /**
     * Retrieves all users as a list of {@link UserDTO}.
     *
     * @return a list of {@link UserDTO} representing all users
     */
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDTO).toList();
    }

    /**
     * Creates a new user and returns the {@link UserDTO} of the created user.
     *
     * @param user the {@link User} entity to create
     * @return the {@link UserDTO} of the created user
     * @throws RuntimeException if the email is already registered
     */
    public UserDTO createUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already registered");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Encrypt password
        return userMapper.toDTO(userRepository.save(user));
    }

    /**
     * Deletes a user by its ID.
     *
     * @param id the ID of the user to delete
     */
    public void deleteUser(Double id) {
        userRepository.deleteById(id);
    }

    /**
     * Updates an existing user and returns the updated {@link UserDTO}.
     *
     * @param id the ID of the user to update
     * @param user the updated {@link User} data
     * @return the {@link UserDTO} of the updated user
     * @throws RuntimeException if the user is not found
     */
    public UserDTO updateUser(Double id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setPhone(user.getPhone());
        existingUser.setAddress(user.getAddress());
        return userMapper.toDTO(userRepository.save(existingUser));
    }

    /**
     * Updates a user's password.
     *
     * @param id the ID of the user to update
     * @param oldPassword the current password to verify
     * @param newPassword the new password to set
     * @throws RuntimeException if the current password is incorrect
     * @throws RuntimeException if the user is not found
     */
    public void updatePassword(Double id, String oldPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
            throw new RuntimeException("Incorrect current password");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    /**
     * Updates a user's address and returns the updated {@link UserDTO}.
     *
     * @param id the ID of the user to update
     * @param newAddress the new address to set
     * @return the {@link UserDTO} of the updated user
     * @throws RuntimeException if the user is not found
     */
    public UserDTO updateUserAddress(Double id, String newAddress) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setAddress(newAddress);
        return userMapper.toDTO(userRepository.save(user));
    }

    /**
     * Verifies if the current password matches the stored password.
     *
     * @param id the ID of the user to verify
     * @param currentPassword the current password to check
     * @throws RuntimeException if the password does not match
     * @throws RuntimeException if the user is not found
     */
    public void verifyPassword(Double id, String currentPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        
        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new RuntimeException("Incorrect password");
        }
    }

    /**
     * Resets a user's password and saves it.
     *
     * @param id the ID of the user to reset the password for
     * @param newPassword the new password to set
     * @throws RuntimeException if the user is not found
     */
    public void resetPassword(Double id, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPassword(passwordEncoder.encode(newPassword)); // Encrypt new password
        userRepository.save(user);
    }

    /**
     * Updates a user's phone number and returns the updated {@link UserDTO}.
     *
     * @param id the ID of the user to update
     * @param newPhone the new phone number to set
     * @return the {@link UserDTO} of the updated user
     * @throws RuntimeException if the user is not found
     */
    public UserDTO updateUserPhone(Double id, String newPhone) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setPhone(newPhone);
        return userMapper.toDTO(userRepository.save(user));
    }

    /**
     * Retrieves a {@link UserDTO} based on the user's email.
     * 
     * This method fetches a {@link User} from the database using the provided email.
     * If the user with the specified email is not found, it throws an exception.
     * 
     * @param email the email of the user to retrieve
     * @return a {@link UserDTO} containing the user's information
     * @throws RuntimeException if no user with the specified email is found
     */
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with the specified email not found"));
        return userMapper.toDTO(user);
    }

}
