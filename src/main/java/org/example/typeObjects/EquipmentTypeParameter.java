package org.example.typeObjects;

import org.example.utils.ParameterValue;

import java.util.ArrayList;
import java.util.List;

public class EquipmentTypeParameter {
    private String name;
    private List<ParameterValue> values;

    public EquipmentTypeParameter(String name, List<ParameterValue> values) {
        this.name = name;
        this.values = values != null ? new ArrayList<>(values) : new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<ParameterValue> getValues() {
        return new ArrayList<>(values);
    }
}
