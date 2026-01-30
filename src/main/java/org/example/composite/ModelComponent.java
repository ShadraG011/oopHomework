package org.example.composite;

import org.example.prototype.Prototype;
import org.example.visitor.ModelVisitor;

/**
 * Интерфейс для паттерна Компоновщик - базовый компонент иерархии
 */
public interface ModelComponent extends Prototype<ModelComponent> {
    void accept(ModelVisitor visitor);
}
