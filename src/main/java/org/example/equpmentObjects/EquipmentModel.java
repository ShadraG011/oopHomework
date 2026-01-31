package org.example.equpmentObjects;

import java.util.*;
import org.example.prototype.Prototype;
import org.example.visitor.ModelVisitor;
import org.example.utils.ParameterValue;
import org.example.utils.OperationResult;
import org.example.composite.ModelComponent;
import org.example.typeObjects.EquipmentType;
import org.example.visitor.ClimateControlWorker;
import org.example.composite.LeafParameterValue;
import org.example.composite.CompositeModelFunction;
import org.example.composite.CompositeModelParameter;
import org.example.typeObjects.EquipmentTypeFunction;
import org.example.typeObjects.EquipmentTypeParameter;

/**
 * Основной класс с применением паттерна Прототип
 */
public class EquipmentModel implements Prototype {
    private String name;
    private EquipmentType equipmentType;
    private List<ModelComponent> modelComponents;

    /**
     * Конструктор для создания модели оборудования
     */
    public EquipmentModel(EquipmentType equipmentType, String name,
                          List<ModelComponent> modelComponents) {
        this.equipmentType = equipmentType;
        this.name = name;
        this.modelComponents = modelComponents;
    }

    /**
     * Паттерн Прототип: глубокое клонирование модели
     */
    @Override
    public EquipmentModel clone() {
        // Создаем новый экземпляр без использования super.clone()
        EquipmentModel clone = new EquipmentModel(
                this.equipmentType,
                this.name + " (копия)", // Добавляем пометку для наглядности
                new ArrayList<>()
        );

        // Глубокое копирование компонентов
        for (ModelComponent component : this.modelComponents) {
            clone.modelComponents.add(component.clone());
        }

        return clone;
    }

    /**
     * Выполнение операции с использованием паттерна Посетитель
     */
    public OperationResult executeOperation(ModelVisitor visitor) {
        for (ModelComponent component : modelComponents) {
            component.accept(visitor);
        }
        return ((ClimateControlWorker) visitor).getResult();
    }

    // Геттеры и сеттеры
    public EquipmentType getEquipmentType() {
        return equipmentType;
    }

    public String getName() {
        return name;
    }

    public List<ModelComponent> getModelComponents() {
        return new ArrayList<>(modelComponents);
    }

    public void setModelComponents(List<ModelComponent> modelComponents) {
        this.modelComponents = modelComponents;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEquipmentType(EquipmentType equipmentType) {
        this.equipmentType = equipmentType;
    }

    /**
     * Метод для настройки параметров модели с валидацией значений
     * @param model Модель оборудования для настройки
     * @param functionName Название функции, которую нужно настроить
     * @param parameterConfigurations Конфигурация параметров в виде Map<название параметра, значение>
     */
    public static void configureModelParameters(
            EquipmentModel model,
            String functionName,
            Map<String, Double> parameterConfigurations
    ) throws IllegalArgumentException {
        // Находим функцию в модели
        Optional<CompositeModelFunction> targetFunction = model.getModelComponents().stream()
                .filter(component -> component instanceof CompositeModelFunction)
                .map(component -> (CompositeModelFunction) component)
                .filter(func -> functionName.equalsIgnoreCase(func.getName()))
                .findFirst();

        if (targetFunction.isEmpty()) {
            throw new IllegalArgumentException("Функция '" + functionName + "' не найдена в модели");
        }

        CompositeModelFunction function = targetFunction.get();

        // Получаем тип оборудования для валидации значений
        EquipmentType equipmentType = model.getEquipmentType();

        // Настраиваем каждый параметр из конфигурации
        for (Map.Entry<String, Double> entry : parameterConfigurations.entrySet()) {
            String paramName = entry.getKey();
            double value = entry.getValue();

            // Находим параметр в функции
            Optional<CompositeModelParameter> targetParam = function.getParameters().stream()
                    .filter(param -> paramName.equalsIgnoreCase(param.getName()))
                    .findFirst();

            if (targetParam.isEmpty()) {
                throw new IllegalArgumentException(
                        "Параметр '" + paramName + "' не найден в функции '" + functionName + "'"
                );
            }

            CompositeModelParameter parameter = targetParam.get();

            // Валидация значения на основе типа оборудования
            validateParameterValueAgainstEquipmentType(equipmentType, functionName, paramName, value);

            // Устанавливаем новое значение параметра
            if (!parameter.getValues().isEmpty()) {
                // Обновляем существующее значение
                LeafParameterValue existingValue = parameter.getValues().get(0);
                existingValue.setNumValue(value);
            } else {
                // Создаем новое значение
                parameter.addValue(new LeafParameterValue(value, ""));
            }
        }

        System.out.println("Параметры модели '" + model.getName() + "' успешно настроены:");
        parameterConfigurations.forEach(
            (param, val) -> System.out.println("   • " + param + ": " + val)
        );
    }

    /**
     * Валидация значения параметра на соответствие диапазону значений типа оборудования
     */
    private static void validateParameterValueAgainstEquipmentType(
            EquipmentType equipmentType,
            String functionName,
            String paramName,
            double value
    ) {
        // Находим функцию в типе оборудования
        Optional<EquipmentTypeFunction> typeFunction = equipmentType.getFunctions().stream()
                .filter(func -> functionName.equalsIgnoreCase(func.getName()))
                .findFirst();

        if (typeFunction.isEmpty()) {
            throw new IllegalArgumentException(
                    "Функция '" + functionName + "' не поддерживается типом оборудования '" +
                            equipmentType.getName() + "'"
            );
        }

        // Находим параметр в функции типа оборудования
        Optional<EquipmentTypeParameter> typeParam = typeFunction.get().getWorkParameters().stream()
                .filter(param -> paramName.equalsIgnoreCase(param.getName()))
                .findFirst();

        if (typeParam.isEmpty()) {
            throw new IllegalArgumentException(
                    "Параметр '" + paramName + "' не поддерживается функцией '" +
                            functionName + "' типа оборудования '" + equipmentType.getName() + "'"
            );
        }

        // Получаем допустимые значения для параметра
        List<ParameterValue> allowedValues = typeParam.get().getValues();
        if (allowedValues.isEmpty()) {
            return; // Нет ограничений на значения
        }

        // Находим минимальное и максимальное значения
        double minValue = allowedValues.stream()
                .mapToDouble(ParameterValue::getNumValue)
                .min()
                .orElse(Double.NEGATIVE_INFINITY);

        double maxValue = allowedValues.stream()
                .mapToDouble(ParameterValue::getNumValue)
                .max()
                .orElse(Double.POSITIVE_INFINITY);

        // Проверяем, что значение находится в допустимом диапазоне
        if (value < minValue || value > maxValue) {
            throw new IllegalArgumentException(String.format(
                    "Значение %.1f для параметра '%s' выходит за допустимый диапазон [%.1f, %.1f] для типа оборудования '%s'",
                    value, paramName, minValue, maxValue, equipmentType.getName()
            ));
        }
    }
}
