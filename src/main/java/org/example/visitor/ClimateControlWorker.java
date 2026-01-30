package org.example.visitor;

import org.example.composite.CompositeModelFunction;
import org.example.composite.CompositeModelParameter;
import org.example.utils.OperationContext;
import org.example.utils.OperationResult;

/**
 * Конкретный посетитель для климат-контроля
 */
public class ClimateControlWorker implements ModelVisitor {
    private OperationContext context = new OperationContext();

    @Override
    public void visit(CompositeModelFunction function) {
        if ("Климат-контроль".equalsIgnoreCase(function.getName())) {
            context.addLog("[INFO] Запуск климат-контроля");
            context.setDuration(2.0); // 2 часа по умолчанию

            // Расчет энергопотребления на основе параметров
            double basePower = 1000.0; // базовая мощность в Вт
            double ventilationPower = 0.0;
            double heatingPower = 0.0;

            for (CompositeModelParameter param : function.getParameters()) {
                if ("Скорость вентиляции".equalsIgnoreCase(param.getName())) {
                    if (!param.getValues().isEmpty()) {
                        double speed = param.getValues().get(0).getNumValue();
                        ventilationPower = speed * 0.5; // 0.5 Вт на м³/ч
                    }
                } else if ("Целевая температура".equalsIgnoreCase(param.getName())) {
                    if (!param.getValues().isEmpty()) {
                        double targetTemp = param.getValues().get(0).getNumValue();
                        heatingPower = (targetTemp - 20) * 200; // упрощенный расчет
                        if (heatingPower < 0) heatingPower = 0;
                    }
                }
            }

            double totalPower = basePower + ventilationPower + heatingPower;
            double energyConsumption = (totalPower * context.getDuration()) / 1000.0; // в кВт·ч

            context.setEnergyConsumption(energyConsumption);
            context.setStatus("УСПЕХ");
            context.addLog("[INFO] Климат-контроль завершен успешно");
        }
    }

    @Override
    public void visit(CompositeModelParameter parameter) {
        if (!parameter.getValues().isEmpty()) {
            String valueStr = parameter.getValues().get(0).getStringValue();
            double valueNum = parameter.getValues().get(0).getNumValue();

            if (!valueStr.isEmpty()) {
                context.addLog(String.format("[INFO] Параметр '%s': %s",
                        parameter.getName(), valueStr));
            } else {
                context.addLog(String.format("[INFO] Параметр '%s': %.1f",
                        parameter.getName(), valueNum));
            }
        }
    }

    public OperationResult getResult() {
        return new OperationResult(context);
    }
}
