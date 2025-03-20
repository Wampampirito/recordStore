package com.recordstore.model;

import com.recordstore.enums.HEADPHONES_TYPE;
import com.recordstore.enums.NOISE_CANCELING;
import com.recordstore.enums.PRODUCT_CATEGORY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents headphones, a specific type of audio equipment, in the record store. This class extends from 
 * {@link AudioEquipment} and adds additional attributes that are specific to headphones, such as the type 
 * of headphones (e.g., over-ear, in-ear) and the noise cancellation feature.
 * 
 * The headphones are categorized under the {@link PRODUCT_CATEGORY#AE_HEADPHONES} product category.
 * 
 * Example usage:
 * <pre>
 * Headphone headphones = new Headphone("Beats Studio", 299.99, 30, "Beats", "Black", 15, 12, true, true, true, true, true, 
 *     HEADPHONES_TYPE.OVER_EAR, NOISE_CANCELING.YES);
 * </pre>
 * 
 * @see AudioEquipment
 * @see PRODUCT_CATEGORY
 * @see HEADPHONES_TYPE
 * @see NOISE_CANCELING
 */
@Entity
@Table(name = "headphone")
@Data
@EqualsAndHashCode(callSuper=true)
@NoArgsConstructor
public class Headphone extends AudioEquipment {

    /**
     * The type of headphones (e.g., over-ear, in-ear, on-ear).
     * This attribute is represented by the {@link HEADPHONES_TYPE} enum.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "headphones_type")
    private HEADPHONES_TYPE headphoneType;

    /**
     * The noise cancellation feature of the headphones.
     * This attribute indicates whether the headphones support active noise cancellation (ANC).
     * It is represented by the {@link NOISE_CANCELING} enum.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "anc")
    private NOISE_CANCELING anc;

    /**
     * Constructor to create a new Headphone instance with the specified details.
     * 
     * @param name The model of the headphones.
     * @param price The price of the headphones.
     * @param stock The stock quantity of the headphones.
     * @param brand The brand of the headphones.
     * @param color The color of the headphones.
     * @param batteryLife The battery life of the headphones in hours.
     * @param warranty The warranty period of the headphones in months.
     * @param microphoneBuiltIn Indicates if the headphones have a built-in microphone.
     * @param wireless Indicates if the headphones are wireless.
     * @param bluetooth Indicates if the headphones have Bluetooth connectivity.
     * @param usb Indicates if the headphones have a USB port.
     * @param aux Indicates if the headphones have an AUX input.
     * @param headphoneType The type of headphones (e.g., over-ear, in-ear).
     * @param anc The noise cancellation feature of the headphones.
     */
    public Headphone(String name, Double price, Integer stock, String brand, String color, int batteryLife,
                     int warranty, Boolean microphoneBuiltIn, Boolean wireless, Boolean bluetooth, Boolean usb,
                     Boolean aux, HEADPHONES_TYPE headphoneType, NOISE_CANCELING anc) {
        super(name, price, stock, brand, color, batteryLife, warranty, microphoneBuiltIn, wireless, bluetooth,
              usb, aux);
        super.setProductCategory(PRODUCT_CATEGORY.AE_HEADPHONES);
        this.headphoneType = headphoneType;
        this.anc = anc;
    }
}

