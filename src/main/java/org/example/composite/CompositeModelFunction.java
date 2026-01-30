package org.example.composite;

import org.example.visitor.ModelVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Составная функция модели (Composite для функций)
 */
public class CompositeModelFunction implements ModelComponent {
    private String name;
    private List<CompositeModelParameter> parameters = new ArrayList<>();

    public CompositeModelFunction(String name) {
        this.name = name;
    }

    public void addParameter(CompositeModelParameter parameter) {
        parameters.add(parameter);
    }

    public String getName() {
        return name;
    }

    public List<CompositeModelParameter> getParameters() {
        return new ArrayList<>(parameters);
    }

    @Override
    public void accept(ModelVisitor visitor) {
        visitor.visit(this);
        for (CompositeModelParameter param : parameters) {
            param.accept(visitor);
        }
    }

    @Override
    public ModelComponent clone() {
        CompositeModelFunction clone = new CompositeModelFunction(this.name);
        for (CompositeModelParameter param : parameters) {
            clone.addParameter((CompositeModelParameter) param.clone());
        }
        return clone;
    }
}
