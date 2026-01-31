package org.example.mediator;

import org.example.equpmentObjects.EquipmentModel;
import org.example.factory.EquipmentCreator;
import org.example.repository.*;

import java.util.List;

public class EquipmentMediator {
    private final EquipmentCreator creator;
    private final EquipmentRepository repository;

    public EquipmentMediator(
            EquipmentCreator creator,
            EquipmentRepository repository
    ) {
        this.creator = creator;
        this.repository = repository;
    }

    public EquipmentModel createEquipmentModel(String type, String name) {
        EquipmentModel model = creator.create(type, name);
        repository.save(model);
        return model;
    }

    public EquipmentModel addEquipmentModel(EquipmentModel model) {
        repository.save(model);
        return model;
    }

    public EquipmentModel getEquipmentModelByName(String name) {
        return repository.findByName(name);
    }

    public void deleteEquipmentByName(String name) {
        repository.delete(name);
    }

    public List<EquipmentModel> getAll() {
        return repository.findAll();
    }
}
