package com.example.SmartFarmingMonitor.model;

import com.example.SmartFarmingMonitor.service.AlertService;

import jakarta.persistence.Entity;

@Entity
public class MoistureSensor extends SensorSensor {

    private static final double LOW_THRESHOLD = 50.0;

    @Override
    public void process() {
        if (value <= LOW_THRESHOLD) {
            alertService.addAlert(new Alert("Low Moisture " + value));
        }
    }

}
