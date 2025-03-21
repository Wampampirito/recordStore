package com.recordstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recordstore.dto.PortableDTO;
import com.recordstore.enums.PORTABLE_TYPE;
import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.RESISTANCE;
import com.recordstore.service.PortableService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * REST controller for managing Portable devices.
 * Provides methods for saving, updating, deleting, and searching Portables.
 * List of all endpoints:
 * 
 * POST /portable/new - Save a new Portable
 * PUT /portable/{id} - Update an existing Portable
 * DELETE /portable/{id} - Delete a Portable by its ID
 * GET /portable/{id} - Get a Portable by its ID
 * GET /portable/all - Get all Portables
 * GET /portable/aux/{aux} - Find Portables with AUX port
 * GET /portable/usb/{usb} - Find Portables with USB port
 * GET /portable/bluetooth/{bluetooth} - Find Portables with Bluetooth
 * GET /portable/warranty/{warranty} - Find Portables by warranty
 * GET /portable/brand/{brand} - Find Portables by brand
 * GET /portable/color/{color} - Find Portables by color
 * GET /portable/stock/{stock} - Find Portables by stock
 * GET /portable/price-range - Find Portables by price range
 * GET /portable/name/{name} - Find Portables by name
 * GET /portable/radio/{radio} - Find Portables with built-in radio
 * GET /portable/type/{portableType} - Find Portables by type
 * GET /portable/power/{powerType} - Find Portables by power type
 * GET /portable/battery/{batteryLife} - Find Portables by battery life
 * GET /portable/resistance/{resistance} - Find Portables by resistance type
 */
@RestController
@RequestMapping("/portable")
@Tag(name = "PortableController", description = "Endpoints for managing portable devices")
public class PortableController {

    @Autowired
    private PortableService portableService;

    /**
     * Saves a new Portable object in the database.
     *
     * @param portableDTO The DTO representing the Portable to save.
     */
    @Operation(summary = "Save a new Portable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portable saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/new")
    public void savePortable(@RequestBody PortableDTO portableDTO) {
        portableService.savePortable(portableDTO);
    }

