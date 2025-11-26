package com.example.SmartFarmingMonitor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.SmartFarmingMonitor.model.MoistureSensor;
import com.example.SmartFarmingMonitor.model.NutrientSensor;
import com.example.SmartFarmingMonitor.model.TemperatureSensor;
import com.example.SmartFarmingMonitor.repo.MoistureSensorRepo;
import com.example.SmartFarmingMonitor.repo.NutrientSensorRepo;
import com.example.SmartFarmingMonitor.repo.TemperatureSensorRepo;
import com.example.SmartFarmingMonitor.service.AlertService;

import jakarta.annotation.PostConstruct;

@Component
public class SensorThread implements Runnable {

    @Autowired
    private MoistureSensorRepo moistureRepo;
    @Autowired
    private TemperatureSensorRepo tempRepo;
    @Autowired
    private NutrientSensorRepo nutrientRepo;
    @Autowired
    private AlertService alertService;

    private Thread thread;

    @PostConstruct
    public void startThread() {
        thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }

    @Override
    public void run() {

        MoistureSensor moistureSensor = new MoistureSensor();
        TemperatureSensor temperatureSensor = new TemperatureSensor();
        NutrientSensor nutrientSensor = new NutrientSensor();

        moistureSensor.setAlertService(alertService);
        temperatureSensor.setAlertService(alertService);
        nutrientSensor.setAlertService(alertService);

        while (true) {
            try {

                // simulate sensor values
                moistureSensor.setValue(Math.random() * 100);
                temperatureSensor.setValue(15 + Math.random() * 10);
                nutrientSensor.setValue(Math.random() * 50);

                // process alerts
                moistureSensor.process();
                temperatureSensor.process();
                nutrientSensor.process();

                // save to db
                moistureRepo.save(moistureSensor);
                tempRepo.save(temperatureSensor);
                nutrientRepo.save(nutrientSensor);

                Thread.sleep(5000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
