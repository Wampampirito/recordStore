package com.recordstore.controller;

import com.recordstore.dto.AlbumDTO;
import com.recordstore.model.Album;
import com.recordstore.service.AlbumService;
import com.recordstore.mapper.AlbumMapper;
import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.PRODUCT_CATEGORY;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/albums")
@Tag(name = "Album Controller", description = "Manage album records")
public class AlbumController {

    private final AlbumService albumService;
        private final AlbumMapper albumMapper;
    
        @Autowired
        public AlbumController(AlbumService albumService, AlbumMapper albumMapper) {
            this.albumService = albumService;
            this.albumMapper = albumMapper;
        }
    
        @Operation(summary = "Get all albums", description = "Retrieve a list of all albums available in the system")
        @GetMapping
        public ResponseEntity<List<AlbumDTO>> getAllAlbums() {
            List<AlbumDTO> albums = albumService.getAllAlbums();
            return ResponseEntity.ok(albums);
        }
    
        @Operation(summary = "Get album by ID", description = "Retrieve a specific album by its unique ID")
        @GetMapping("/{id}")
        public ResponseEntity<AlbumDTO> getAlbumById(@PathVariable Integer id) {
            Optional<Album> album = albumService.getAlbumById(id);
            return album.map(value -> ResponseEntity.ok(albumMapper.toDTO(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get albums by artist", description = "Retrieve a list of albums by a specific artist")
    @GetMapping("/artist/{artist}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByArtist(@PathVariable String artist) {
        List<AlbumDTO> albums = albumService.getAlbumsByArtist(artist);
        return ResponseEntity.ok(albums);
    }

    @Operation(summary = "Get albums by genre", description = "Retrieve a list of albums by a specific genre")
    @GetMapping("/genre/{genre}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByGenre(@PathVariable ALBUM_GENRE genre) {
        List<AlbumDTO> albums = albumService.getAlbumsByGenre(genre);
        return ResponseEntity.ok(albums);
    }

    @Operation(summary = "Get albums by format", description = "Retrieve a list of albums by a specific format")
    @GetMapping("/format/{format}")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByFormat(@PathVariable ALBUM_FORMAT format) {
        List<AlbumDTO> albums = albumService.getAlbumsByFormat(format);
        return ResponseEntity.ok(albums);
    }

    @Operation(summary = "Get albums by year range", description = "Retrieve a list of albums released within a specific year range")
    @GetMapping("/year-range")
    public ResponseEntity<List<AlbumDTO>> getAlbumsByYearRange(@RequestParam int startYear, @RequestParam int endYear) {
        List<AlbumDTO> albums = albumService.getAlbumsByYearRange(startYear, endYear);
        return ResponseEntity.ok(albums);
    }

    @Operation(summary = "Create a new album", description = "Add a new album to the system")
    @PostMapping ("/new")
    public ResponseEntity<AlbumDTO> createAlbum(@RequestBody AlbumDTO albumDTO) {
        Album album = AlbumMapper.toEntity(albumDTO);
        AlbumDTO savedAlbum = albumService.saveAlbum(album);
        return ResponseEntity.ok(savedAlbum);
    }

    @Operation(summary = "Update an existing album", description = "Modify an album's details using its ID")
    @PutMapping("/update/{id}")
    public ResponseEntity<AlbumDTO> updateAlbum(@PathVariable Integer id, @RequestBody AlbumDTO albumDTO) {
        AlbumDTO updatedAlbum = albumService.updateAlbum(id, AlbumMapper.toEntity(albumDTO));
        return ResponseEntity.ok(updatedAlbum);
    }

    @Operation(summary = "Delete an album", description = "Remove an album from the system by its ID")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteAlbum(@PathVariable Integer id) {
        albumService.deleteAlbum(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get albums by price range", description = "Retrieve albums within a specified price range")
@GetMapping("/price-range/{minPrice}/{maxPrice}")
public ResponseEntity<List<AlbumDTO>> getAlbumsByPriceRange(@PathVariable Double minPrice, @PathVariable Double maxPrice) {
    List<AlbumDTO> albums = albumService.getAlbumsByPriceRange(minPrice, maxPrice);
    return ResponseEntity.ok(albums);
}

@Operation(summary = "Get albums by duration range", description = "Retrieve albums within a specified duration range")
@GetMapping("/duration-range/{minDuration}/{maxDuration}")
public ResponseEntity<List<AlbumDTO>> getAlbumsByDuration(@PathVariable String minDuration, @PathVariable String maxDuration) {
    List<AlbumDTO> albums = albumService.getAlbumsByDuration(minDuration, maxDuration);
    return ResponseEntity.ok(albums);
}

@Operation(summary = "Get albums in stock", description = "Retrieve albums that have stock available")
@GetMapping("/in-stock")
public ResponseEntity<List<AlbumDTO>> getAlbumsInStock() {
    List<AlbumDTO> albums = albumService.getAlbumsInStock();
    return ResponseEntity.ok(albums);
}
@Operation(summary = "Get albums by category", description = "Retrieve a list of albums by a specific product category")
@GetMapping("/category/{category}")
public ResponseEntity<List<AlbumDTO>> getAlbumsByCategory(@PathVariable PRODUCT_CATEGORY category) {
    List<AlbumDTO> albums = albumService.getAlbumsByCategory(category);
    return ResponseEntity.ok(albums);
}

@Operation(summary = "Count albums by artist", description = "Retrieve the number of albums by a specific artist")
@GetMapping("/count-by-artist/{artist}")
public ResponseEntity<Integer> countAlbumsByArtist(@PathVariable String artist) {
    Integer count = albumService.countAlbumsByArtist(artist);
    return ResponseEntity.ok(count);
}

@Operation(summary = "Count albums by genre", description = "Retrieve the number of albums by a specific genre")
@GetMapping("/count-by-genre/{genre}")
public ResponseEntity<Integer> countAlbumsByGenre(@PathVariable ALBUM_GENRE genre) {
    Integer count = albumService.countAlbumsByGenre(genre);
    return ResponseEntity.ok(count);
}

@Operation(summary = "Count albums by format", description = "Retrieve the number of albums by a specific format")
@GetMapping("/count-by-format/{format}")
public ResponseEntity<Integer> countAlbumsByFormat(@PathVariable ALBUM_FORMAT format) {
    Integer count = albumService.countAlbumsByFormat(format);
    return ResponseEntity.ok(count);
}

@Operation(summary = "Find albums by name", description = "Retrieve a list of albums by a partial name search")
@GetMapping("/search-by-name/{name}")
public ResponseEntity<List<AlbumDTO>> findAlbumsByName(@PathVariable String name) {
    List<AlbumDTO> albums = albumService.findAlbumsByName(name);
    return ResponseEntity.ok(albums);
}

}
