package org.example.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Контекст операции для хранения результатов работы
 */
public class OperationContext {
    private Map<String, Object> parameters = new HashMap<>();
    private List<String> logs = new ArrayList<>();
    private double energyConsumption = 0.0;
    private double duration = 0.0;
    private String status = "IN_PROGRESS";

    public void setParameter(String name, Object value) {
        parameters.put(name, value);
    }

    public Object getParameter(String name) {
        return parameters.get(name);
    }

    public void addLog(String message) {
        logs.add(message);
    }

    public void setEnergyConsumption(double energy) {
        this.energyConsumption = energy;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public double getDuration() {
        return duration;
    }

    public String getStatus() {
        return status;
    }
}
