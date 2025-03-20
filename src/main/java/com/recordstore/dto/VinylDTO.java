package com.recordstore.dto;
import com.recordstore.enums.VINYL_RPM;
import com.recordstore.enums.VINYL_SIZE;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class VinylDTO extends AlbumDTO {
        private VINYL_SIZE size;
        private VINYL_RPM rpm;
        private String color;
}