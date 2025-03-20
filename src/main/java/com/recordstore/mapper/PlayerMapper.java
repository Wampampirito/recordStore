package com.recordstore.mapper;

import com.recordstore.model.Player;
import com.recordstore.dto.PlayerDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper class to convert between Player entity and PlayerDTO.
 */
@Component
public class PlayerMapper {

    /**
     * Converts a Player entity to a PlayerDTO.
     * 
     * @param player The Player entity to be converted.
     * @return The PlayerDTO corresponding to the Player entity.
     */
    public PlayerDTO toDTO(Player player) {
        if (player == null) {
            return null;
        }
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setPrice(player.getPrice());
        playerDTO.setStock(player.getStock());
        playerDTO.setProductCategory(player.getProductCategory());
        playerDTO.setBrand(player.getBrand());
        playerDTO.setColor(player.getColor());
        playerDTO.setWarranty(player.getWarranty());
        playerDTO.setBluetooth(player.getBluetooth());
        playerDTO.setUsb(player.getUsb());
        playerDTO.setRadio(player.getRadio());
        playerDTO.setAux(player.getAux());
        playerDTO.setRca(player.getRca());
        playerDTO.setBuiltInSpeaker(player.getBuiltInSpeaker());
        return playerDTO;
    }

    /**
     * Converts a PlayerDTO to a Player entity.
     * 
     * @param playerDTO The PlayerDTO to be converted.
     * @return The Player entity corresponding to the PlayerDTO.
     */
    public Player toEntity(PlayerDTO playerDTO) {
        if (playerDTO == null) {
            return null;
        }
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setName(playerDTO.getName());
        player.setPrice(playerDTO.getPrice());
        player.setStock(playerDTO.getStock());
        player.setProductCategory(playerDTO.getProductCategory());
        player.setBrand(playerDTO.getBrand());
        player.setColor(playerDTO.getColor());
        player.setWarranty(playerDTO.getWarranty());
        player.setBluetooth(playerDTO.getBluetooth());
        player.setUsb(playerDTO.getUsb());
        player.setRadio(playerDTO.getRadio());
        player.setAux(playerDTO.getAux());
        player.setRca(playerDTO.getRca());
        player.setBuiltInSpeaker(playerDTO.getBuiltInSpeaker());
        return player;
    }
}

