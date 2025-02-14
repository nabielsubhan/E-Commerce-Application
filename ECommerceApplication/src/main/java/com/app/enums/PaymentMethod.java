package com.app.enums;

public enum PaymentMethod {
    COD("COD");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
