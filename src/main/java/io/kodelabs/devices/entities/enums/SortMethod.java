package io.kodelabs.devices.entities.enums;

import lombok.Getter;

@Getter
public enum SortMethod {
    ASCENDING("Ascending"), DESCENDING("Descending");
    private final String sortMethod;

    private SortMethod(String sortMethod) {
        this.sortMethod = sortMethod;
    }
}
