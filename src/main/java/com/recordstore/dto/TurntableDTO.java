package com.recordstore.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class TurntableDTO extends PlayerDTO {
    private String brand;
    private String model;
    private Double price;
    private Boolean isDirectDrive;

}