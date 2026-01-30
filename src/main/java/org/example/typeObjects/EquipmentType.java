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

    public static EquipmentType createClimateEquipmentType() {
        // Создание типов параметров для совместимости с исходной структурой
        List<ParameterValue> tempValues = Arrays.asList(
            new ParameterValue(18, ""),
            new ParameterValue(20, ""),
            new ParameterValue(22, ""),
            new ParameterValue(25, "")
        );

        List<ParameterValue> humidityValues = Arrays.asList(
            new ParameterValue(60, ""),
            new ParameterValue(65, ""),
            new ParameterValue(70, ""),
            new ParameterValue(75, "")
        );

        List<ParameterValue> ventilationValues = Arrays.asList(
            new ParameterValue(100, ""),
            new ParameterValue(150, ""),
            new ParameterValue(200, "")
        );

        EquipmentTypeParameter tempParam = new EquipmentTypeParameter("Целевая температура", tempValues);
        EquipmentTypeParameter humidityParam = new EquipmentTypeParameter("Целевая влажность", humidityValues);
        EquipmentTypeParameter ventilationParam = new EquipmentTypeParameter("Скорость вентиляции", ventilationValues);

        EquipmentTypeFunction climateFunction = new EquipmentTypeFunction(
            "Климат-контроль",
            Arrays.asList(tempParam, humidityParam, ventilationParam)
        );

        return new EquipmentType(
            "Система климат-контроля",
            Arrays.asList(tempParam, humidityParam, ventilationParam),
            Arrays.asList(climateFunction)
        );
    }
}
