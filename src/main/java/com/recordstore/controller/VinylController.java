package com.recordstore.controller;

import com.recordstore.dto.VinylDTO;
import com.recordstore.mapper.VinylMapper;
import com.recordstore.model.Vinyl;
import com.recordstore.service.VinylService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.ALBUM_FORMAT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing vinyls in the record store.
 * Provides endpoints to retrieve, create, update, and delete vinyls.
 * 
 * <p>
 * This controller interacts with the {@link VinylService} and
 * {@link VinylMapper}
 * to manage the business logic and data transformation.
 * </p>
 * 
 * <p>
 * <b>Available endpoints:</b>
 * </p>
 * <ul>
 * <li><b>GET /vinyl/all</b>: Retrieves all vinyls.</li>
 * <li><b>GET /vinyl/{id}</b>: Retrieves a vinyl by its ID.</li>
 * <li><b>GET /vinyl/artist/{artist}</b>: Retrieves vinyls by a specific artist.</li>
 * <li><b>GET /vinyl/genre/{genre}</b>: Retrieves vinyls by a specific genre.</li>
 * <li><b>GET /vinyl/format/{format}</b>: Retrieves vinyls by a specific format.</li>
 * <li><b>GET /vinyl/year-range</b>: Retrieves vinyls within a specified year range.</li>
 * <li><b>GET /vinyl/price-range</b>: Retrieves vinyls within a specified price range.</li>
 * <li><b>GET /vinyl/duration-range</b>: Retrieves vinyls within a specified duration range.</li>
 * <li><b>POST /vinyl/new</b>: Creates a new vinyl.</li>
 * <li><b>PUT /vinyl/update/{id}</b>: Updates an existing vinyl.</li>
 * <li><b>DELETE /vinyl/delete/{id}</b>: Deletes a vinyl by its ID.</li>
 * </ul>
 */
@RestController
@RequestMapping("/vinyl")
public class VinylController {

    private final VinylService vinylService;
    private final VinylMapper vinylMapper;

    @Autowired
    public VinylController(VinylService vinylService, VinylMapper vinylMapper) {
        this.vinylService = vinylService;
        this.vinylMapper = vinylMapper;
    }

