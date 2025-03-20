package com.recordstore.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.dto.TurntableDTO;
import com.recordstore.model.Turntable;

public interface TurntableRepository extends JpaRepository<Turntable, Integer> {

    List<Turntable> findByUsb(boolean usb);

    List<TurntableDTO> findByBrand(String brand);

    List<TurntableDTO> findByColor(String color);

    List<TurntableDTO> findByWarrantyGreaterThanEqual(Integer warranty);

    List<TurntableDTO> findByBluetooth(boolean bluetooth);
    
}