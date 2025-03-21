package com.recordstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.model.Turntable;

/**
 * Repository interface for managing {@link Turntable} entities.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods for performing basic CRUD operations
 * and custom queries on {@link Turntable} entities. Additionally, it includes methods for filtering turntables 
 * based on specific attributes such as brand, color, warranty, USB compatibility, and Bluetooth support.
 * </p>
 */
public interface TurntableRepository extends JpaRepository<Turntable, Integer> {

    /**
     * Finds all turntables that support USB connectivity.
     * 
     * @param usb boolean indicating whether to filter by USB support.
     * @return A list of {@link Turntable} entities that match the specified USB support.
     */
    List<Turntable> findByUsb(boolean usb);

    /**
     * Finds turntables by their brand and returns them as {@link Turntable}.
     * 
     * @param brand the brand of the turntable to filter by.
     * @return A list of {@link Turntable} objects corresponding to the turntables of the specified brand.
     */
    List<Turntable> findByBrand(String brand);

    /**
     * Finds turntables by their color and returns them as {@link Turntable}.
     * 
     * @param color the color of the turntable to filter by.
     * @return A list of {@link Turntable} objects corresponding to the turntables of the specified color.
     */
    List<Turntable> findByColor(String color);

    /**
     * Finds turntables with a warranty greater than or equal to the specified value.
     * 
     * @param warranty the minimum warranty duration to filter by.
     * @return A list of {@link Turntable} objects corresponding to the turntables with the specified warranty.
     */
    List<Turntable> findByWarrantyGreaterThanEqual(Integer warranty);

    /**
     * Finds turntables that support Bluetooth connectivity.
     * 
     * @param bluetooth boolean indicating whether to filter by Bluetooth support.
     * @return A list of {@link Turntable} objects corresponding to the turntables with Bluetooth support.
     */
    List<Turntable> findByBluetooth(boolean bluetooth);
}
