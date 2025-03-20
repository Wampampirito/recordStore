package com.recordstore.mapper;

import com.recordstore.model.Player;
import com.recordstore.dto.PlayerDTO;
import org.springframework.stereotype.Component;

/**
 * Mapper class responsible for converting between {@link com.recordstore.model.Player} entity and 
 * {@link com.recordstore.dto.PlayerDTO}.
 * <p>This class provides methods to convert a {@link com.recordstore.model.Player} entity to 
 * a {@link com.recordstore.dto.PlayerDTO} and vice versa.</p>
 * 
 * <p>Main methods:</p>
 * <ul>
 *   <li><b>toDTO</b>: Converts a {@link com.recordstore.model.Player} entity into a {@link com.recordstore.dto.PlayerDTO} object.</li>
 *   <li><b>toEntity</b>: Converts a {@link com.recordstore.dto.PlayerDTO} object into a {@link com.recordstore.model.Player} entity.</li>
 * </ul>
 * 
 * <p>Usage example:</p>
 * <pre>
 * // Convert a Player entity to a PlayerDTO
 * PlayerDTO playerDTO = playerMapper.toDTO(player);
 * 
 * // Convert a PlayerDTO to a Player entity
 * Player playerEntity = playerMapper.toEntity(playerDTO);
 * </pre>
 */
@Component
public class PlayerMapper {

    /**
     * Converts a {@link com.recordstore.model.Player} entity to a {@link com.recordstore.dto.PlayerDTO}.
     * 
     * @param player The {@link com.recordstore.model.Player} entity to be converted.
     * @return The corresponding {@link com.recordstore.dto.PlayerDTO} object.
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
     * Converts a {@link com.recordstore.dto.PlayerDTO} object to a {@link com.recordstore.model.Player} entity.
     * 
     * @param playerDTO The {@link com.recordstore.dto.PlayerDTO} to be converted.
     * @return The corresponding {@link com.recordstore.model.Player} entity.
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
