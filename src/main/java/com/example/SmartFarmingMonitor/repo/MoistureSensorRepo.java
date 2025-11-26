package com.example.SmartFarmingMonitor.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SmartFarmingMonitor.model.MoistureSensor;

public interface MoistureSensorRepo extends JpaRepository<MoistureSensor, Long> {
    Optional<MoistureSensor> findTopByOrderByTimeDesc();

    List<MoistureSensor> findTop10ByOrderByTimeDesc();
}
