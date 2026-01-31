package org.example.strategy;

import org.example.composite.CompositeModelFunction;
import org.example.composite.CompositeModelParameter;
import org.example.composite.LeafParameterValue;
import org.example.equpmentObjects.EquipmentModel;
import org.example.typeObjects.EquipmentType;
import org.example.typeObjects.EquipmentTypeFunction;
import org.example.typeObjects.EquipmentTypeParameter;
import org.example.utils.ParameterValue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClimateEquipmentTypeCreationStrategy implements EquipmentCreationStrategy {

    @Override
    public EquipmentModel create(String name) {
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

        EquipmentType type = new EquipmentType(
                "Система климат-контроля",
                Arrays.asList(tempParam, humidityParam, ventilationParam),
                Arrays.asList(climateFunction));

        CompositeModelFunction climateControl = new CompositeModelFunction("Климат-контроль");

        CompositeModelParameter tempParam1 = new CompositeModelParameter("Целевая температура");
        tempParam1.addValue(new LeafParameterValue(20.0, ""));

        CompositeModelParameter humidityParam1 = new CompositeModelParameter("Целевая влажность");
        humidityParam1.addValue(new LeafParameterValue(65.0, ""));

        CompositeModelParameter ventilationParam1 = new CompositeModelParameter("Скорость вентиляции");
        ventilationParam1.addValue(new LeafParameterValue(150.0, ""));

        climateControl.addParameter(tempParam1);
        climateControl.addParameter(humidityParam1);
        climateControl.addParameter(ventilationParam1);

        return new EquipmentModel(type, "Базовый климат-контроль",
                Arrays.asList(climateControl));
    }
}
