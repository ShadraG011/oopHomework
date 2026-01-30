package org.example.utils;

public class ParameterValue {
    private double numValue;
    private String stringValue;

    public ParameterValue(double numValue, String stringValue) {
        this.numValue = numValue;
        this.stringValue = stringValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    public double getNumValue() {
        return numValue;
    }

    @Override
    public String toString() {
        return stringValue != null && !stringValue.isEmpty() ? stringValue : String.valueOf(numValue);
    }
}
