package org.example.composite;

import org.example.visitor.ModelVisitor;

/**
 * Листовое значение параметра (Leaf для значений)
 */
public class LeafParameterValue implements ModelComponent {
    private double numValue;
    private String stringValue;

    public LeafParameterValue(double numValue, String stringValue) {
        this.numValue = numValue;
        this.stringValue = stringValue;
    }

    public double getNumValue() {
        return numValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setNumValue(double numValue) {
        this.numValue = numValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public void accept(ModelVisitor visitor) {
        // Листовые значения не требуют посещения в данном упрощенном варианте
    }

    @Override
    public ModelComponent clone() {
        return new LeafParameterValue(this.numValue, this.stringValue);
    }
}
