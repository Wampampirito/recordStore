package com.recordstore.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class PlayerDTO  extends ProductDTO {

    private String brand;
    private String color;
    private Integer warranty;
    private Boolean bluetooth;
    private Boolean usb;
    private Boolean radio;
    private Boolean aux;
    private Boolean rca;
    private Boolean builtInSpeaker;

}