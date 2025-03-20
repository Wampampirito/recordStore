package com.recordstore.controller;

import com.recordstore.dto.HeadphonesDTO;
import com.recordstore.enums.HEADPHONES_TYPE;
import com.recordstore.enums.NOISE_CANCELING;
import com.recordstore.service.HeadphoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/headphone")
public class HeadphoneController {

    private final HeadphoneService headphoneService;

    @Autowired
    public HeadphoneController(HeadphoneService headphoneService) {
        this.headphoneService = headphoneService;
    }

    /**
     * Retrieve a list of all headphones.
     *
     * @return List of all headphones.
     */
    @Operation(summary = "Get all headphones", description = "Retrieve a list of all headphones available in the store.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of headphones retrieved successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/all")
    public ResponseEntity<List<HeadphonesDTO>> getAllHeadphones() {
        List<HeadphonesDTO> headphones = headphoneService.getAllHeadphones();
        return new ResponseEntity<>(headphones, HttpStatus.OK);
    }

    /**
     * Retrieve a specific headphone by its id.
     *
     * @param id The id of the headphone.
     * @return The details of the headphone.
     */
    @Operation(summary = "Get headphone by id", description = "Retrieve details of a specific headphone by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Headphone found and retrieved."),
            @ApiResponse(responseCode = "404", description = "Headphone not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/{id}")
    public ResponseEntity<HeadphonesDTO> getHeadphoneById(@PathVariable Integer id) {
        try {
            HeadphonesDTO headphone = headphoneService.getHeadphoneById(id);
            return new ResponseEntity<>(headphone, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new LinkedMultiValueMap<>(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Create a new headphone.
     *
     * @param headphonesDTO The details of the headphone to create.
     * @return The created headphone.
     */
    @Operation(summary = "Create a new headphone", description = "Create a new headphone with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Headphone created successfully."),
            @ApiResponse(responseCode = "400", description = "Invalid input provided."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PostMapping("/new")
    public ResponseEntity<HeadphonesDTO> createHeadphone(@RequestBody HeadphonesDTO headphonesDTO) {
        HeadphonesDTO createdHeadphone = headphoneService.saveHeadphone(headphonesDTO);
        return new ResponseEntity<>(createdHeadphone, HttpStatus.CREATED);
    }

    /**
     * Update an existing headphone.
     *
     * @param id            The id of the headphone to update.
     * @param headphonesDTO The new details of the headphone.
     * @return The updated headphone.
     */
    @Operation(summary = "Update headphone by id", description = "Update an existing headphone by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Headphone updated successfully."),
            @ApiResponse(responseCode = "404", description = "Headphone not found."),
            @ApiResponse(responseCode = "400", description = "Invalid input provided."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<HeadphonesDTO> updateHeadphone(@PathVariable Integer id,
            @RequestBody HeadphonesDTO headphonesDTO) {
        try {
            HeadphonesDTO updatedHeadphone = headphoneService.updateHeadphone(id, headphonesDTO);
            return new ResponseEntity<>(updatedHeadphone, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(new LinkedMultiValueMap<>(), HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a headphone by its id.
     *
     * @param id The id of the headphone to delete.
     * @return A status message indicating the result.
     */
    @Operation(summary = "Delete headphone by id", description = "Delete a headphone by its id.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Headphone deleted successfully."),
            @ApiResponse(responseCode = "404", description = "Headphone not found."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteHeadphone(@PathVariable Integer id) {

        try {
            headphoneService.deleteHeadphone(id);
            return ResponseEntity.ok("Product successfully deleted with ID: " + id);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the product.");
        }

    }

    /**
     * Filter headphones by ANC (Active Noise Cancellation).
     *
     * @param anc The ANC status to filter by.
     * @return List of headphones with the specified ANC status.
     */
    @Operation(summary = "Get headphones with ANC", description = "Retrieve a list of headphones that have Active Noise Cancellation.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of headphones with ANC retrieved successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/filter/anc/{anc}")
    public ResponseEntity<List<HeadphonesDTO>> getHeadphonesWithANC(@PathVariable NOISE_CANCELING anc) {
        List<HeadphonesDTO> headphones = headphoneService.getHeadphonesWithANC(anc);
        return new ResponseEntity<>(headphones, HttpStatus.OK);
    }

    /**
     * Filter headphones by Bluetooth support.
     *
     * @param bluetooth The Bluetooth status to filter by.
     * @return List of headphones with Bluetooth support.
     */
    @Operation(summary = "Get headphones with Bluetooth", description = "Retrieve a list of headphones that have Bluetooth support.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of headphones with Bluetooth retrieved successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/filter/bluetooth/{bluetooth}")
    public ResponseEntity<List<HeadphonesDTO>> getHeadphonesWithBluetooth(@PathVariable Boolean bluetooth) {
        List<HeadphonesDTO> headphones = headphoneService.getHeadphonesWithBluetooth(bluetooth);
        return new ResponseEntity<>(headphones, HttpStatus.OK);
    }

    /**
     * Filter headphones by wireless support.
     *
     * @param wireless The wireless status to filter by.
     * @return List of wireless headphones.
     */
    @Operation(summary = "Get wireless headphones", description = "Retrieve a list of wireless headphones.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of wireless headphones retrieved successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/filter/wireless/{wireless}")
    public ResponseEntity<List<HeadphonesDTO>> getHeadphonesWireless(@PathVariable Boolean wireless) {
        List<HeadphonesDTO> headphones = headphoneService.getHeadphonesWireless(wireless);
        return new ResponseEntity<>(headphones, HttpStatus.OK);
    }

    /**
     * Filter headphones by warranty period.
     *
     * @param warranty The warranty period to filter by.
     * @return List of headphones with the specified warranty period.
     */
    @Operation(summary = "Get headphones by warranty", description = "Retrieve a list of headphones with a specified warranty period.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of headphones with specified warranty retrieved successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/filter/warranty/{warranty}")
    public ResponseEntity<List<HeadphonesDTO>> getHeadphonesByWarranty(@PathVariable Integer warranty) {
        List<HeadphonesDTO> headphones = headphoneService.getHeadphonesByWarranty(warranty);
        return new ResponseEntity<>(headphones, HttpStatus.OK);
    }

    /**
     * Filter headphones by battery life.
     *
     * @param batteryLife The battery life to filter by.
     * @return List of headphones with the specified battery life.
     */
    @Operation(summary = "Get headphones by battery life", description = "Retrieve a list of headphones with a specified battery life.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of headphones with specified battery life retrieved successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/filter/battery-life/{batteryLife}")
    public ResponseEntity<List<HeadphonesDTO>> getHeadphonesByBatteryLife(@PathVariable Integer batteryLife) {
        List<HeadphonesDTO> headphones = headphoneService.getHeadphonesByBatteryLife(batteryLife);
        return new ResponseEntity<>(headphones, HttpStatus.OK);
    }

    /**
     * Filter headphones by type.
     *
     * @param type The type of headphones to filter by.
     * @return List of headphones of the specified type.
     */
    @Operation(summary = "Get headphones by type", description = "Retrieve a list of headphones by their type.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of headphones of the specified type retrieved successfully."),
            @ApiResponse(responseCode = "500", description = "Internal server error.")
    })
    @GetMapping("/filter/type/{type}")
    public ResponseEntity<List<HeadphonesDTO>> getHeadphonesByType(@PathVariable HEADPHONES_TYPE type) {
        List<HeadphonesDTO> headphones = headphoneService.getHeadphonesByType(type);
        return new ResponseEntity<>(headphones, HttpStatus.OK);
    }
}
