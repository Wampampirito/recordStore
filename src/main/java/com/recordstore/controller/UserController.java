package com.recordstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recordstore.dto.UserDTO;
import com.recordstore.mapper.UserMapper;
import com.recordstore.model.User;
import com.recordstore.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

/**
 * REST controller for managing users in the system.
 * <p>
 * Provides endpoints for CRUD operations, password management, and user details updates.
 * </p>
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    /**
     * Constructor to inject user service and user mapper.
     *
     * @param userService the service handling business logic for users
     * @param userMapper  the mapper to convert between entity and DTO
     */
    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    /**
     * Retrieves a list of all users.
     *
     * @return a list of {@link UserDTO}
     */
    @Operation(summary = "Get all users", description = "Retrieve a list of all users.")
    @ApiResponse(responseCode = "200", description = "List of users retrieved successfully")
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    /**
     * Retrieves a user by their unique ID.
     *
     * @param id the user ID
     * @return the {@link UserDTO} if found, or 404 if not
     */
    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id) {
        Optional<User> userOptional = userService.getUserById(id);

        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userMapper.toDTO(userOptional.get()));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email the user's email
     * @return the {@link UserDTO} if found
     */
    @Operation(summary = "Get user by email", description = "Retrieve a user by their email address.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(
            @Parameter(description = "User email", required = true) @PathVariable String email) {
        User user = userMapper.toEntity(userService.getUserByEmail(email));
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    /**
     * Creates a new user in the system.
     *
     * @param userDTO the user data
     * @return the created {@link UserDTO}
     */
    @Operation(summary = "Create a new user", description = "Create a new user in the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User created successfully"),
            @ApiResponse(responseCode = "409", description = "Email already registered")
    })
    @PostMapping("/new")
    public ResponseEntity<UserDTO> createUser(
            @Parameter(description = "User data", required = true) @RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User savedUser = userMapper.toEntity(userService.saveUser(user));
        return ResponseEntity.ok(userMapper.toDTO(savedUser));
    }

    /**
     * Updates the details of an existing user.
     *
     * @param id      the user ID
     * @param userDTO the updated user data
     * @return the updated {@link UserDTO}
     */
    @Operation(summary = "Update user details", description = "Update the details of an existing user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id,
            @Parameter(description = "Updated user data", required = true) @RequestBody UserDTO userDTO) {
        User user = userMapper.toEntity(userDTO);
        User updatedUser = userMapper.toEntity(userService.updateUser(id, user));
        return ResponseEntity.ok(userMapper.toDTO(updatedUser));
    }

    /**
     * Updates the password of an existing user.
     *
     * @param id          the user ID
     * @param oldPassword the current password
     * @param newPassword the new password
     * @return a success message
     */
    @Operation(summary = "Update user password", description = "Update the password of an existing user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password updated successfully"),
            @ApiResponse(responseCode = "401", description = "Incorrect current password"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PatchMapping("/{id}/password")
    public ResponseEntity<String> updatePassword(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id,
            @Parameter(description = "Old password", required = true) @RequestParam String oldPassword,
            @Parameter(description = "New password", required = true) @RequestParam String newPassword) {
        userService.updatePassword(id, oldPassword, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }

    /**
     * Deletes a user from the system.
     *
     * @param id the user ID
     * @return a success message
     */
    @Operation(summary = "Delete a user", description = "Delete an existing user from the system.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    /**
     * Updates the address of an existing user.
     *
     * @param id         the user ID
     * @param newAddress the new address
     * @return the updated {@link UserDTO}
     */
    @Operation(summary = "Update user address", description = "Update the address of an existing user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Address updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PatchMapping("/{id}/address")
    public ResponseEntity<UserDTO> updateUserAddress(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id,
            @Parameter(description = "New address", required = true) @RequestParam String newAddress) {
        return ResponseEntity.ok(userService.updateUserAddress(id, newAddress));
    }

    /**
     * Verifies the current password of a user.
     *
     * @param id             the user ID
     * @param currentPassword the current password
     * @return a success message
     */
    @Operation(summary = "Verify user password", description = "Verify the current password of a user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password verified successfully"),
            @ApiResponse(responseCode = "401", description = "Incorrect password")
    })
    @PostMapping("/{id}/verify-password")
    public ResponseEntity<String> verifyPassword(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id,
            @Parameter(description = "Current password", required = true) @RequestParam String currentPassword) {
        userService.verifyPassword(id, currentPassword);
        return ResponseEntity.ok("Password verified successfully");
    }

    /**
     * Resets the password of a user.
     *
     * @param id          the user ID
     * @param newPassword the new password
     * @return a success message
     */
    @Operation(summary = "Reset user password", description = "Reset the password of a user to a new one.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password reset successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PatchMapping("/{id}/reset-password")
    public ResponseEntity<String> resetPassword(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id,
            @Parameter(description = "New password", required = true) @RequestParam String newPassword) {
        userService.resetPassword(id, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }

    /**
     * Updates the phone number of an existing user.
     *
     * @param id       the user ID
     * @param newPhone the new phone number
     * @return the updated {@link UserDTO}
     */
    @Operation(summary = "Update user phone", description = "Update the phone number of an existing user.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Phone number updated successfully"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PatchMapping("/{id}/phone")
    public ResponseEntity<UserDTO> updateUserPhone(
            @Parameter(description = "User ID", required = true) @PathVariable Integer id,
            @Parameter(description = "New phone number", required = true) @RequestParam String newPhone) {
        return ResponseEntity.ok(userService.updateUserPhone(id, newPhone));
    }
}
