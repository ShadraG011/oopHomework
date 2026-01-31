package org.example.typeObjects;

import org.example.utils.ParameterValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Исходные классы предметной области (для совместимости)
 */
public class EquipmentType {
    private String name;
    private List<EquipmentTypeParameter> parameters;
    private List<EquipmentTypeFunction> functions;

    public EquipmentType(String name, List<EquipmentTypeParameter> parameters,
                         List<EquipmentTypeFunction> functions) {
        this.name = name;
        this.parameters = parameters != null ? new ArrayList<>(parameters) : new ArrayList<>();
        this.functions = functions != null ? new ArrayList<>(functions) : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<EquipmentTypeParameter> getParameters() {
        return new ArrayList<>(parameters);
    }

    public List<EquipmentTypeFunction> getFunctions() {
        return new ArrayList<>(functions);
    }
}
