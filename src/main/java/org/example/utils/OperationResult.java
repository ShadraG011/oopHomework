package org.example.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Результат операции
 */
public class OperationResult {
    private String status;
    private double energyConsumption;
    private double duration;
    private List<String> logs;

    public OperationResult(OperationContext context) {
        this.status = context.getStatus();
        this.energyConsumption = context.getEnergyConsumption();
        this.duration = context.getDuration();
        this.logs = context.getLogs();
    }

    public String getStatus() {
        return status;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public double getDuration() {
        return duration;
    }

    public List<String> getLogs() {
        return new ArrayList<>(logs);
    }
}
