package com.recordstore.model;

import com.recordstore.enums.PRODUCT_CATEGORY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents audio equipment products in the record store. This class is an abstract base class for different types of 
 * audio equipment, such as headphones, speakers, etc., and contains attributes common to all audio equipment.
 * 
 * This class extends the {@link Product} class, inheriting its properties such as model, price, and stock. In addition, 
 * it has specific attributes like brand, color, battery life, warranty, microphone availability, and connectivity options 
 * such as wireless, Bluetooth, USB, and AUX.
 * 
 * The audio equipment is categorized under the {@link com.recordstore.enums.PRODUCT_CATEGORY#AUDIO_EQUIPMENT} product category.
 * 
 * Example usage:
 * <pre>
 * AudioEquipment headphones = new AudioEquipment("Beats", 199.99, 50, "Beats", "Black", 20, 12, true, true, true, true, true);
 * </pre>
 * 
 * @see Product
 * @see PRODUCT_CATEGORY
 */
@Entity
@Table(name = "audio_equipment")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public abstract class AudioEquipment extends Product {

    /**
     * The brand of the audio equipment.
     */
    @Column(name = "brand")
    private String brand;

    /**
     * The color of the audio equipment.
     */
    @Column(name = "color")
    private String color;

    /**
     * The battery life of the audio equipment in hours.
     */
    @Column(name = "battery_life")
    private Integer batteryLife;

    /**
     * The warranty period for the audio equipment in months.
     */
    @Column(name = "warranty")
    private Integer warranty;

    /**
     * Indicates whether the audio equipment has a built-in microphone.
     */
    @Column(name = "microphone_built_in")
    private Boolean microphoneBuiltIn;

    /**
     * Indicates whether the audio equipment is wireless.
     */
    @Column(name = "wireless")
    private Boolean  wireless;

    /**
     * Indicates whether the audio equipment has Bluetooth connectivity.
     */
    @Column(name = "bluetooth")
    private Boolean bluetooth;

    /**
     * Indicates whether the audio equipment has a USB port.
     */
    @Column(name = "usb")
    private Boolean usb;

    /**
     * Indicates whether the audio equipment has an AUX input.
     */
    @Column(name = "aux")
    private Boolean aux;

    /**
     * Constructor to create a new AudioEquipment instance with specified details.
     * 
     * @param model The model of the audio equipment.
     * @param price The price of the audio equipment.
     * @param stock The stock quantity of the audio equipment.
     * @param brand The brand of the audio equipment.
     * @param color The color of the audio equipment.
     * @param batteryLife The battery life of the audio equipment in hours.
     * @param warranty The warranty period of the audio equipment in months.
     * @param microphoneBuiltIn Indicates if the audio equipment has a built-in microphone.
     * @param wireless Indicates if the audio equipment is wireless.
     * @param bluetooth Indicates if the audio equipment has Bluetooth connectivity.
     * @param usb Indicates if the audio equipment has a USB port.
     * @param aux Indicates if the audio equipment has an AUX input.
     */
    public AudioEquipment(String model, Double price, Integer stock, String brand, String color, Integer batteryLife,
    Integer warranty, Boolean microphoneBuiltIn, Boolean wireless, Boolean bluetooth, Boolean usb, Boolean aux) {
        super(model, price, stock);
        super.setProductCategory(PRODUCT_CATEGORY.AUDIO_EQUIPMENT);
        this.brand = brand;
        this.color = color;
        this.batteryLife = batteryLife;
        this.warranty = warranty;
        this.microphoneBuiltIn = microphoneBuiltIn;
        this.wireless = wireless;
        this.bluetooth = bluetooth;
        this.usb = usb;
        this.aux = aux;
    }
}
