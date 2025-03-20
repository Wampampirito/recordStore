package com.recordstore.controller;

import com.recordstore.dto.VinylDTO;
import com.recordstore.mapper.VinylMapper;
import com.recordstore.model.Vinyl;
import com.recordstore.service.VinylService;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.ALBUM_FORMAT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<VinylDTO>> getAllVinyls() {
        List<VinylDTO> vinyls = vinylService.getAllVinyls().stream()
                .map(vinylMapper::toVinylDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to get a vinyl by its ID.
     *
     * @param id The ID of the vinyl to retrieve.
     * @return A {@link VinylDTO} representing the vinyl.
     */
    @GetMapping("/{id}")
    public ResponseEntity<VinylDTO> getVinylById(@PathVariable Double id) {
        Optional<VinylDTO> vinylDTO = vinylService.getVinylById(id).map(vinylMapper::toVinylDTO);
        return vinylDTO.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to get vinyls by artist name.
     *
     * @param artist The name of the artist.
     * @return List of vinyls by the specified artist in {@link VinylDTO} format.
     */
    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<VinylDTO>> getVinylsByArtist(@PathVariable String artist) {
        List<VinylDTO> vinyls = vinylService.getVinylsByArtist(artist).stream()
                .map(vinylMapper::toVinylDTO)
                .collect(Collectors.toList());
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
    public ResponseEntity<List<VinylDTO>> getVinylsByYearRange(@RequestParam int startYear, @RequestParam int endYear) {
        List<VinylDTO> vinyls = vinylService.getVinylsByYearRange(startYear, endYear).stream()
                .map(vinylMapper::toVinylDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to get vinyls by genre.
     *
     * @param genre The genre of the vinyls.
     * @return List of vinyls with the specified genre in {@link VinylDTO} format.
     */
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<VinylDTO>> getVinylsByGenre(@PathVariable ALBUM_GENRE genre) {
        List<VinylDTO> vinyls = vinylService.getVinylsByGenre(genre).stream()
                .map(vinylMapper::toVinylDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to get vinyls by format.
     *
     * @param format The format of the vinyls.
     * @return List of vinyls with the specified format in {@link VinylDTO} format.
     */
    @GetMapping("/format/{format}")
    public ResponseEntity<List<VinylDTO>> getVinylsByFormat(@PathVariable ALBUM_FORMAT format) {
        List<VinylDTO> vinyls = vinylService.getVinylsByFormat(format).stream()
                .map(vinylMapper::toVinylDTO)
                .collect(Collectors.toList());
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
    public ResponseEntity<List<VinylDTO>> getVinylsByPriceRange(@RequestParam Double minPrice, @RequestParam Double maxPrice) {
        List<VinylDTO> vinyls = vinylService.getVinylsByPriceRange(minPrice, maxPrice).stream()
                .map(vinylMapper::toVinylDTO)
                .collect(Collectors.toList());
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
    public ResponseEntity<List<VinylDTO>> getVinylsByDuration(@RequestParam String minDuration, @RequestParam String maxDuration) {
        List<VinylDTO> vinyls = vinylService.getVinylsByDuration(minDuration, maxDuration).stream()
                .map(vinylMapper::toVinylDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(vinyls);
    }

    /**
     * Endpoint to save a vinyl.
     *
     * @param vinylDTO The vinyl data to be saved.
     * @return The saved vinyl in {@link VinylDTO} format.
     */
    @PostMapping
    public ResponseEntity<VinylDTO> saveVinyl(@RequestBody VinylDTO vinylDTO) {
        Vinyl savedVinyl = vinylService.saveVinyl(vinylMapper.toEntity(vinylDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(vinylMapper.toVinylDTO(savedVinyl));
    }

    /**
     * Endpoint to delete a vinyl by its ID.
     *
     * @param id The ID of the vinyl to delete.
     * @return No content response.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVinyl(@PathVariable Integer id) {
        vinylService.deleteVinyl(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Endpoint to update a vinyl by its ID.
     *
     * @param id          The ID of the vinyl to update.
     * @param updatedVinylDTO The updated vinyl data.
     * @return The updated vinyl in {@link VinylDTO} format.
     */
    @PutMapping("/{id}")
    public ResponseEntity<VinylDTO> updateVinyl(@PathVariable Double id, @RequestBody VinylDTO updatedVinylDTO) {
        Vinyl updatedVinyl = vinylService.updateVinyl(id, vinylMapper.toEntity(updatedVinylDTO));
        return ResponseEntity.ok(vinylMapper.toVinylDTO(updatedVinyl));
    }
}
