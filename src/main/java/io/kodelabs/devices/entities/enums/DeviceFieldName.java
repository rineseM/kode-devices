package io.kodelabs.devices.entities.enums;

public enum DeviceFieldName {
    NAME("name"),
    MANUFACTURER("manufacturer"),
    PRICE("price"),
    YEAR("year"),
    STATUS("status");
    private final String fieldName;
    private DeviceFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
