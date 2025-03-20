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
 * Clase que representa un altavoz (Speaker) en el sistema.
 * <p>
 * Esta clase hereda de {@link AudioEquipment} y agrega propiedades específicas para los altavoces,
 * como la radio, potencia, impedancia, frecuencia mínima y máxima, peso, tipo de alimentación y resistencia.
 * </p>
 * <p>
 * Un altavoz puede ser inalámbrico, tener un micrófono incorporado y ser compatible con tecnologías como
 * Bluetooth y USB. Además, su potencia se mide en vatios y tiene características específicas relacionadas con la 
 * calidad de sonido y su resistencia.
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
     * Indica si el altavoz tiene radio incorporado.
     */
    @Column(name = "radio")
    private Boolean radio;

    /**
     * Potencia del altavoz en vatios (W).
     */
    @Column(name = "power")
    private Integer power;

    /**
     * Impedancia del altavoz en ohmios (Ω).
     */
    @Column(name = "impedance")
    private Integer impedance;

    /**
     * Frecuencia mínima del altavoz en hercios (Hz).
     */
    @Column(name = "min_freq")
    private Integer minFrequency;

    /**
     * Frecuencia máxima del altavoz en hercios (Hz).
     */
    @Column(name = "max_freq")
    private Integer maxFrequency;

    /**
     * Peso del altavoz en gramos (g).
     */
    @Column(name = "weight")
    private Integer weight;

    /**
     * Tipo de alimentación del altavoz (por ejemplo, batería, corriente alterna).
     */
    @Column(name = "power_type")
    @Enumerated(EnumType.STRING)
    private POWER_TYPE powerType;

    /**
     * Resistencia del altavoz (por ejemplo, resistencia al agua, polvo o golpes).
     */
    @Column(name = "resistance")
    @Enumerated(EnumType.STRING)
    private RESISTANCE resistance;

    /**
     * Constructor parametrizado para inicializar un altavoz con sus atributos.
     *
     * @param model           El modelo del altavoz.
     * @param price           El precio del altavoz.
     * @param stock           La cantidad disponible en stock.
     * @param brand           La marca del altavoz.
     * @param color           El color del altavoz.
     * @param batteryLife     La duración de la batería en horas.
     * @param warranty        La garantía del altavoz en meses.
     * @param microphoneBuiltIn Indica si el altavoz tiene micrófono incorporado.
     * @param wireless        Indica si el altavoz es inalámbrico.
     * @param bluetooth       Indica si el altavoz tiene Bluetooth.
     * @param usb             Indica si el altavoz tiene puerto USB.
     * @param aux             Indica si el altavoz tiene entrada auxiliar (Aux).
     * @param radio           Indica si el altavoz tiene radio incorporado.
     * @param power           La potencia del altavoz en vatios (W).
     * @param impedance       La impedancia del altavoz en ohmios (Ω).
     * @param minFrequency    La frecuencia mínima del altavoz en hercios (Hz).
     * @param maxFrequency    La frecuencia máxima del altavoz en hercios (Hz).
     * @param weight          El peso del altavoz en gramos (g).
     * @param powerType       El tipo de alimentación del altavoz.
     * @param resistance      La resistencia del altavoz.
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
