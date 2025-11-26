package com.example.SmartFarmingMonitor.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SmartFarmingMonitor.model.Alert;

@Service
public class AlertService {

    private final List<Alert> alerts = new ArrayList<>();

    public void addAlert(Alert alert) {
        alerts.add(alert);
    }

    public List<Alert> getAlerts() {
        return new ArrayList<>(alerts);
    }

    public void clearAlerts() {
        alerts.clear();
    }
}
