package org.example.repository;

import org.example.equpmentObjects.EquipmentModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EquipmentRepository {
    private final Map<String, EquipmentModel> storage = new HashMap<>();

    public void save(EquipmentModel model) {
        if (storage.get(model.getName()) != null) {
            System.out.println("Модель с таким именем уже существует!");
            return;
        }
        storage.put(model.getName(), model);
    }

    public EquipmentModel findByName(String name) {
        return storage.get(name);
    }

    public List<EquipmentModel> findAll() {
        return new ArrayList<>(storage.values());
    }

    public List<EquipmentModel> searchByType(String type) {
        return storage.values().stream()
                .filter(m -> m.getEquipmentType() != null && m.getEquipmentType().getName().equals(type))
                .collect(Collectors.toList());
    }

    public void delete(String name) {
        storage.remove(name);
    }

}