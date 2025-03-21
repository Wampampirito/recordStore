package com.recordstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.recordstore.model.Player;


import org.springframework.lang.NonNull;

/**
 * Repository interface for managing Player entities.
 * Provides methods to interact with the database.
 */
public interface PlayerRepository extends JpaRepository<Player, Integer> {

    /**
     * Deletes a player by its id.
     *
     * @param id the id of the player to delete.
     */
    void deleteById(@NonNull Integer id);

    /**
     * Checks if a player with the given id exists.
     *
     * @param id the id to check.
     * @return true if a player with the given id exists, false otherwise.
     */
    boolean existsById(@NonNull Integer id);

    /**
     * Finds a player by its id.
     *
     * @param id the id of the player.
     * @return an Optional containing the player if found, otherwise empty.
     */
    @NonNull
    Optional<Player> findById(@NonNull Integer id);

    /**
     * Finds players by name.
     *
     * @param name the name of the player.
     * @return a list of players matching the given name.
     */
    List<Player> findByName(String name);

    /**
     * Finds players within a specified price range.
     *
     * @param minPrice the minimum price.
     * @param maxPrice the maximum price.
     * @return a list of players within the specified price range.
     */
    List<Player> findByPriceBetween(Double minPrice, Double maxPrice);

    /**
     * Finds players by stock availability.
     *
     * @param stock the stock count.
     * @return a list of players with the specified stock.
     */
    List<Player> findByStock(Integer stock);

    /**
     * Finds players by brand.
     *
     * @param brand the brand name.
     * @return a list of players of the specified brand.
     */
    List<Player> findByBrand(String brand);

    /**
     * Finds players by color.
     *
     * @param color the color of the player.
     * @return a list of players of the specified color.
     */
    List<Player> findByColor(String color);

    /**
     * Finds players by warranty period.
     *
     * @param warranty the warranty period in months.
     * @return a list of players with the specified warranty period.
     */
    List<Player> findByWarranty(Integer warranty);

    /**
     * Finds players with Bluetooth capability.
     *
     * @param bluetooth true if the player has Bluetooth, false otherwise.
     * @return a list of players with Bluetooth capability.
     */
    List<Player> findByBluetooth(Boolean bluetooth);

    /**
     * Finds players with USB support.
     *
     * @param usb true if the player supports USB, false otherwise.
     * @return a list of players with USB support.
     */
    List<Player> findByUsb(Boolean usb);

    /**
     * Finds players with radio functionality.
     *
     * @param radio true if the player has a radio, false otherwise.
     * @return a list of players with a radio.
     */
    List<Player> findByRadio(Boolean radio);

    /**
     * Finds players with AUX input support.
     *
     * @param aux true if the player has an AUX input, false otherwise.
     * @return a list of players with AUX input support.
     */
    List<Player> findByAux(Boolean aux);

    /**
     * Finds players with RCA connectivity.
     *
     * @param rca true if the player has RCA connectivity, false otherwise.
     * @return a list of players with RCA connectivity.
     */
    List<Player> findByRca(Boolean rca);

    /**
     * Finds players with built-in speakers.
     *
     * @param builtInSpeaker true if the player has built-in speakers, false otherwise.
     * @return a list of players with built-in speakers.
     */
    List<Player> findByBuiltInSpeaker(Boolean builtInSpeaker);

    /**
     * Finds players containing the given name in their model.
     *
     * @param name the name fragment to search for.
     * @return a list containing the matching player if found, otherwise empty.
     */
    List<Player> findByNameContaining(String name);

  
}
