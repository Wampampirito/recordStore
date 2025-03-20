package com.recordstore.controller;

import com.recordstore.dto.AlbumDTO;
import com.recordstore.model.Album;
import com.recordstore.service.AlbumService;
import com.recordstore.mapper.AlbumMapper;
import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


/**
 * REST controller for managing albums in the record store.
 * Provides endpoints to retrieve, create, update, and delete albums.
 * 
 * <p>This controller interacts with the {@link AlbumService} and {@link AlbumMapper}
 * to manage the business logic and data transformation.</p>
 * 
 * <p><b>Available endpoints:</b></p>
 * <ul>
 *   <li><b>GET /albums</b>: Retrieves all albums.</li>
 *   <li><b>GET /albums/{id}</b>: Retrieves an album by its ID.</li>
 *   <li><b>GET /albums/artist/{artist}</b>: Retrieves albums by a specific artist.</li>
 *   <li><b>GET /albums/genre/{genre}</b>: Retrieves albums by a specific genre.</li>
 *   <li><b>GET /albums/format/{format}</b>: Retrieves albums by a specific format.</li>
 *   <li><b>GET /albums/year-range</b>: Retrieves albums within a specified year range.</li>
 *   <li><b>POST /albums/new</b>: Creates a new album.</li>
 *   <li><b>PUT /albums/update/{id}</b>: Updates an existing album.</li>
 *   <li><b>DELETE /albums/delete/{id}</b>: Deletes an album by its ID.</li>
 * </ul>
 */
@RestController
@RequestMapping("/albums")
@Tag(name = "Albums", description = "Endpoints for managing album records")
public class AlbumController {

    private final AlbumService albumService;
    private final AlbumMapper albumMapper;

    /**
     * Constructor to initialize AlbumController with dependencies.
     *
     * @param albumService the service handling album operations
     * @param albumMapper  the mapper for converting between Album and AlbumDTO
     */
    @Autowired
    public AlbumController(AlbumService albumService, AlbumMapper albumMapper) {
        this.albumService = albumService;
        this.albumMapper = albumMapper;
    }

    /**
     * Retrieves a list of all albums.
     *
     * @return a list of {@link AlbumDTO} objects
     */
    @Operation(summary = "Retrieve all albums", description = "Returns a list of all albums.")
    @ApiResponse(responseCode = "200", description = "List of albums retrieved successfully")
    @GetMapping
    public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
        List<AlbumDTO> albums = albumService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    /**
     * Retrieves an album by its unique ID.
     *
     * @param id the unique identifier of the album
     * @return the requested {@link AlbumDTO} or 404 if not found
     */
    @Operation(summary = "Retrieve an album by ID", description = "Returns an album based on its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Album found"),
        @ApiResponse(responseCode = "404", description = "Album not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Integer id) {
        Optional<Album> album = albumService.getAlbumById(id);
        return album.map(value -> ResponseEntity.ok(albumMapper.toDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves albums by a specific artist.
     *
     * @param artist the name of the artist
     * @return a list of albums by the given artist
     */
    @Operation(summary = "Retrieve albums by artist", description = "Returns albums filtered by artist name.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Albums found"),
        @ApiResponse(responseCode = "404", description = "Albums not found")
    })
    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByArtist(@PathVariable String artist) {
        List<AlbumDTO> albums = albumService.getAlbumsByArtist(artist);
        return ResponseEntity.ok(albums);
    }

    /**
     * Retrieves albums by a specific genre.
     *
     * @param genre the genre of the albums
     * @return a list of albums within the given genre
     */
    @Operation(summary = "Retrieve albums by genre", description = "Returns albums filtered by genre.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Albums found"),
        @ApiResponse(responseCode = "404", description = "Albums not found")
    })
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByGenre(@PathVariable ALBUM_GENRE genre) {
        List<AlbumDTO> albums = albumService.getAlbumsByGenre(genre);
        return ResponseEntity.ok(albums);
    }

    /**
     * Retrieves albums by a specific format.
     *
     * @param format the format of the albums (e.g., CD, Vinyl)
     * @return a list of albums within the given format
     */
    @Operation(summary = "Retrieve albums by format", description = "Returns albums filtered by format (CD, Vinyl, etc.).")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Albums found"),
        @ApiResponse(responseCode = "404", description = "Albums not found")
    })
    @GetMapping("/format/{format}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByFormat(@PathVariable ALBUM_FORMAT format) {
        List<AlbumDTO> albums = albumService.getAlbumsByFormat(format);
        return ResponseEntity.ok(albums);
    }

    /**
     * Retrieves albums released within a specific year range.
     *
     * @param startYear the start of the year range
     * @param endYear   the end of the year range
     * @return a list of albums released within the given range
     */
    @Operation(summary = "Retrieve albums by year range", description = "Returns albums released within the specified year range.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Albums found"),
        @ApiResponse(responseCode = "404", description = "Albums not found")
    })
    @GetMapping("/year-range")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByYearRange(
            @RequestParam int startYear,
            @RequestParam int endYear) {
        List<AlbumDTO> albums = albumService.getAlbumsByYearRange(startYear, endYear);
        return ResponseEntity.ok(albums);
    }

    /**
     * Creates a new album.
     *
     * @param albumDTO the album data to be created
     * @return the created {@link AlbumDTO}
     */
    @Operation(summary = "Create a new album", description = "Creates and returns a new album.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Album created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid album data")
    })
    @PostMapping("/new")
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        Album album = albumMapper.toEntity(albumDTO);
        AlbumDTO savedAlbum = albumService.saveAlbum(album);
        return ResponseEntity.ok(savedAlbum);
    }

    /**
     * Updates an existing album.
     *
     * @param id       the ID of the album to update
     * @param albumDTO the new album data
     * @return the updated {@link AlbumDTO}
     */
    @Operation(summary = "Update an album", description = "Updates an existing album based on its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Album updated successfully"),
        @ApiResponse(responseCode = "404", description = "Album not found")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Integer id, @RequestBody AlbumDTO albumDTO) {
        AlbumDTO updatedAlbum = albumService.updateAlbum(id, albumMapper.toEntity(albumDTO));
        return ResponseEntity.ok(updatedAlbum);
    }

    /**
     * Deletes an album by its ID.
     *
     * @param id the ID of the album to delete
     * @return a response entity with no content
     */
    @Operation(summary = "Delete an album", description = "Deletes an album based on its ID.")
    @ApiResponses({
        @ApiResponse(responseCode = "204", description = "Album deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Album not found")
    })
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }
}

