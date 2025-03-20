package com.recordstore.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.recordstore.enums.PORTABLE_TYPE;
import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.RESISTANCE;
import com.recordstore.model.Portable;

/**
 * Repository for managing {@link Portable} entities.
 * It provides methods for CRUD operations on the {@link Portable} entity.
 */
public interface PortableRepository extends JpaRepository<Portable, Integer> {

    /**
     * Deletes a portable by its id.
     *
     * @param id the id of the portable to delete.
     */
    void deleteById(@NonNull Integer id);

    /**
     * Checks if a portable with the given id exists.
     *
     * @param id the id to check.
     * @return true if a portable with the given id exists, false otherwise.
     */
    boolean existsById(@NonNull Integer id);

    /**
     * Finds a portable by its id.
     *
     * @param id the id of the portable.
     * @return an Optional containing the portable if found, otherwise empty.
     */
    @NonNull
    Optional<Portable> findById(@NonNull Integer id);

    /**
     * Finds players by name.
     *
     * @param name the name of the portable.
     * @return a list of players matching the given name.
     */
    List<Portable> findByName(String name);

    /**
     * Finds players within a specified price range.
     *
     * @param minPrice the minimum price.
     * @param maxPrice the maximum price.
     * @return a list of players within the specified price range.
     */
    List<Portable> findByPriceBetween(Double minPrice, Double maxPrice);

    /**
     * Finds players by stock availability.
     *
     * @param stock the stock count.
     * @return a list of players with the specified stock.
     */
    List<Portable> findByStock(Integer stock);

    /**
     * Finds players by brand.
     *
     * @param brand the brand name.
     * @return a list of players of the specified brand.
     */
    List<Portable> findByBrand(String brand);

    /**
     * Finds players by color.
     *
     * @param color the color of the portable.
     * @return a list of players of the specified color.
     */
    List<Portable> findByColor(String color);

    /**
     * Finds players by warranty period.
     *
     * @param warranty the warranty period in months.
     * @return a list of players with the specified warranty period.
     */
    List<Portable> findByWarranty(Integer warranty);

    /**
     * Finds players with Bluetooth capability.
     *
     * @param bluetooth true if the portable has Bluetooth, false otherwise.
     * @return a list of players with Bluetooth capability.
     */
    List<Portable> findByBluetooth(Boolean bluetooth);

    /**
     * Finds players with USB support.
     *
     * @param usb true if the portable supports USB, false otherwise.
     * @return a list of players with USB support.
     */
    List<Portable> findByUsb(Boolean usb);

    /**
     * Finds players with radio functionality.
     *
     * @param radio true if the portable has a radio, false otherwise.
     * @return a list of players with a radio.
     */
    List<Portable> findByRadio(Boolean radio);

    /**
     * Finds players with AUX input support.
     *
     * @param aux true if the portable has an AUX input, false otherwise.
     * @return a list of players with AUX input support.
     */
    List<Portable> findByAux(Boolean aux);

    /**
     * Finds players with RCA connectivity.
     *
     * @param rca true if the portable has RCA connectivity, false otherwise.
     * @return a list of players with RCA connectivity.
     */
    List<Portable> findByRca(Boolean rca);

    /**
     * Finds players with built-in speakers.
     *
     * @param builtInSpeaker true if the portable has built-in speakers, false otherwise.
     * @return a list of players with built-in speakers.
     */
    List<Portable> findByBuiltInSpeaker(Boolean builtInSpeaker);

    /**
     * Finds players containing the given name in their model.
     *
     * @param name the name fragment to search for.
     * @return an Optional containing the matching portable if found, otherwise empty.
     */
    Optional<Portable> findByNameContaining(String name);

    /**
     * Finds all portables with the specified portable type.
     *
     * @param portableType the portable type (e.g., speaker, headphone).
     * @return a list of portables that match the given portable type.
     */
    Optional<Portable> findByPortableType(PORTABLE_TYPE portableType);

    /**
     * Finds all portables with the specified power type.
     *
     * @param powerType the power type (e.g., battery, AC).
     * @return a list of portables that match the given power type.
     */
    Optional<Portable> findByPowerType(POWER_TYPE powerType);

    /**
     * Finds all portables with the specified battery life.
     *
     * @param batteryLife the battery life in hours.
     * @return a list of portables that match the given battery life.
     */
    Optional<Portable> findByBatteryLife(Integer batteryLife);

    /**
     * Finds all portables with the specified resistance.
     *
     * @param resistance the resistance (e.g., IPX7, IPX8).
     * @return a list of portables that match the given resistance.
     */
    Optional<Portable> findByResistance(RESISTANCE resistance);

    /**
     * Finds all portables with the specified warranty period.
     *
     * @param batteryLife the warranty period in months.
     * @return a list of portables that match the given warranty period.
     */
    Optional<Portable> findByBatteryLifeGreaterThanEqual(Integer batteryLife);

    /**
     * Finds all portables with the specified warranty period.
     *
     * @param warranty the warranty period in months.
     * @return a list of portables that match the given warranty period.
     */
    Optional<Portable> findByWarrantyGreaterThanEqual(Integer warranty);
}