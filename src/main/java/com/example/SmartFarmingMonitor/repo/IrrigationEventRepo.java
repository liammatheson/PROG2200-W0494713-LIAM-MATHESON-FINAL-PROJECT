package com.example.SmartFarmingMonitor.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.SmartFarmingMonitor.model.IrrigationEvent;

public interface IrrigationEventRepo extends JpaRepository<IrrigationEvent, Long> {
    List<IrrigationEvent> findTop5ByOrderByTimeDesc();
}
