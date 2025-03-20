package com.recordstore.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recordstore.dto.PlayerDTO;
import com.recordstore.mapper.PlayerMapper;
import com.recordstore.model.Order;
import com.recordstore.model.Player;
import com.recordstore.model.Wishlist;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.PlayerRepository;
import com.recordstore.repository.WishlistRepository;



/**
 * Service class responsible for managing operations related to the Player entity.
 * Provides methods to create, update, delete, and retrieve players.
 */


@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;
    private final OrderRepository orderRepository;
    private final WishlistRepository wishlistRepository;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper, OrderRepository orderRepository, WishlistRepository wishlistRepository) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
        this.orderRepository = orderRepository;
        this.wishlistRepository = wishlistRepository;

    }

    /**
     * Creates a new Player based on the provided PlayerDTO.
     *
     * @param playerDTO The PlayerDTO with the details of the player to be created.
     * @return The created PlayerDTO.
     */
    public PlayerDTO addPlayer(PlayerDTO playerDTO) {
        // Convert PlayerDTO to Player entity
        Player player = playerMapper.toEntity(playerDTO);
        // Save the Player entity to the database
        player = playerRepository.save(player);
        // Convert the saved Player entity to PlayerDTO and return it
        return playerMapper.toDTO(player);
    }

    /**
     * Updates an existing Player based on the provided PlayerDTO.
     * Only non-null fields from the DTO will be updated.
     *
     * @param id       The id of the player to be updated.
     * @param playerDTO The PlayerDTO containing the updated details.
     * @return The updated PlayerDTO, or null if the Player was not found.
     */
    public PlayerDTO updatePlayer(Integer id, PlayerDTO playerDTO) {
        Optional<Player> existingPlayerOpt = playerRepository.findById(id);

        if (existingPlayerOpt.isPresent()) {
            Player existingPlayer = existingPlayerOpt.get();

            // Update only non-null fields from the PlayerDTO
            if (playerDTO.getName() != null) {
                existingPlayer.setName(playerDTO.getName());
            }
            if (playerDTO.getPrice() != null) {
                existingPlayer.setPrice(playerDTO.getPrice());
            }
            if (playerDTO.getStock() != null) {
                existingPlayer.setStock(playerDTO.getStock());
            }
            if (playerDTO.getBrand() != null) {
                existingPlayer.setBrand(playerDTO.getBrand());
            }
            if (playerDTO.getColor() != null) {
                existingPlayer.setColor(playerDTO.getColor());
            }
            if (playerDTO.getWarranty() != null) {
                existingPlayer.setWarranty(playerDTO.getWarranty());
            }
            if (playerDTO.getBluetooth() != null) {
                existingPlayer.setBluetooth(playerDTO.getBluetooth());
            }
            if (playerDTO.getUsb() != null) {
                existingPlayer.setUsb(playerDTO.getUsb());
            }
            if (playerDTO.getRadio() != null) {
                existingPlayer.setRadio(playerDTO.getRadio());
            }
            if (playerDTO.getAux() != null) {
                existingPlayer.setAux(playerDTO.getAux());
            }
            if (playerDTO.getRca() != null) {
                existingPlayer.setRca(playerDTO.getRca());
            }
            if (playerDTO.getBuiltInSpeaker() != null) {
                existingPlayer.setBuiltInSpeaker(playerDTO.getBuiltInSpeaker());
            }

            // Save the updated Player entity
            existingPlayer = playerRepository.save(existingPlayer);
            // Convert the updated Player entity to PlayerDTO and return it
            return playerMapper.toDTO(existingPlayer);
        }

        // Return null if the player with the given id doesn't exist
        return null;
    }

    /**
     * Deletes a Player by its id.
     *
     * @param id The id of the Player to be deleted.
     */
    @Transactional
    public void deletePlayerById(Integer id) {
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

        // If it is not in any order or wishlist, delete the product
        playerRepository.deleteById(id);
    }

    /**
     * Retrieves a Player by its id.
     *
     * @param id The id of the Player to be retrieved.
     * @return The PlayerDTO if found, otherwise an empty Optional.
     */
    public Optional<PlayerDTO> getPlayerById(Integer id) {
        Optional<Player> playerOpt = playerRepository.findById(id);
        return playerOpt.map(playerMapper::toDTO);
    }

    /**
     * Retrieves all Players.
     *
     * @return A list of PlayerDTOs representing all players.
     */
    public List<PlayerDTO> getAllPlayers() {
        List<Player> players = playerRepository.findAll();
        return players.stream()
                .map(playerMapper::toDTO)
                .toList();
    }

/**
 * Retrieves a list of players that have an aux port.
 * 
 * @param aux whether the player has an aux port.
 * @return a list of players with an aux port.
 */
public List<PlayerDTO> findByAux(Boolean aux) {
    return playerRepository.findByAux(aux)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}

/**
 * Retrieves a list of players that have a USB port.
 * 
 * @param usb whether the player has a USB port.
 * @return a list of players with a USB port.
 */
public List<PlayerDTO> findByUsb(Boolean usb) {
    return playerRepository.findByUsb(usb)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}

/**
 * Retrieves a list of players that have Bluetooth connectivity.
 * 
 * @param bluetooth whether the player has Bluetooth.
 * @return a list of players with Bluetooth connectivity.
 */
public List<PlayerDTO> findByBluetooth(Boolean bluetooth) {
    return playerRepository.findByBluetooth(bluetooth)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}

/**
 * Retrieves a list of players within a specified warranty period.
 * 
 * @param warranty the warranty period in months.
 * @return a list of players with the specified warranty period.
 */
public List<PlayerDTO> findByWarranty(Integer warranty) {

    return playerRepository.findByWarranty(warranty)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}

/**
 * Retrieves a list of players by brand.
 * 
 * @param brand the brand of the player.
 * @return a list of players with the specified brand.
 */
public List<PlayerDTO> findByBrand(String brand) {
 
    return playerRepository.findByBrand(brand)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}

/**
 * Retrieves a list of players by color.
 * 
 * @param color the color of the player.
 * @return a list of players with the specified color.
 */
public List<PlayerDTO> findByColor(String color) {

    return playerRepository.findByColor(color)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}

/**
 * Retrieves a list of players by available stock.
 * 
 * @param stock the available stock of the player.
 * @return a list of players with the specified stock.
 */
public List<PlayerDTO> findByStock(Integer stock) {
    return playerRepository.findByStock(stock)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}

/**
 * Retrieves a list of players within a specified price range.
 * 
 * @param minPrice the minimum price.
 * @param maxPrice the maximum price.
 * @return a list of players within the price range.
 */
public List<PlayerDTO> findByPriceRange(Double minPrice, Double maxPrice) {
    return playerRepository.findByPriceBetween(minPrice, maxPrice)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}

/**
 * Retrieves a list of players by model name.
 * 
 * @param name the model name of the player.
 * @return a list of players with the specified model name.
 */
public List<PlayerDTO> findByName(String name) {
    return playerRepository.findByNameContaining(name)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}


/**
 * Retrieves a list of players that have a radio.
 * 
 * @param radio whether the player has a radio.
 * @return a list of players with a radio.
 */
public List<PlayerDTO> findByRadio(Boolean radio) {
    return playerRepository.findByRadio(radio)
                           .stream()
                           .map(player -> playerMapper.toDTO(player))
                           .collect(Collectors.toList());
}
}
