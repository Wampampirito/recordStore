package com.recordstore.controller;

import com.recordstore.dto.SpeakerDTO;
import com.recordstore.service.SpeakerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller class that handles HTTP requests related to the Speaker entity.
 * Provides endpoints for adding, updating, deleting, and retrieving speakers.
 */
@RestController
@RequestMapping("/speaker")
public class SpeakerController {

    private final SpeakerService speakerService;

    @Autowired
    public SpeakerController(SpeakerService speakerService) {
        this.speakerService = speakerService;
    }

    /**
     * Fetch all speakers available in the store.
     *
     * @return a ResponseEntity containing a list of all speakers in DTO format.
     */
    @Operation(summary = "Get all speakers", description = "Retrieve a list of all speakers available in the store.")
    @ApiResponse(responseCode = "200", description = "List of speakers retrieved successfully")
    @GetMapping ("/all")
    public ResponseEntity<List<SpeakerDTO>> getAllSpeakers() {
        List<SpeakerDTO> speakers = speakerService.getAllSpeakers();
        return new ResponseEntity<>(speakers, HttpStatus.OK);
    }

    /**
     * Fetch a speaker by its unique id.
     *
     * @param id the unique identifier (id) of the speaker.
     * @return a ResponseEntity containing the speaker in DTO format if found, or a 404 Not Found if not.
     */
    @Operation(summary = "Get a speaker by id", description = "Retrieve a specific speaker using its id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Speaker found"),
        @ApiResponse(responseCode = "404", description = "Speaker not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<SpeakerDTO> getSpeakerById(@PathVariable Integer id) {
        Optional<SpeakerDTO> speakerDTO = speakerService.getSpeakerById(id);
        return speakerDTO.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Add a new speaker to the store.
     *
     * @param speakerDTO the speaker data to be added in DTO format.
     * @return a ResponseEntity containing the added speaker in DTO format.
     */
    @Operation(summary = "Add a new speaker", description = "Create a new speaker in the store.")
    @ApiResponse(responseCode = "201", description = "Speaker created successfully")
    @PostMapping
    public ResponseEntity<SpeakerDTO> addSpeaker(@RequestBody SpeakerDTO speakerDTO) {
        SpeakerDTO addedSpeaker = speakerService.addSpeaker(speakerDTO);
        return new ResponseEntity<>(addedSpeaker, HttpStatus.CREATED);
    }

    /**
     * Update an existing speaker's information.
     *
     * @param id the id of the speaker to be updated.
     * @param speakerDTO the new speaker data to update in DTO format.
     * @return a ResponseEntity containing the updated speaker in DTO format, or a 404 Not Found if not found.
     */
    @Operation(summary = "Update a speaker", description = "Update the details of an existing speaker using its id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Speaker updated successfully"),
        @ApiResponse(responseCode = "404", description = "Speaker not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<SpeakerDTO> updateSpeaker(@PathVariable Integer id, @RequestBody SpeakerDTO speakerDTO) {
        try {
            SpeakerDTO updatedSpeaker = speakerService.updateSpeaker(id, speakerDTO);
            return new ResponseEntity<>(updatedSpeaker, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Delete a speaker by its unique id.
     *
     * @param id the id of the speaker to delete.
     * @return a ResponseEntity with status OK if the speaker is deleted, or a 404 Not Found if not found.
     */
    @Operation(summary = "Delete a speaker", description = "Remove a speaker from the store using its id.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Speaker deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Speaker not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSpeaker(@PathVariable Integer id) {
        try {
            speakerService.deleteSpeaker(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
