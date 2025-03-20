package com.recordstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recordstore.dto.TurntableDTO;
import com.recordstore.model.Turntable;
import com.recordstore.service.TurntableService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;
import java.util.Optional;

/**
 * REST Controller for managing turntables.
 */
@RestController
@RequestMapping("/turntable")
@Tag(name = "Turntables", description = "API for managing turntables")
public class TurntableController {

    private final TurntableService turntableService;

    @Autowired
    public TurntableController(TurntableService turntableService) {
        this.turntableService = turntableService;
    }

    /**
     * Retrieves all turntables.
     * 
     * @return List of all turntables as DTOs.
     */
    @Operation(summary = "Get all turntables", description = "Retrieves all turntables from the database.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of turntables")
    @GetMapping("/all")
    public ResponseEntity<List<TurntableDTO>> getAllTurntables() {
        return ResponseEntity.ok(turntableService.getAllTurntables());
    }

    /**
     * Retrieves a turntable by its ID.
     * 
     * @param id The ID of the turntable.
     * @return The corresponding TurntableDTO, if found.
     */
    @Operation(summary = "Get a turntable by ID", description = "Retrieves a specific turntable by its ID.")
    @ApiResponse(responseCode = "200", description = "Turntable found")
    @ApiResponse(responseCode = "404", description = "Turntable not found")
    @GetMapping("/{id}")
    public ResponseEntity<TurntableDTO> getTurntableById(@PathVariable Integer id) {
        return turntableService.getTurntableById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Saves a new turntable.
     * 
     * @param dto The DTO representing the turntable to save.
     * @return The saved TurntableDTO.
     */
    @Operation(summary = "Save a new turntable", description = "Creates and saves a new turntable.")
    @ApiResponse(responseCode = "200", description = "Turntable successfully saved")
    @PostMapping("/new")
    public ResponseEntity<TurntableDTO> saveTurntable(@RequestBody TurntableDTO dto) {
        return ResponseEntity.ok(turntableService.saveTurntable(dto));
    }

    /**
     * Deletes a turntable by its ID.
     * 
     * @param id The ID of the turntable to delete.
     */
    @Operation(summary = "Delete a turntable", description = "Deletes a turntable by its ID.")
    @ApiResponse(responseCode = "204", description = "Turntable successfully deleted")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTurntable(@PathVariable Integer id) {
        try {
            turntableService.deleteTurntable(id);
            return ResponseEntity.ok("Product successfully deleted with ID: " + id);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the product.");
        }
    }
    

    /**
     * Retrieves turntables filtered by brand.
     * 
     * @param brand The brand name to filter by.
     * @return List of turntables matching the brand.
     */
    @Operation(summary = "Get turntables by brand", description = "Retrieves turntables by brand.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of turntables by brand")
    @GetMapping("/brand/{brand}")
    public ResponseEntity<List<TurntableDTO>> getTurntablesByBrand(@PathVariable String brand) {
        return ResponseEntity.ok(turntableService.getTurntablesByBrand(brand));
    }

    // Other endpoints with similar annotations

    /**
     * Updates an existing turntable.
     * 
     * @param id        The ID of the Turntable to update.
     * @param turntable The Turntable object with updated fields.
     * @return The updated Turntable, or an empty Optional if not found.
     */
    @Operation(summary = "Update a turntable", description = "Updates an existing turntable with new data.")
    @ApiResponse(responseCode = "200", description = "Turntable successfully updated")
    @ApiResponse(responseCode = "404", description = "Turntable not found")
    @PutMapping("/{id}")
    public ResponseEntity<Turntable> updateTurntable(@PathVariable Integer id, @RequestBody Turntable turntable) {
        Optional<Turntable> updatedTurntable = turntableService.updateTurntable(id, turntable);
        return updatedTurntable.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
