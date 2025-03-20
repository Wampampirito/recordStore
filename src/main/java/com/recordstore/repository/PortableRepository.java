package com.recordstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.recordstore.model.Portable;

public interface PortableRepository extends JpaRepository<Portable, Double> {
}