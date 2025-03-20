package com.recordstore.model;

import com.recordstore.enums.PRODUCT_CATEGORY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a general audio equipment product in the record store. This class serves as an abstract base 
 * for different types of audio equipment, such as headphones, speakers, etc. It contains attributes that are 
 * common to all audio equipment, such as brand, color, battery life, warranty, and connectivity options.
 * 
 * This class extends from {@link Product}, inheriting its properties like model, price, and stock, and adds specific 
 * attributes that define various features of audio equipment, such as microphone availability, wireless connectivity, 
 * Bluetooth, USB, and AUX support. It serves as a foundation for more specific audio equipment classes.
 * 
 * The audio equipment products are categorized under the {@link com.recordstore.enums.PRODUCT_CATEGORY#AUDIO_EQUIPMENT} 
 * category.
 * 
 * Example usage:
 * <pre>
 * AudioEquipment headphones = new AudioEquipment("Beats", 199.99, 50, "Beats", "Black", 20, 12, true, true, true, true, true);
 * </pre>
 * 
 * @see Product
 * @see PRODUCT_CATEGORY
 * @see com.recordstore.model.Headphone
 * @see com.recordstore.model.Speaker
 */
@Entity
@Table(name = "audio_equipment")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public abstract class AudioEquipment extends Product {

    /**
     * The brand of the audio equipment.
     * This field specifies the manufacturer or brand name of the audio equipment.
     */
    @Column(name = "brand")
    private String brand;

    /**
     * The color of the audio equipment.
     * This field specifies the color of the audio equipment, which can be used for filtering or display purposes.
     */
    @Column(name = "color")
    private String color;

    /**
     * The battery life of the audio equipment in hours.
     * This field specifies the duration (in hours) that the audio equipment can function on a full charge.
     */
    @Column(name = "battery_life")
    private Integer batteryLife;

    /**
     * The warranty period for the audio equipment in months.
     * This field specifies the warranty period offered for the audio equipment, in months.
     */
    @Column(name = "warranty")
    private Integer warranty;

    /**
     * Indicates whether the audio equipment has a built-in microphone.
     * This field is true if the equipment includes a microphone, and false otherwise.
     */
    @Column(name = "microphone_built_in")
    private Boolean microphoneBuiltIn;

    /**
     * Indicates whether the audio equipment is wireless.
     * This field is true if the equipment operates without the need for a physical connection (e.g., Bluetooth), and false otherwise.
     */
    @Column(name = "wireless")
    private Boolean wireless;

    /**
     * Indicates whether the audio equipment has Bluetooth connectivity.
     * This field is true if the equipment supports Bluetooth connectivity, and false otherwise.
     */
    @Column(name = "bluetooth")
    private Boolean bluetooth;

    /**
     * Indicates whether the audio equipment has a USB port.
     * This field is true if the equipment is equipped with a USB port for connectivity, and false otherwise.
     */
    @Column(name = "usb")
    private Boolean usb;

    /**
     * Indicates whether the audio equipment has an AUX input.
     * This field is true if the equipment supports an AUX input for wired connections, and false otherwise.
     */
    @Column(name = "aux")
    private Boolean aux;

    /**
     * Constructs a new AudioEquipment instance with the specified details.
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
