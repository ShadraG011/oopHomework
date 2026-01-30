package org.example;

import org.example.equpmentObjects.EquipmentModel;
import org.example.typeObjects.EquipmentType;
import org.example.utils.OperationResult;
import org.example.visitor.ClimateControlWorker;
import org.example.visitor.ModelVisitor;

import java.util.*;

/**
 * Демонстрационный класс работы системы
 */
public class GreenhouseSystemDemo {
    public static void main(String[] args) {
        System.out.println("🌱 Демонстрация системы управления оборудованием для теплиц с применением паттернов проектирования\n");

        // 1. Создание заглушек для типов оборудования
        EquipmentType climateType = EquipmentType.createClimateEquipmentType();

        // 2. Создание базовой конфигурации для климат-контроля (прототип)
        EquipmentModel climatePrototype = EquipmentModel.createClimateControlPrototype(climateType);
        System.out.println("✅ Создан прототип: " + climatePrototype.getName());

        // 3. Клонирование прототипа для создания новой модели
        EquipmentModel tomatoClimate = climatePrototype.clone();
        tomatoClimate.setName("Климат-контроль для зоны томатов");
        System.out.println("\n🔧 Создана копия: " + tomatoClimate.getName());

        // 4. Модификация копии с реальной настройкой параметров
        Map<String, Double> tomatoParams = new HashMap<>();
        tomatoParams.put("Целевая влажность", 65.0);
        tomatoParams.put("Скорость вентиляции", 200.0);
        tomatoParams.put("Целевая температура", 23.0);

        EquipmentModel.configureModelParameters(tomatoClimate, "Климат-контроль", tomatoParams);

        // 5. Выполнение операции
        System.out.println("\n⚡ Запуск симуляции работы климат-контроля для томатов:\n");
        ModelVisitor worker = new ClimateControlWorker();
        OperationResult result = tomatoClimate.executeOperation(worker);
        printSimulationResults(tomatoClimate, result);

        // 6. Создание еще одной копии с другими параметрами
        System.out.println("\n🔄 Создание копии для зоны клубники:\n");
        EquipmentModel strawberryClimate = climatePrototype.clone();
        strawberryClimate.setName("Климат-контроль для зоны клубники");

        Map<String, Double> strawberryParams = new HashMap<>();
        strawberryParams.put("Целевая влажность", 75.0);
        strawberryParams.put("Скорость вентиляции", 100.0);
        strawberryParams.put("Целевая температура", 18.0);

        EquipmentModel.configureModelParameters(strawberryClimate, "Климат-контроль", strawberryParams);

        ModelVisitor strawberryWorker = new ClimateControlWorker();
        OperationResult strawberryResult = strawberryClimate.executeOperation(strawberryWorker);
        printSimulationResults(strawberryClimate, strawberryResult);

        System.out.println("\n✅ Симуляция завершена успешно!");
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