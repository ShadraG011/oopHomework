package org.example.strategy;

import org.example.equpmentObjects.EquipmentModel;
import org.example.typeObjects.EquipmentType;
import org.example.typeObjects.EquipmentTypeFunction;
import org.example.typeObjects.EquipmentTypeParameter;
import org.example.utils.ParameterValue;

import java.util.ArrayList;
import java.util.Arrays;

public class PumpCreationStrategy implements EquipmentCreationStrategy {

    @Override
    public EquipmentModel create(String name) {
        EquipmentTypeFunction turnOn = new EquipmentTypeFunction("turnOn", new ArrayList<>());
        EquipmentTypeFunction turnOff = new EquipmentTypeFunction("turnOff", new ArrayList<>());
        EquipmentTypeParameter power = new EquipmentTypeParameter("power",
                Arrays.asList(new ParameterValue(100L, "")));
        EquipmentType pump = new EquipmentType("pump", Arrays.asList(power), Arrays.asList(turnOn, turnOff));
        return new EquipmentModel(pump, name, new ArrayList<>());
    }
}