    /**
     * Updates an existing Portable in the database.
     *
     * @param id          The ID of the Portable to update.
     * @param portableDTO The DTO representing the Portable data to update.
     */
    @Operation(summary = "Update an existing Portable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portable updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Portable not found")
    })
    @PutMapping("/{id}")
    public void updatePortable(@PathVariable Integer id, @RequestBody PortableDTO portableDTO) {
        portableService.updatePortable(id, portableDTO);
    }

    /**
     * Deletes a Portable by its ID if it is not associated with any orders or
     * wishlists.
     *
     * @param id The ID of the Portable to delete.
     * @return A ResponseEntity with a message indicating the result of the
     *         operation.
     */
    @Operation(summary = "Delete a Portable by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portable deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Portable not found"),
            @ApiResponse(responseCode = "400", description = "Portable is associated with orders or wishlists")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePortableById(@PathVariable Integer id) {
        try {
            portableService.deletePortable(id);
            return ResponseEntity.ok("Product successfully deleted with ID: " + id);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the product.");
        }
    }

    /**
     * Retrieves a Portable by its ID.
     *
     * @param id The ID of the Portable to retrieve.
     * @return The DTO representing the Portable.
     */
    @Operation(summary = "Get a Portable by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portable retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Portable not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PortableDTO> getPortableById(@PathVariable Integer id) {
        Optional<PortableDTO> portableDTO = portableService.getPortableById(id);

        // Verificamos si el PortableDTO está presente
        if (portableDTO.isPresent()) {
            return ResponseEntity.ok(portableDTO.get()); // Retorna un 200 con el PortableDTO
        } else {
            return ResponseEntity.notFound().build(); // Retorna un 404 si no se encuentra
        }
    }

    /**
     * Retrieves all Portables.
     *
     * @return A list of all Portables.
     */
    @Operation(summary = "Get all Portables")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping ("/all")
    public List<PortableDTO> getAllPortables() {
        return portableService.getAllPortables();
    }

    /**
     * Searches for Portables that have an AUX port.
     *
     * @param aux Boolean value indicating if the portable should have an AUX port.
     * @return A list of Portables that have an AUX port.
     */
    @Operation(summary = "Find Portables with AUX port")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/aux/{aux}")
    public List<PortableDTO> findByAux(@PathVariable Boolean aux) {
        return portableService.findByAux(aux);
    }

    /**
     * Searches for Portables that have a USB port.
     *
     * @param usb Boolean value indicating if the portable should have a USB port.
     * @return A list of Portables that have a USB port.
     */
    @Operation(summary = "Find Portables with USB port")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/usb/{usb}")
    public List<PortableDTO> findByUsb(@PathVariable Boolean usb) {
        return portableService.findByUsb(usb);
    }

    /**
     * Searches for Portables that have Bluetooth.
     *
     * @param bluetooth Boolean value indicating if the portable should have
     *                  Bluetooth.
     * @return A list of Portables that have Bluetooth.
     */
    @Operation(summary = "Find Portables with Bluetooth")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/bluetooth/{bluetooth}")
    public List<PortableDTO> findByBluetooth(@PathVariable Boolean bluetooth) {
        return portableService.findByBluetooth(bluetooth);
    }

    /**
     * Searches for Portables with a minimum warranty value.
     *
     * @param warranty The minimum warranty value.
     * @return A list of Portables with a warranty greater than or equal to the
     *         provided value.
     */
    @Operation(summary = "Find Portables by warranty")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/warranty/{warranty}")
    public List<PortableDTO> findByWarranty(@PathVariable Integer warranty) {
        return portableService.findByWarranty(warranty);
    }

    /**
     * Searches for Portables by brand.
     *
     * @param brand The brand to search for.
     * @return A list of Portables that match the brand.
     */
    @Operation(summary = "Find Portables by brand")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/brand/{brand}")
    public List<PortableDTO> findByBrand(@PathVariable String brand) {
        return portableService.findByBrand(brand);
    }

    /**
     * Searches for Portables by color.
     *
     * @param color The color to search for.
     * @return A list of Portables that match the color.
     */
    @Operation(summary = "Find Portables by color")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/color/{color}")
    public List<PortableDTO> findByColor(@PathVariable String color) {
        return portableService.findByColor(color);
    }

    /**
     * Searches for Portables with a specific stock quantity.
     *
     * @param stock The stock quantity to search for.
     * @return A list of Portables that match the stock quantity.
     */
    @Operation(summary = "Find Portables by stock")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/stock/{stock}")
    public List<PortableDTO> findByStock(@PathVariable Integer stock) {
        return portableService.findByStock(stock);
    }

    /**
     * Searches for Portables within a specific price range.
     *
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of Portables within the given price range.
     */
    @Operation(summary = "Find Portables by price range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/price-range")
    public List<PortableDTO> findByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        return portableService.findByPriceRange(minPrice, maxPrice);
    }

    /**
     * Searches for Portables by name.
     *
     * @param name The name to search for.
     * @return A list of Portables that match the name.
     */
    @Operation(summary = "Find Portables by name")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/name/{name}")
    public List<PortableDTO> findByName(@PathVariable String name) {
        return portableService.findByName(name);
    }

    /**
     * Searches for Portables that have a built-in radio.
     *
     * @param radio Boolean value indicating if the portable should have a built-in
     *              radio.
     * @return A list of Portables that have a radio.
     */
    @Operation(summary = "Find Portables with built-in radio")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/radio/{radio}")
    public List<PortableDTO> findByRadio(@PathVariable Boolean radio) {
        return portableService.findByRadio(radio);
    }

    /**
     * Searches for Portables by type.
     *
     * @param portableType The type of Portable.
     * @return A list of Portables that match the given type.
     */
    @Operation(summary = "Find Portables by type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/type/{portableType}")
    public List<PortableDTO> findByPortableType(@PathVariable PORTABLE_TYPE portableType) {
        return portableService.findByPortableType(portableType);
    }

    /**
     * Searches for Portables by power type.
     *
     * @param powerType The power type to search for.
     * @return A list of Portables that match the power type.
     */
    @Operation(summary = "Find Portables by power type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/power/{powerType}")
    public List<PortableDTO> findByPowerType(@PathVariable POWER_TYPE powerType) {
        return portableService.findByPowerType(powerType);
    }

    /**
     * Searches for Portables by battery life.
     *
     * @param batteryLife The minimum battery life.
     * @return A list of Portables with battery life greater than or equal to the
     *         given value.
     */
    @Operation(summary = "Find Portables by battery life")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/battery/{batteryLife}")
    public List<PortableDTO> findByBatteryLife(@PathVariable Integer batteryLife) {
        return portableService.findByBatteryLife(batteryLife);
    }

    /**
     * Searches for Portables by resistance type.
     *
     * @param resistance The resistance type to search for.
     * @return A list of Portables that match the resistance type.
     */
    @Operation(summary = "Find Portables by resistance type")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Portables retrieved successfully")
    })
    @GetMapping("/resistance/{resistance}")
    public List<PortableDTO> findByResistance(@PathVariable RESISTANCE resistance) {
        return portableService.findByResistance(resistance);
    }
}
