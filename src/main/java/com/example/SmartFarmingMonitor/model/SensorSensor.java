package com.example.SmartFarmingMonitor.model;

import java.time.LocalDateTime;

import com.example.SmartFarmingMonitor.service.AlertService;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Transient;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class SensorSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDateTime time;
    protected double value;

    @Transient
    protected AlertService alertService;

    public void setAlertService(AlertService svc) {
        this.alertService = svc;
    }

    public SensorSensor() {
        this.time = LocalDateTime.now();
    }

    public abstract void process();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

}
