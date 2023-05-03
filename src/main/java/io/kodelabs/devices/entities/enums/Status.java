package io.kodelabs.devices.entities.enums;

import lombok.Getter;
@Getter
public enum Status {
    ACTIVE("Activating"),
    ON("On"),
    DEACTIVE("Deactivating"),
    OFF("Off"),
    ERROR("Error");
    private final String status;
    private Status(String status) {
        this.status = status;
    }
}
