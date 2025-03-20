package com.recordstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.model.AudioEquipment;

public interface AudioEquipmentRepository extends JpaRepository <AudioEquipment, Double> {

}
