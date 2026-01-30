package org.example.composite;

import org.example.visitor.ModelVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Составной параметр модели (Composite для параметров)
 */
public class CompositeModelParameter implements ModelComponent {
    private String name;
    private List<LeafParameterValue> values = new ArrayList<>();

    public CompositeModelParameter(String name) {
        this.name = name;
    }

    public void addValue(LeafParameterValue value) {
        values.add(value);
    }

    public String getName() {
        return name;
    }

    public List<LeafParameterValue> getValues() {
        return new ArrayList<>(values);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
        for (LeafParameterValue value : values) {
            // Листовые значения не посещаются напрямую в данном упрощенном варианте
        }
    }

    @Override
    public ModelComponent clone() {
        CompositeModelParameter clone = new CompositeModelParameter(this.name);
        for (LeafParameterValue value : values) {
            clone.addValue((LeafParameterValue) value.clone());
        }
        return clone;
    }
}
