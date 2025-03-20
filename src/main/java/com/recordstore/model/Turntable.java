package com.recordstore.model;

import com.recordstore.enums.VINYL_RPM;
import com.recordstore.enums.TRACTION;
import com.recordstore.enums.MECHANISM;
import com.recordstore.enums.PRODUCT_CATEGORY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


/**
 * Class representing a turntable in the system.
 * <p>
 * This class inherits from {@link Player} and adds specific properties for turntables,
 * such as the presence of built-in preamps, allowed vinyl rotation speeds (RPM), traction type,
 * and the mechanism used to spin the vinyl.
 * </p>
 * <p>
 * A turntable is a device used to play vinyl records. Additional parameters allow customization
 * of the turntable's features, such as Bluetooth connectivity and the type of traction (direct drive or belt drive)
 * used to rotate the platter.
 * </p>
 *
 * @see Player
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "turntable")
public class Turntable extends Player {

    /**
     * Indicates whether the turntable has a built-in preamplifier.
     */
    @Column(name = "preamp")
    private Boolean hasBuiltInPreAmp;

    /**
     * The allowed rotation speeds for the vinyl, in revolutions per minute (RPM).
     */
    @Column(name = "allowed_RPM")
    private VINYL_RPM rpm;

    /**
     * The type of traction used by the turntable (e.g., direct drive or belt drive).
     */
    @Column(name = "traction")
    private TRACTION traction;

    /**
     * The mechanism used by the turntable to spin the vinyl.
     */
    @Column(name = "mechanism")
    private MECHANISM mechanism;

    /**
     * Parameterized constructor to initialize a turntable with its attributes.
     *
     * @param model             The model of the turntable.
     * @param price             The price of the turntable.
     * @param stock             The available stock quantity.
     * @param brand             The brand of the turntable.
     * @param color             The color of the turntable.
     * @param warranty          The warranty of the turntable, in months.
     * @param bluetooth         Indicates if the turntable has Bluetooth.
     * @param usb               Indicates if the turntable has a USB port.
     * @param radio             Indicates if the turntable has a radio.
     * @param aux               Indicates if the turntable has an auxiliary (Aux) input.
     * @param rca               Indicates if the turntable has an RCA port.
     * @param builtInSpeaker    Indicates if the turntable has built-in speakers.
     * @param hasBuiltInPreAmp  Indicates if the turntable has a built-in preamplifier.
     * @param rpm               The allowed rotation speed for the vinyl (RPM).
     * @param traction          The type of traction (e.g., direct drive or belt drive).
     * @param mechanism         The mechanism used by the turntable to spin the vinyl.
     */
    public Turntable(String model, Double price, Integer stock, String brand, String color, Integer warranty,
                     Boolean bluetooth, Boolean usb, Boolean radio, Boolean aux, Boolean rca, Boolean builtInSpeaker,
                     Boolean hasBuiltInPreAmp, VINYL_RPM rpm, TRACTION traction, MECHANISM mechanism) {
        
        super(model, price, stock, brand, color, warranty, bluetooth, usb, radio, aux, rca, builtInSpeaker);
        super.setProductCategory(PRODUCT_CATEGORY.P_TURNTABLE);

        this.hasBuiltInPreAmp = hasBuiltInPreAmp;
        this.rpm = rpm;
        this.traction = traction;
        this.mechanism = mechanism;
    }
}
