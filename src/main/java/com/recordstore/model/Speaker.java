package com.recordstore.model;

import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.PRODUCT_CATEGORY;
import com.recordstore.enums.RESISTANCE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Class representing a speaker in the system.
 * <p>
 * This class inherits from {@link AudioEquipment} and adds specific properties for speakers,
 * such as radio, power, impedance, minimum and maximum frequency, weight, power type, and resistance.
 * </p>
 * <p>
 * A speaker can be wireless, have a built-in microphone, and be compatible with technologies such as
 * Bluetooth and USB. Additionally, its power is measured in watts and it has specific characteristics related to 
 * sound quality and resistance.
 * </p>
 *
 * @see AudioEquipment
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "speaker")
@NoArgsConstructor
public class Speaker extends AudioEquipment {

    /**
     * Indicates if the speaker has a built-in radio.
     */
    @Column(name = "radio")
    private Boolean radio;

    /**
     * Power of the speaker in watts (W).
     */
    @Column(name = "power")
    private Integer power;

    /**
     * Impedance of the speaker in ohms (Ω).
     */
    @Column(name = "impedance")
    private Integer impedance;

    /**
     * Minimum frequency of the speaker in hertz (Hz).
     */
    @Column(name = "min_freq")
    private Integer minFrequency;

    /**
     * Maximum frequency of the speaker in hertz (Hz).
     */
    @Column(name = "max_freq")
    private Integer maxFrequency;

    /**
     * Weight of the speaker in grams (g).
     */
    @Column(name = "weight")
    private Integer weight;

    /**
     * Power source type of the speaker (e.g., battery, AC).
     */
    @Column(name = "power_type")
    @Enumerated(EnumType.STRING)
    private POWER_TYPE powerType;

    /**
     * Resistance of the speaker (e.g., resistance to water, dust, or impacts).
     */
    @Column(name = "resistance")
    @Enumerated(EnumType.STRING)
    private RESISTANCE resistance;

    /**
     * Constructor to initialize a speaker with its attributes.
     *
     * @param model           The model of the speaker.
     * @param price           The price of the speaker.
     * @param stock           The available stock of the speaker.
     * @param brand           The brand of the speaker.
     * @param color           The color of the speaker.
     * @param batteryLife     The battery life of the speaker in hours.
     * @param warranty        The warranty period of the speaker in months.
     * @param microphoneBuiltIn Indicates if the speaker has a built-in microphone.
     * @param wireless        Indicates if the speaker is wireless.
     * @param bluetooth       Indicates if the speaker has Bluetooth.
     * @param usb             Indicates if the speaker has a USB port.
     * @param aux             Indicates if the speaker has an aux port.
     * @param radio           Indicates if the speaker has a built-in radio.
     * @param power           The power of the speaker in watts (W).
     * @param impedance       The impedance of the speaker in ohms (Ω).
     * @param minFrequency    The minimum frequency of the speaker in hertz (Hz).
     * @param maxFrequency    The maximum frequency of the speaker in hertz (Hz).
     * @param weight          The weight of the speaker in grams (g).
     * @param powerType       The power type of the speaker.
     * @param resistance      The resistance of the speaker.
     */
    public Speaker(String model, Double price, Integer stock, String brand, String color, Integer batteryLife,
                   Integer warranty, Boolean microphoneBuiltIn, Boolean wireless, Boolean bluetooth, Boolean usb,
                   Boolean aux, Boolean radio, Integer power, Integer impedance, Integer minFrequency, Integer maxFrequency,
                   Integer weight, POWER_TYPE powerType, RESISTANCE resistance) {
        super(model, price, stock, brand, color, batteryLife, warranty, microphoneBuiltIn, wireless, bluetooth,
                usb, aux);
        super.setProductCategory(PRODUCT_CATEGORY.AE_SPEAKER);
        this.radio = radio;
        this.power = power;
        this.impedance = impedance;
        this.minFrequency = minFrequency;
        this.maxFrequency = maxFrequency;
        this.weight = weight;
        this.powerType = powerType;
        this.resistance = resistance;
    }
}
