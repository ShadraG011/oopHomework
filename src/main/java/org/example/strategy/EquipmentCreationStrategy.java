package org.example.strategy;

import org.example.equpmentObjects.EquipmentModel;

public interface EquipmentCreationStrategy {
    EquipmentModel create(String name);
}
