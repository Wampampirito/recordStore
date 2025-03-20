package com.recordstore.dto;


import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class AlbumDTO extends ProductDTO {
    private String artist;
    private int year;
    private ALBUM_FORMAT format;
    private ALBUM_GENRE genre;
    private String duration;

}
