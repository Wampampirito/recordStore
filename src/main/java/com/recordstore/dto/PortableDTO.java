package com.recordstore.dto;

import com.recordstore.enums.PORTABLE_TYPE;
import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.RESISTANCE;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class PortableDTO extends PlayerDTO {


    private PORTABLE_TYPE portableType;
    private POWER_TYPE powerType;
    private int batteryLife; // En horas
    private RESISTANCE resistance;
}