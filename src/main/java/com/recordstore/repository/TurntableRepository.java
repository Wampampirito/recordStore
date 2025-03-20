package com.recordstore.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.model.Turntable;

public interface TurntableRepository extends JpaRepository<Turntable, Double> {
}