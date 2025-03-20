package com.recordstore.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.model.Speaker;

public interface SpeakerRepository extends JpaRepository <Speaker, Double> {

    boolean existsById(Integer id);

    void deleteById(Integer id);

    Optional<Speaker> findById(Integer id);

}
