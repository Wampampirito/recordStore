package com.recordstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.enums.HEADPHONES_TYPE;
import com.recordstore.enums.NOISE_CANCELING;
import com.recordstore.model.Headphone;

/**
 * Repository interface for {@link Headphone} entity.
 * <p>
 * This interface extends {@link JpaRepository} and provides methods to perform CRUD operations 
 * and custom queries for the {@link Headphone} entity.
 * </p>
 * <p>
 * The repository allows filtering headsets based on various attributes like 
 * active noise cancellation (ANC), wireless connectivity, Bluetooth support, 
 * warranty period, battery life, and headphone type.
 * </p>
 * 
 * Example usage:
 * <pre>
 * Headphone headphone = headphoneRepository.getHeadphoneById(1);
 * List<Headphone> wirelessHeadphones = headphoneRepository.findByWireless(true);
 * </pre>
 */
public interface HeadphoneRepository extends JpaRepository<Headphone, Integer> {

    /**
     * Finds a headphone by its unique ID.
     * 
     * @param id the ID of the headphone.
     * @return the headphone with the specified ID.
     */
    Headphone getHeadphoneById(Integer id);

    /**
     * Finds all headphones with the specified active noise cancellation status.
     * 
     * @param anc the ANC status (e.g., active or no active cancellation).
     * @return a list of headphones that match the given ANC status.
     */
    List<Headphone> findByAnc(NOISE_CANCELING anc);

    /**
     * Finds all headphones that are wireless.
     * 
     * @param wireless the wireless status (true for wireless headphones).
     * @return a list of wireless headphones.
     */
    List<Headphone> findByWireless(Boolean wireless);

    /**
     * Finds all headphones with Bluetooth support.
     * 
     * @param bluetooth the Bluetooth status (true for Bluetooth-enabled headphones).
     * @return a list of headphones that support Bluetooth.
     */
    List<Headphone> findByBluetooth(Boolean bluetooth);

    /**
     * Finds all headphones with the specified warranty period.
     * 
     * @param warranty the warranty period in months.
     * @return a list of headphones with the given warranty period.
     */
    List<Headphone> findByWarranty(Integer warranty);

    /**
     * Finds all headphones of a specific type.
     * 
     * @param headphoneType the type of headphones (e.g., over-ear, in-ear).
     * @return a list of headphones of the specified type.
     */
    List<Headphone> findByHeadphoneType(HEADPHONES_TYPE headphoneType);

    /**
     * Finds all headphones with a battery life greater than or equal to the specified value.
     * 
     * @param batteryLife the battery life in hours.
     * @return a list of headphones with at least the specified battery life.
     */
    List<Headphone> findByBatteryLifeGreaterThanEqual(Integer batteryLife);

    /**
     * Finds all headphones with a warranty period greater than or equal to the specified value.
     * 
     * @param warranty the warranty period in months.
     * @return a list of headphones with at least the specified warranty period.
     */
    List<Headphone> findByWarrantyGreaterThanEqual(Integer warranty);
}
