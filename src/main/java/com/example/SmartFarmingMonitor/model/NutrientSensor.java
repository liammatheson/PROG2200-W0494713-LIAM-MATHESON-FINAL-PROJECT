package com.example.SmartFarmingMonitor.model;

import jakarta.persistence.Entity;

@Entity
public class NutrientSensor extends SensorSensor {

    private static final double LOW_THRESHOLD = 20.0;

    @Override
    public void process() {
        if (value <= LOW_THRESHOLD) {
            alertService.addAlert(new Alert("Low Nutrients " + value));
        }
    }

}
