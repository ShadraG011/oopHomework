package org.example;

import org.example.equpmentObjects.EquipmentModel;
import org.example.factory.EquipmentCreator;
import org.example.mediator.EquipmentMediator;
import org.example.repository.EquipmentRepository;
import org.example.utils.OperationResult;
import org.example.visitor.ClimateControlWorker;
import org.example.visitor.ModelVisitor;

import java.util.*;

/**
 * Демонстрационный класс работы системы
 */
public class GreenhouseSystemDemo {
    public static void main(String[] args) {
        System.out.println("Демонстрация системы управления оборудованием для теплиц с применением паттернов проектирования\n");
        EquipmentCreator creator = new EquipmentCreator();
        EquipmentRepository repository = new EquipmentRepository();
        EquipmentMediator mediator = new EquipmentMediator(creator, repository);

        EquipmentModel climatePrototype = mediator.createEquipmentModel("climateControl", "climateControl");

        System.out.printf(
            "Создан прототип модели: %S\nТипом оборудования: %s",
            climatePrototype.getName(),
            climatePrototype.getEquipmentType().getName()
        );

        EquipmentModel tomatoClimate = climatePrototype.clone();
        tomatoClimate.setName("Климат-контроль для зоны томатов");
        mediator.addEquipmentModel(tomatoClimate);
        System.out.println("\n🔧 Создана копия: " + tomatoClimate.getName());

        Map<String, Double> tomatoParams = new HashMap<>();
        tomatoParams.put("Целевая влажность", 65.0);
        tomatoParams.put("Скорость вентиляции", 200.0);
        tomatoParams.put("Целевая температура", 23.0);

        EquipmentModel.configureModelParameters(tomatoClimate, "Климат-контроль", tomatoParams);

        System.out.println("\n Запуск симуляции работы климат-контроля для томатов:\n");
        ModelVisitor worker = new ClimateControlWorker();
        OperationResult result = tomatoClimate.executeOperation(worker);
        printSimulationResults(tomatoClimate, result);

        System.out.println("\nСоздание копии для зоны клубники:\n");
        EquipmentModel strawberryClimate = climatePrototype.clone();
        strawberryClimate.setName("Климат-контроль для зоны клубники");

        Map<String, Double> strawberryParams = new HashMap<>();
        strawberryParams.put("Целевая влажность", 75.0);
        strawberryParams.put("Скорость вентиляции", 100.0);
        strawberryParams.put("Целевая температура", 18.0);

        EquipmentModel.configureModelParameters(strawberryClimate, "Климат-контроль", strawberryParams);
        mediator.addEquipmentModel(strawberryClimate);
        ModelVisitor strawberryWorker = new ClimateControlWorker();
        OperationResult strawberryResult = strawberryClimate.executeOperation(strawberryWorker);
        printSimulationResults(strawberryClimate, strawberryResult);

        System.out.println("\nСимуляция завершена успешно!");
    }

    private static void printSimulationResults(EquipmentModel model, OperationResult result) {
        System.out.println("┌─────────────────────────────────────────────────────────────");
        System.out.println("│ " + model.getName());
        System.out.println("├─────────────────────────────────────────────────────────────");
        for (String log : result.getLogs()) {
            System.out.println("│ " + log);
        }
        System.out.println("├─────────────────────────────────────────────────────────────");
        System.out.println("│ Статус: " + result.getStatus());
        System.out.println("│ Потребленная энергия: " + String.format("%.1f кВт·ч", result.getEnergyConsumption()));
        System.out.println("│ Продолжительность: " + String.format("%.1f ч", result.getDuration()));
        System.out.println("└─────────────────────────────────────────────────────────────");
    }
}