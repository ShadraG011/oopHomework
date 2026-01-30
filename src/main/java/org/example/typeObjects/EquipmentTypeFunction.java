package org.example.typeObjects;

import java.util.ArrayList;
import java.util.List;

public class EquipmentTypeFunction {
    private String name;
    private List<EquipmentTypeParameter> workParameters;

    public EquipmentTypeFunction(String name, List<EquipmentTypeParameter> workParameters) {
        this.name = name;
        this.workParameters = workParameters != null ? new ArrayList<>(workParameters) : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<EquipmentTypeParameter> getWorkParameters() {
        return new ArrayList<>(workParameters);
    }
}
