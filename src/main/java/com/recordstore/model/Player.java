package com.recordstore.model;

import com.recordstore.enums.PRODUCT_CATEGORY;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Represents a player product in the store, which extends the {@link Product} class.
 * The player is a device that can play music and may have various features such as Bluetooth, USB, radio, and others.
 * 
 * This class holds information about the player’s brand, color, warranty, and its connectivity options such as Bluetooth,
 * USB, aux, radio, RCA, and built-in speakers. It inherits from {@link Product}, and the model name is stored in the 
 * {@link Product} class.
 * 
 * @see Product
 * @see PRODUCT_CATEGORY
 */
@Data
@EqualsAndHashCode(callSuper=true)
@Entity
@Table(name = "player")
@NoArgsConstructor
public class Player extends Product {

    /**
     * The brand of the player.
     */
    @Column(name = "brand")
    private String brand;

    /**
     * The color of the player.
     */
    @Column(name = "color")
    private String color;

    /**
     * The warranty period of the player, in months.
     */
    @Column(name = "warranty")
    private Integer warranty; // In months

    /**
     * Indicates if the player has Bluetooth connectivity.
     */
    @Column(name = "bluetooth")
    private Boolean bluetooth;

    /**
     * Indicates if the player has a USB port.
     */
    @Column(name = "usb")
    private Boolean usb;

    /**
     * Indicates if the player has a built-in radio.
     */
    @Column(name = "radio")
    private Boolean radio;

    /**
     * Indicates if the player has an aux port.
     */
    @Column(name = "aux")
    private Boolean aux;

    /**
     * Indicates if the player has RCA connections.
     */
    @Column(name = "rca")
    private Boolean rca;

    /**
     * Indicates if the player has a built-in speaker.
     */
    @Column(name = "built_in_speaker")
    private Boolean builtInSpeaker;

    /**
     * Constructor for initializing the player with its properties.
     * 
     * @param model The model name of the player (stored in {@link Product}).
     * @param price The price of the player.
     * @param stock The available stock of the player.
     * @param brand The brand of the player.
     * @param color The color of the player.
     * @param warranty The warranty period of the player (in months).
     * @param bluetooth Indicates if the player has Bluetooth connectivity.
     * @param usb Indicates if the player has a USB port.
     * @param radio Indicates if the player has a built-in radio.
     * @param aux Indicates if the player has an aux port.
     * @param rca Indicates if the player has RCA connections.
     * @param builtInSpeaker Indicates if the player has a built-in speaker.
     */
    public Player(String model, Double price, Integer stock, String brand, String color, Integer warranty, Boolean bluetooth, Boolean usb, Boolean radio, Boolean aux, Boolean rca, Boolean builtInSpeaker) {
        super(model, price, stock); // Model is stored in the parent class Product
        super.setProductCategory(PRODUCT_CATEGORY.PLAYER);
        this.brand = brand;
        this.color = color;
        this.warranty = warranty;
        this.bluetooth = bluetooth;
        this.usb = usb;
        this.radio = radio;
        this.aux = aux;
        this.rca = rca;
        this.builtInSpeaker = builtInSpeaker;
    }
}

