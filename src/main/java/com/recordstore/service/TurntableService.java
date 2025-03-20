package com.recordstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recordstore.model.Turntable;
import com.recordstore.repository.TurntableRepository;

import java.util.Optional;

/**
 * Service class that handles operations related to the Turntable entity.
 * <p>
 * This service provides methods to perform CRUD operations on Turntable
 * objects, including
 * managing the attributes such as built-in preamp, allowed RPM, traction type,
 * and mechanism.
 * </p>
 * 
 * @see Turntable
 */
@Service
public class TurntableService {

    @Autowired
    private TurntableRepository turntableRepository;

    /**
     * Updates an existing Turntable in the database with the given fields.
     * Only the fields provided in the input will be updated, and the rest will
     * remain unchanged.
     *
     * @param id        The ID of the Turntable to be updated.
     * @param turntable The Turntable object with updated fields.
     * @return The updated Turntable, or an empty Optional if the Turntable is not
     *         found.
     */
    public Optional<Turntable> updateTurntable(Double id, Turntable turntable) {
        Optional<Turntable> existingTurntable = turntableRepository.findById(id);
        if (existingTurntable.isPresent()) {
            Turntable updatedTurntable = existingTurntable.get();

            // Update only the provided fields
            if (turntable.getName() != null) {
                updatedTurntable.setName(turntable.getName());
            }
            if (turntable.getPrice() != null) {
                updatedTurntable.setPrice(turntable.getPrice());
            }
            if (turntable.getStock() != null) {
                updatedTurntable.setStock(turntable.getStock());
            }
            if (turntable.getProductCategory() != null) {
                updatedTurntable.setProductCategory(turntable.getProductCategory());
            }
            if (turntable.getBrand() != null) {
                updatedTurntable.setBrand(turntable.getBrand());
            }
            if (turntable.getColor() != null) {
                updatedTurntable.setColor(turntable.getColor());
            }
            if (turntable.getWarranty() != null) {
                updatedTurntable.setWarranty(turntable.getWarranty());
            }

            // Update boolean fields only if they are explicitly provided (using Boolean
            // instead of boolean)
            if (turntable.getBluetooth() != null) {
                updatedTurntable.setBluetooth(turntable.getBluetooth());
            }
            if (turntable.getUsb() != null) {
                updatedTurntable.setUsb(turntable.getUsb());
            }
            if (turntable.getRadio() != null) {
                updatedTurntable.setRadio(turntable.getRadio());
            }
            if (turntable.getAux() != null) {
                updatedTurntable.setAux(turntable.getAux());
            }
            if (turntable.getRca() != null) {
                updatedTurntable.setRca(turntable.getRca());
            }
            if (turntable.getBuiltInSpeaker() != null) {
                updatedTurntable.setBuiltInSpeaker(turntable.getBuiltInSpeaker());
            }
            if (turntable.getHasBuiltInPreAmp() != null) {
                updatedTurntable.setHasBuiltInPreAmp(turntable.getHasBuiltInPreAmp());
            }

            // Update other fields if present
            if (turntable.getRpm() != null) {
                updatedTurntable.setRpm(turntable.getRpm());
            }
            if (turntable.getTraction() != null) {
                updatedTurntable.setTraction(turntable.getTraction());
            }
            if (turntable.getMechanism() != null) {
                updatedTurntable.setMechanism(turntable.getMechanism());
            }

            return Optional.of(turntableRepository.save(updatedTurntable));
        }
        return Optional.empty();
    }
}
