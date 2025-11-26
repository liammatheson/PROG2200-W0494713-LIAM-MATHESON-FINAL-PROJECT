package com.example.SmartFarmingMonitor.model;

import jakarta.persistence.Entity;

@Entity
public class TemperatureSensor extends SensorSensor {

    private static final double LOW_THRESHOLD = 16.0;

    @Override
    public void process() {
        if (value <= LOW_THRESHOLD) {
            alertService.addAlert(new Alert("Low Temperature " + value));
        }
    }

}
