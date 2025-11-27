package com.example.SmartFarmingMonitor.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SmartFarmingMonitor.model.NutrientSensor;

public interface NutrientSensorRepo extends JpaRepository<NutrientSensor, Long> {
    Optional<NutrientSensor> findTopByOrderByTimeDesc();
    List<NutrientSensor> findTop10ByOrderByTimeDesc();
}
