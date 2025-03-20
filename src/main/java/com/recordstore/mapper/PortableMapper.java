package com.recordstore.mapper;

import org.springframework.stereotype.Component;

import com.recordstore.dto.PortableDTO;
import com.recordstore.model.Portable;

@Component
public class PortableMapper {
    public  PortableDTO toDTO(Portable portable) {
        PortableDTO portableDTO = new PortableDTO();

         
        //Fields inherited from Product
        portableDTO.setId(portable.getId());
        portableDTO.setName(portable.getName());
        portableDTO.setPrice(portable.getPrice());
        portableDTO.setStock(portable.getStock());
        portableDTO.setProductCategory(portable.getProductCategory());
   
        //Fields inherited from Player
        portableDTO.setBrand(portable.getBrand());
        portableDTO.setColor(portable.getColor());
        portableDTO.setWarranty(portable.getWarranty());
        portableDTO.setBluetooth(portable.getBluetooth());
        portableDTO.setUsb(portable.getUsb());
        portableDTO.setRadio(portable.getRadio());
        portableDTO.setAux(portable.getAux());
        portableDTO.setRca(portable.getRca());
        portableDTO.setBuiltInSpeaker(portable.getBuiltInSpeaker());

        //Specific fields of Portable
        portableDTO.setPortableType(portable.getPortableType());
        portableDTO.setPowerType(portable.getPowerType());
        portableDTO.setBatteryLife(portable.getBatteryLife());
        portableDTO.setResistance(portable.getResistance());

        return portableDTO;
    }

    public Portable toEntity (PortableDTO portableDTO) {
        Portable portable = new Portable();

        //Fields inherited from Product
        portable.setId(portableDTO.getId());
        portable.setName(portableDTO.getName());
        portable.setPrice(portableDTO.getPrice());
        portable.setStock(portableDTO.getStock());
        portable.setProductCategory(portableDTO.getProductCategory());
   
        //Fields inherited from Player
        portable.setBrand(portableDTO.getBrand());
        portable.setColor(portableDTO.getColor());
        portable.setWarranty(portableDTO.getWarranty());
        portable.setBluetooth(portableDTO.getBluetooth());
        portable.setUsb(portableDTO.getUsb());
        portable.setRadio(portableDTO.getRadio());
        portable.setAux(portableDTO.getAux());
        portable.setRca(portableDTO.getRca());
        portable.setBuiltInSpeaker(portableDTO.getBuiltInSpeaker());

        //Specific fields of Portable
        portable.setPortableType(portableDTO.getPortableType());
        portable.setPowerType(portableDTO.getPowerType());
        portable.setBatteryLife(portableDTO.getBatteryLife());
        portable.setResistance(portableDTO.getResistance());

        return portable;
    }

}
