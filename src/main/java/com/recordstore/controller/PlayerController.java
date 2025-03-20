package com.recordstore.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.recordstore.dto.PlayerDTO;
import com.recordstore.service.PlayerService;

import java.util.List;

@RestController
@RequestMapping("/player")
@Tag(name = "Player Controller", description = "Manages player-related operations")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    /**
     * Retrieves all players.
     *
     * @return List of all players.
     */
    @GetMapping ("/all")
    @Operation(summary = "Get all players", description = "Retrieves a list of all players")
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
    @GetMapping("/name/{model}")
    @Operation(summary = "Get players by name", description = "Finds players based on their model name")
    public List<PlayerDTO> getPlayersByName(@PathVariable String model) {
        return playerService.findByName(model);
    }

    /**
     * Retrieves players by Bluetooth capability.
     *
     * @param bluetooth True if the player has Bluetooth, false otherwise.
     * @return List of matching players.
     */
    @GetMapping("/bluetooth")
    @Operation(summary = "Get players by Bluetooth capability", description = "Finds players that support Bluetooth connectivity")
    public List<PlayerDTO> getPlayersByBluetooth(@RequestParam Boolean bluetooth) {
        return playerService.findByBluetooth(bluetooth);
    }

    /**
     * Retrieves players by USB support.
     *
     * @param usb True if the player supports USB, false otherwise.
     * @return List of matching players.
     */
    @GetMapping("/usb")
    @Operation(summary = "Get players by USB support", description = "Finds players that have USB functionality")
    public List<PlayerDTO> getPlayersByUsb(@RequestParam Boolean usb) {
        return playerService.findByUsb(usb);
    }

    /**
     * Retrieves players by radio functionality.
     *
     * @param radio True if the player has a radio feature, false otherwise.
     * @return List of matching players.
     */
    @GetMapping("/radio")
    @Operation(summary = "Get players by radio functionality", description = "Finds players that include a radio feature")
    public List<PlayerDTO> getPlayersByRadio(@RequestParam Boolean radio) {
        return playerService.findByRadio(radio);
    }

    /**
     * Retrieves players by AUX input support.
     *
     * @param aux True if the player has an AUX input, false otherwise.
     * @return List of matching players.
     */
    @GetMapping("/aux")
    @Operation(summary = "Get players by AUX input", description = "Finds players that support AUX input")
    public List<PlayerDTO> getPlayersByAux(@RequestParam Boolean aux) {
        return playerService.findByAux(aux);
    }

    /**
     * Deletes a player by its id.
     *
     * @param id The id of the player to be deleted.
     * @return 204 No Content if deleted successfully, 404 Not Found if the player does not exist.
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete player by id", description = "Removes a player from the system using its id")
    public ResponseEntity<Void> deletePlayer(@PathVariable Integer id) {
        if (playerService.getPlayerById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        playerService.deletePlayerById(id);
        return ResponseEntity.noContent().build();
    }
}
