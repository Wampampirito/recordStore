package com.recordstore.mapper;

import org.springframework.stereotype.Component;

import com.recordstore.dto.UserDTO;
import com.recordstore.model.User;

/**
 * Component responsible for mapping between {@link User} entities and {@link UserDTO} DTOs.
 * 
 * The {@link UserMapper} class provides methods to convert a {@link User} entity to its corresponding 
 * {@link UserDTO} and vice versa. This is useful for separating the persistence layer (entities) 
 * from the presentation layer (DTOs), allowing for better flexibility and a cleaner architecture.
 * 
 * The provided methods are:
 * <ul>
 *   <li><b>toDTO</b>: Converts a {@link User} entity to a {@link UserDTO}.</li>
 *   <li><b>toEntity</b>: Converts a {@link UserDTO} to a {@link User} entity.</li>
 * </ul>
 * 
 * Example usage:
 * <pre>
 * UserMapper userMapper = new UserMapper();
 * UserDTO userDTO = userMapper.toDTO(user);
 * User userEntity = userMapper.toEntity(userDTO);
 * </pre>
 */
@Component
public class UserMapper {

    /**
     * Converts a {@link User} entity to its corresponding {@link UserDTO}.
     * 
     * This method takes a {@link User} entity and extracts its properties to populate a {@link UserDTO} object, 
     * which is suitable to be sent through the presentation layer or API.
     * 
     * @param user The {@link User} entity to be converted to a DTO.
     * @return A {@link UserDTO} object with the same values as the {@link User} entity.
     */
    public UserDTO toDTO(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhone(user.getPhone());
        userDTO.setAddress(user.getAddress());
        userDTO.setPassword(user.getPassword()); // Mapping password
        userDTO.setListOrder(user.getListOrder());
        userDTO.setListWishlist(user.getListWishlist());

        return userDTO;
    }

    /**
     * Converts a {@link UserDTO} to its corresponding {@link User} entity.
     * 
     * This method takes a {@link UserDTO} object and extracts its properties to populate a new {@link User} entity, 
     * which can then be persisted in the database.
     * 
     * @param userDTO The {@link UserDTO} to be converted to a {@link User} entity.
     * @return A new {@link User} entity with the values from the DTO.
     */
    public User toEntity(UserDTO userDTO) {
        User user = new User();

        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPhone(userDTO.getPhone());
        user.setAddress(userDTO.getAddress());
        user.setPassword(userDTO.getPassword()); // Setting password from DTO
        user.setListOrder(userDTO.getListOrder());
        user.setListWishlist(userDTO.getListWishlist());

        return user;
    }

}
