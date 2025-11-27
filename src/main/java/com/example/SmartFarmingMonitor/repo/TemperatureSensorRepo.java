package com.example.SmartFarmingMonitor.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SmartFarmingMonitor.model.TemperatureSensor;

public interface TemperatureSensorRepo extends JpaRepository<TemperatureSensor, Long> {
    Optional<TemperatureSensor> findTopByOrderByTimeDesc();
    List<TemperatureSensor> findTop10ByOrderByTimeDesc();
}
