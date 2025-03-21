package com.recordstore.dto;

import java.util.ArrayList;
import java.util.List;

import com.recordstore.model.Order;
import com.recordstore.model.User;
import com.recordstore.model.Wishlist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * Data Transfer Object (DTO) for {@link User}.
 * <p>
 * This DTO is used to transfer user data between different layers of the
 * application,
 * such as from the controller to the service. It contains basic user
 * information,
 * including name, email, phone number, address, password, as well as associated
 * orders
 * and wishlist.
 * </p>
 * 
 * 
 * Example of usage:
 * 
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
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String email;
    private String phone;
    private String address;
    @NotBlank
    @NotNull
    private String password;
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
        this.password = user.getPassword();

    }

 /**
  * Constructs a {@link UserDTO} with the given parameters.
  * @param name the name of the user
  * @param email the email of the user
  * @param phone  the phone number of the user
  * @param address the address of the user
  * @param password the password of the user
  */

    public UserDTO(String name, String email, String phone, String address, String password) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.password = password;

    }

}
