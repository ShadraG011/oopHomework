package org.example.prototype;

import org.example.equpmentObjects.EquipmentModel;

public interface Prototype<T> {
    T clone();
}
