package org.example.visitor;

import org.example.composite.CompositeModelFunction;
import org.example.composite.CompositeModelParameter;

/**
 * Интерфейс для паттерна Посетитель
 */
public interface ModelVisitor {
    void visit(CompositeModelFunction function);
    void visit(CompositeModelParameter parameter);
}
