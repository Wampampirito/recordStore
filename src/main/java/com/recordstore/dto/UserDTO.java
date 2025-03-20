package com.recordstore.dto;

import java.util.ArrayList;
import java.util.List;

import com.recordstore.model.Order;
import com.recordstore.model.User;
import com.recordstore.model.Wishlist;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for {@link User}.
 * <p>
 * This DTO is used to transfer user data between different layers of the application,
 * such as from the controller to the service. It contains basic user information,
 * including name, email, phone number, address, password, as well as associated orders 
 * and wishlist.
 * </p>
 * 
 * 
 * Example of usage:
 * <pre>
 * UserDTO userDTO = new UserDTO();
 * userDTO.setName("John Doe");
 * userDTO.setEmail("johndoe@example.com");
 * userDTO.setPhone("1234567890");
 * userDTO.setAddress("123 Main St");
 * userDTO.setPassword("mySecurePassword123");
 * </pre>
 * 
 * 
 * <p>
 * The {@link UserDTO} can also be created from an existing {@link User} entity.
 * </p>
 * 
 * @see User
 */
@Data
@NoArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String phone;
    private String address;
    private String password; // Added password field
    private List<Order> listOrder = new ArrayList<>();
    private Wishlist listWishlist;

    /**
     * Constructs a {@link UserDTO} from a {@link User} entity.
     * 
     * @param user the {@link User} entity to convert
     */
    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.listOrder = user.getListOrder();
        this.listWishlist = user.getListWishlist();
    }
}
