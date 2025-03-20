package com.recordstore.model;

import com.recordstore.enums.RESISTANCE;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

import com.recordstore.enums.PORTABLE_TYPE;
import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.PRODUCT_CATEGORY;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a portable player product in the store, which extends the {@link Player} class.
 * A portable player is a device designed to be carried around, often with features such as battery operation,
 * resistance to environmental factors, and different types of portable media (e.g., digital, cassette, or CD players).
 * 
 * This class holds information about the portable player's type, power source, battery life, and resistance to 
 * water, dust, and impacts. It inherits from {@link Player} and includes additional properties specific to portable players.
 * 
 * @see Player
 * @see PORTABLE_TYPE
 * @see POWER_TYPE
 * @see RESISTANCE
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "portable")
@NoArgsConstructor
public class Portable extends Player {

    /**
     * The type of portable player (e.g., Digital, Cassette, CD).
     */
    @Column(name = "portable_type")
    private PORTABLE_TYPE portableType;

    /**
     * The type of power source for the portable player (e.g., Battery, AC, or Batteries).
     */
    @Column(name = "power_type")
    private POWER_TYPE powerType;

    /**
     * The battery life of the portable player, in hours.
     */
    @Column(name = "battery_life")
    private Integer batteryLife; // In hours

    /**
     * The resistance level of the portable player, indicating its ability to withstand water, dust, impacts, etc.
     * This can be a value from the {@link RESISTANCE} enumeration.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "resistance")
    private RESISTANCE resistance;

    /**
     * Constructor for initializing a portable player with its properties.
     * 
     * @param model The model name of the portable player (stored in {@link Product}).
     * @param price The price of the portable player.
     * @param stock The available stock of the portable player.
     * @param brand The brand of the portable player.
     * @param color The color of the portable player.
     * @param description A brief description of the portable player.
     * @param warranty The warranty period of the portable player, in months.
     * @param bluetooth Indicates if the portable player has Bluetooth connectivity.
     * @param usb Indicates if the portable player has a USB port.
     * @param radio Indicates if the portable player has a built-in radio.
     * @param aux Indicates if the portable player has an aux port.
     * @param rca Indicates if the portable player has RCA connections.
     * @param builtInSpeaker Indicates if the portable player has a built-in speaker.
     * @param portableType The type of portable player (e.g., Digital, Cassette, or CD).
     * @param powerType The type of power source (e.g., Battery, AC, or Batteries).
     * @param batteryLife The battery life of the portable player, in hours.
     * @param resistance The resistance level of the portable player to water, dust, and impacts.
     */
    public Portable(String model, Double price, Integer stock, String brand, String color, String description,
                    Integer warranty, Boolean bluetooth, Boolean usb, Boolean radio, Boolean aux, Boolean rca,
                    Boolean builtInSpeaker, PORTABLE_TYPE portableType, POWER_TYPE powerType, Integer batteryLife,
                    RESISTANCE resistance) {
        super(model, price, stock, brand, color, warranty, bluetooth, usb, radio, aux, rca,
              builtInSpeaker);
        super.setProductCategory(PRODUCT_CATEGORY.P_PORTABLE);
        this.portableType = portableType;
        this.powerType = powerType;
        this.batteryLife = batteryLife;
        this.resistance = resistance;
    }
}