    /**
     * Endpoint to get all vinyls.
     *
     * @return List of all vinyls in {@link VinylDTO} format.
     */
    @GetMapping("/all")
    @Operation(summary = "Get all vinyls", description = "Retrieves a list of all vinyls")
    @ApiResponse(responseCode = "200", description = "List of vinyls retrieved successfully")   
    public ResponseEntity<List<VinylDTO>> getAllVinyls() {
        List<VinylDTO> vinyls = vinylService.getAllVinyls();
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to get a vinyl by its ID.
     *
     * @param id The ID of the vinyl to retrieve.
     * @return A {@link VinylDTO} representing the vinyl.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get vinyl by ID", description = "Retrieves a vinyl by its unique ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vinyl found"),
        @ApiResponse(responseCode = "404", description = "Vinyl not found")
    })
    public ResponseEntity<VinylDTO> getVinylById(@PathVariable Integer id) {
        Optional<VinylDTO> vinylDTO = vinylService.getVinylById(id);
        return vinylDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to get vinyls by artist name.
     *
     * @param artist The name of the artist.
     * @return List of vinyls by the specified artist in {@link VinylDTO} format.
     */
    @GetMapping("/artist/{artist}")
    @Operation(summary = "Get vinyls by artist", description = "Returns vinyls filtered by artist name")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vinyls found"),
        @ApiResponse(responseCode = "404", description = "Vinyls not found")
    })
    public ResponseEntity<List<VinylDTO>> getVinylsByArtist(@PathVariable String artist) {
        List<VinylDTO> vinyls = vinylService.getVinylsByArtist(artist);
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to get vinyls within a specific year range.
     *
     * @param startYear The start year of the range.
     * @param endYear   The end year of the range.
     * @return List of vinyls within the specified year range in {@link VinylDTO} format.
     */
    @GetMapping("/year-range")
    @Operation(summary = "Get vinyls by year range", description = "Returns vinyls released within the specified year range")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vinyls found"),
        @ApiResponse(responseCode = "404", description = "Vinyls not found")
    })
    public ResponseEntity<List<VinylDTO>> getVinylsByYearRange(@RequestParam int startYear, @RequestParam int endYear) {
        List<VinylDTO> vinyls = vinylService.getVinylsByYearRange(startYear, endYear);
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to get vinyls by genre.
     *
     * @param genre The genre of the vinyls.
     * @return List of vinyls with the specified genre in {@link VinylDTO} format.
     */
    @GetMapping("/genre/{genre}")
    @Operation(summary = "Get vinyls by genre", description = "Returns vinyls filtered by genre")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vinyls found"),
        @ApiResponse(responseCode = "404", description = "Vinyls not found")
    })
    public ResponseEntity<List<VinylDTO>> getVinylsByGenre(@PathVariable ALBUM_GENRE genre) {
        List<VinylDTO> vinyls = vinylService.getVinylsByGenre(genre);
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to get vinyls by format.
     *
     * @param format The format of the vinyls.
     * @return List of vinyls with the specified format in {@link VinylDTO} format.
     */
    @GetMapping("/format/{format}")
    @Operation(summary = "Get vinyls by format", description = "Returns vinyls filtered by format")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vinyls found"),
        @ApiResponse(responseCode = "404", description = "Vinyls not found")
    })
    public ResponseEntity<List<VinylDTO>> getVinylsByFormat(@PathVariable ALBUM_FORMAT format) {
        List<VinylDTO> vinyls = vinylService.getVinylsByFormat(format);
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to get vinyls within a specific price range.
     *
     * @param minPrice The minimum price of the vinyls.
     * @param maxPrice The maximum price of the vinyls.
     * @return List of vinyls within the price range in {@link VinylDTO} format.
     */
    @GetMapping("/price-range")
    @Operation(summary = "Get vinyls by price range", description = "Returns vinyls within the specified price range")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vinyls found"),
        @ApiResponse(responseCode = "404", description = "Vinyls not found")
    })
    public ResponseEntity<List<VinylDTO>> getVinylsByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        List<VinylDTO> vinyls = vinylService.getVinylsByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to get vinyls within a specific duration range.
     *
     * @param minDuration The minimum duration.
     * @param maxDuration The maximum duration.
     * @return List of vinyls within the specified duration range in {@link VinylDTO} format.
     */
    @GetMapping("/duration-range")
    @Operation(summary = "Get vinyls by duration range", description = "Returns vinyls within the specified duration range")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vinyls found"),
        @ApiResponse(responseCode = "404", description = "Vinyls not found")
    })
    public ResponseEntity<List<VinylDTO>> getVinylsByDuration(@RequestParam String minDuration, @RequestParam String maxDuration) {
        List<VinylDTO> vinyls = vinylService.getVinylsByDuration(minDuration, maxDuration);
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to save a vinyl.
     *
     * @param vinylDTO The vinyl data to be saved.
     * @return The saved vinyl in {@link VinylDTO} format.
     */
    @PostMapping ("/new")
    @Operation(summary = "Add a new vinyl", description = "Create a new vinyl in the store")
    @ApiResponse(responseCode = "201", description = "Vinyl created successfully")
    public ResponseEntity<VinylDTO> saveVinyl(@RequestBody VinylDTO vinylDTO) {
        Vinyl savedVinyl = vinylMapper.toEntity(vinylDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(vinylService.saveVinyl(savedVinyl));
    }

    /**
     * Endpoint to delete a vinyl by its ID.
     *
     * @param id The ID of the vinyl to delete.
     * @return No content response.
     */
    @DeleteMapping("delete/{id}")
    @Operation(summary = "Delete a vinyl", description = "Remove a vinyl from the store using its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Vinyl deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Vinyl not found")
    })
    public ResponseEntity<String> deleteVinyl(@PathVariable Integer id) {
        try {
            vinylService.deleteVinyl(id);
            return ResponseEntity.ok("Product successfully deleted with ID: " + id);
        } catch (IllegalStateException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while deleting the product.");
        }
    }

    /**
     * Endpoint to update a vinyl by its ID.
     *
     * @param id          The ID of the vinyl to update.
     * @param updatedVinylDTO The updated vinyl data.
     * @return The updated vinyl in {@link VinylDTO} format.
     */
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a vinyl", description = "Update the details of an existing vinyl using its ID")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Vinyl updated successfully"),
        @ApiResponse(responseCode = "404", description = "Vinyl not found")
    })
    public ResponseEntity<VinylDTO> updateVinyl(@PathVariable Integer id, @RequestBody VinylDTO updatedVinylDTO) {
        return ResponseEntity.ok( vinylService.updateVinyl(id, vinylMapper.toEntity(updatedVinylDTO)));
    }
}
