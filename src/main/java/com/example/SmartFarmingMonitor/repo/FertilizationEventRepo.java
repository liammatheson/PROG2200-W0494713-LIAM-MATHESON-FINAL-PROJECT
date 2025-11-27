package com.example.SmartFarmingMonitor.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.SmartFarmingMonitor.model.FertilizationEvent;

public interface FertilizationEventRepo extends JpaRepository<FertilizationEvent, Long> {
    List<FertilizationEvent> findTop5ByOrderByTimeDesc();
}
