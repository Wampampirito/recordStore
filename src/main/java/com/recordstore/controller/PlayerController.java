package com.recordstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recordstore.dto.PlayerDTO;

import com.recordstore.service.PlayerService;

import java.util.List;

/**
 * REST Controller that manages operations related to music players in the
 * record store.
 * It provides endpoints for retrieving players by various criteria, such as
 * model name, Bluetooth capability, and USB support.
 * 
 * Endpoints:
 * 1. GET /player/all - Retrieves all players
 * 2. GET /player/{id} - Retrieves a player by its id
 * 3. GET /player/model/{model} - Retrieves players by model name
 * 4. GET /player/bluetooth - Retrieves players by Bluetooth capability
 * 5. GET /player/usb - Retrieves players by USB support
 * 6. GET /player/radio - Retrieves players by radio functionality
 * 7. GET /player/aux - Retrieves players by AUX input support
 * 8. POST /player/new - Saves a new player
 * 9. PUT /player/{id} - Updates an existing player
 * 10. DELETE /player/{id} - Deletes a player by its id
 */
@RestController
@RequestMapping("/player")
@Tag(name = "Player Controller", description = "Manages player-related operations")
public class PlayerController {

    private final PlayerService playerService;

    /**
     * Constructor to initialize the PlayerController with the PlayerService.
     * 
     * @param playerService The PlayerService instance to handle business logic
     *                      operations
     */
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Retrieves all players.
     *
     * @return List of all players.
     */
    @GetMapping("/all")
    @Operation(summary = "Get all players", description = "Retrieves a list of all players")
    @ApiResponse(responseCode = "200", description = "List of players retrieved successfully")
    public List<PlayerDTO> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    /**
     * Retrieves a player by its id.
     *
     * @param id The id of the player.
     * @return The player DTO if found, or 404 Not Found if not.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get player by id", description = "Retrieves a player by its unique id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player found"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    public ResponseEntity<PlayerDTO> getPlayerById(@PathVariable Integer id) {
        return playerService.getPlayerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * Retrieves players by name.
     *
     * @param model The model name of the player.
     * @return List of matching players.
     */
    @GetMapping("/model/{model}")
    @Operation(summary = "Get players by model", description = "Finds players based on their model name")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player(s) found"),
            @ApiResponse(responseCode = "404", description = "Player(s) not found")
    })
    public List<PlayerDTO> getPlayersByName(@PathVariable String model) {
        return playerService.findByName(model);
    }

    /**
     * Retrieves players by Bluetooth capability.
     *
     * @param bluetooth True if the player has Bluetooth, false otherwise.
     * @return List of matching players.
     */
    @GetMapping("/bluetooth/{bluetooth}")
    @Operation(summary = "Get players by Bluetooth capability", description = "Finds players that support Bluetooth connectivity")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player(s) found"),
            @ApiResponse(responseCode = "404", description = "Player(s) not found")
    })
    public List<PlayerDTO> getPlayersByBluetooth(@PathVariable Boolean bluetooth) {
        return playerService.findByBluetooth(bluetooth);
    }

    /**
     * Retrieves players by USB support.
     *
     * @param usb True if the player supports USB, false otherwise.
     * @return List of matching players.
     */
    @GetMapping("/usb/{usb}")
    @Operation(summary = "Get players by USB support", description = "Finds players that have USB functionality")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player(s) found"),
            @ApiResponse(responseCode = "404", description = "Player(s) not found")
    })
    public List<PlayerDTO> getPlayersByUsb(@PathVariable Boolean usb) {
        return playerService.findByUsb(usb);
    }

    /**
     * Retrieves players by radio functionality.
     *
     * @param radio True if the player has a radio feature, false otherwise.
     * @return List of matching players.
     */
    @GetMapping("/radio/{radio}")
    @Operation(summary = "Get players by radio functionality", description = "Finds players that include a radio feature")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player(s) found"),
            @ApiResponse(responseCode = "404", description = "Player(s) not found")
    })
    public List<PlayerDTO> getPlayersByRadio(@PathVariable  Boolean radio) {
        return playerService.findByRadio(radio);
    }

    /**
     * Retrieves players by AUX input support.
     *
     * @param aux True if the player has an AUX input, false otherwise.
     * @return List of matching players.
     */
    @GetMapping("/aux/{aux}")
    @Operation(summary = "Get players by AUX input", description = "Finds players that support AUX input")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Player(s) found"),
            @ApiResponse(responseCode = "404", description = "Player(s) not found")
    })
    public List<PlayerDTO> getPlayersByAux(@PathVariable  Boolean aux) {
        return playerService.findByAux(aux);
    }

    /**
     * Saves a new Player object in the database.
     *
     * @param playerDTO The DTO representing the Portable to save.
     */
    @Operation(summary = "Save a new Player")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player saved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping("/new")
    public PlayerDTO savePlayer(@RequestBody PlayerDTO playerDTO) {
        return playerService.savePlayer(playerDTO);
    }


    /**
     * Updates an existing Player in the database.
     *
     * @param id          The ID of the Player to update.
     * @param playerDTO The DTO representing the Player data to update.
     */
    @Operation(summary = "Update an existing Portable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Player updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "Portable not found")
    })
    @PutMapping("/{id}")
    public PlayerDTO updatePortable(@PathVariable Integer id, @RequestBody PlayerDTO playerDTO) {
        return playerService.updatePlayer(id, playerDTO);
    }

    /**
     * Deletes a player by its id.
     *
     * @param id The id of the player to be deleted.
     * @return 204 No Content if deleted successfully, 404 Not Found if the player
     *         does not exist.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete player by id", description = "Removes a player from the system using its id")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Player deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Player not found")
    })
    public ResponseEntity<String> deletePlayer(@PathVariable Integer id) {
        try {
            playerService.deletePlayer(id);
            return ResponseEntity.ok("Product successfully deleted with ID: " + id);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the product.");
        }

    }
}
