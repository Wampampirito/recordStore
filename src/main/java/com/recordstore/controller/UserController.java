package com.recordstore.controller;

import java.util.List;
import java.util.Optional;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recordstore.dto.UserDTO;
import com.recordstore.mapper.UserMapper;
import com.recordstore.model.User;
import com.recordstore.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @Operation(summary = "Get all users", description = "Retrieve a list of all users.")
    @GetMapping ("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(
        @Parameter(description = "User ID", required = true) @PathVariable Double id) {
    Optional<User> userOptional = userService.getUserById(id);

    if (userOptional.isPresent()) {
        UserDTO userDTO = userMapper.toDTO(userOptional.get()); 
        return ResponseEntity.ok(userDTO);
    } else {
        return ResponseEntity.notFound().build(); 
    }
}

    @Operation(summary = "Get user by email", description = "Retrieve a user by their email address.")
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDTO> getUserByEmail(
        @Parameter(description = "User email", required = true) @PathVariable String email) {
        
        User user = userMapper.toEntity(userService.getUserByEmail(email));
        return ResponseEntity.ok(userMapper.toDTO(user));
    }

    @Operation(summary = "Create a new user", description = "Create a new user in the system.")
    @PostMapping ("/new")
    public ResponseEntity<UserDTO> createUser(
        @Parameter(description = "User data", required = true) @RequestBody UserDTO userDTO) {
        
        User user = userMapper.toEntity(userDTO);
        User savedUser = userMapper.toEntity(userService.saveUser(user));
        return ResponseEntity.ok(userMapper.toDTO(savedUser));
    }

    @Operation(summary = "Update user details", description = "Update the details of an existing user.")
    @PutMapping("/update/{id}")
    public ResponseEntity<UserDTO> updateUser(
        @Parameter(description = "User ID", required = true) @PathVariable Double id,
        @Parameter(description = "Updated user data", required = true) @RequestBody UserDTO userDTO) {
        
        User user = userMapper.toEntity(userDTO);
        User updatedUser = userMapper.toEntity(userService.updateUser(id, user));
        return ResponseEntity.ok(userMapper.toDTO(updatedUser));
    }

    @Operation(summary = "Update user password", description = "Update the password of an existing user.")
    @PatchMapping("/{id}/password")
    public ResponseEntity<String> updatePassword(
        @Parameter(description = "User ID", required = true) @PathVariable Double id,
        @Parameter(description = "Old password", required = true) @RequestParam String oldPassword,
        @Parameter(description = "New password", required = true) @RequestParam String newPassword) {
        
        userService.updatePassword(id, oldPassword, newPassword);
        return ResponseEntity.ok("Password updated successfully");
    }

    @Operation(summary = "Delete a user", description = "Delete an existing user from the system.")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteUser(
        @Parameter(description = "User ID", required = true) @PathVariable Double id) {
        
        userService.deleteUser(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    @Operation(summary = "Update user address", description = "Update the address of an existing user.")
    @PatchMapping("/{id}/address")
    public ResponseEntity<UserDTO> updateUserAddress(
        @Parameter(description = "User ID", required = true) @PathVariable Double id, 
        @Parameter(description = "New address", required = true) @RequestBody String newAddress) {
        
        return ResponseEntity.ok(userService.updateUserAddress(id, newAddress));
    }

    @Operation(summary = "Verify user password", description = "Verify the current password of a user.")
    @PostMapping("/{id}/verify-password")
    public ResponseEntity<String> verifyPassword(
        @Parameter(description = "User ID", required = true) @PathVariable Double id,
        @Parameter(description = "Current password", required = true) @RequestParam String currentPassword) {
        
        userService.verifyPassword(id, currentPassword);
        return ResponseEntity.ok("Password verified successfully");
    }

    @Operation(summary = "Reset user password", description = "Reset the password of a user to a new one.")
    @PatchMapping("/{id}/reset-password")
    public ResponseEntity<String> resetPassword(
        @Parameter(description = "User ID", required = true) @PathVariable Double id,
        @Parameter(description = "New password", required = true) @RequestParam String newPassword) {
        
        userService.resetPassword(id, newPassword);
        return ResponseEntity.ok("Password reset successfully");
    }

    @Operation(summary = "Update user phone", description = "Update the phone number of an existing user.")
    @PatchMapping("/{id}/phone")
    public ResponseEntity<UserDTO> updateUserPhone(
        @Parameter(description = "User ID", required = true) @PathVariable Double id,
        @Parameter(description = "New phone number", required = true) @RequestBody String newPhone) {
        
        return ResponseEntity.ok(userService.updateUserPhone(id, newPhone));
    }
}
