package com.recordstore.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.enums.HEADPHONES_TYPE;
import com.recordstore.enums.NOISE_CANCELING;
import com.recordstore.model.Headphone;

public interface HeadphoneRepository extends JpaRepository <Headphone, Integer> {

    Headphone getHeadphoneById(Integer id);


    List<Headphone> findByAnc(NOISE_CANCELING anc);

    List<Headphone> findByWireless(Boolean wireless);

    List<Headphone> findByBluetooth(Boolean bluetooth);

    List<Headphone> findByWarranty(Integer warranty);

    List<Headphone> findByHeadphoneType(HEADPHONES_TYPE headphoneType);

    List<Headphone> findByBatteryLifeGreaterThanEqual(Integer batteryLife);

    List<Headphone> findByWarrantyGreaterThanEqual(Integer warranty);


}
