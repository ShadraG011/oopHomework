package org.example.strategy;

import org.example.equpmentObjects.EquipmentModel;
import org.example.typeObjects.*;
import org.example.utils.ParameterValue;

import java.util.ArrayList;
import java.util.Arrays;

public class LightCreationStrategy implements EquipmentCreationStrategy {
    @Override
    public EquipmentModel create(String name) {
        EquipmentTypeFunction turnOn = new EquipmentTypeFunction("turnOn", new ArrayList<>());
        EquipmentTypeFunction turnOff = new EquipmentTypeFunction("turnOff", new ArrayList<>());
        EquipmentTypeParameter brightness = new EquipmentTypeParameter("brightness",
                Arrays.asList(new ParameterValue(100L, "")));
        EquipmentType light = new EquipmentType("light", Arrays.asList(brightness), Arrays.asList(turnOn, turnOff));
        return new EquipmentModel(light, name, new ArrayList<>());
    }
}
