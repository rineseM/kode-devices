package io.kodelabs.devices.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.kodelabs.devices.utils.Utils;
import io.kodelabs.devices.entities.enums.Status;
import io.kodelabs.devices.entities.interfaces.DeviceController;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ElectronicDevice implements DeviceController {
    String id;
    // add Hibernate validator, use annotations to make params required
    String name;
    String manufacturer;
    double price;
    int year;
    String status;

    public ElectronicDevice() {
        this.id = Utils.generateNextId();
    }

    @Override
    public void activate() {
        setStatus(Status.ACTIVE.getStatus());
    }

    @Override
    public void deactivate() {
        setStatus(Status.DEACTIVE.getStatus());
    }

    @Override
    public void turnOn() {
        setStatus(Status.ON.getStatus());
    }

    @Override
    public void turnOff() {
        setStatus(Status.OFF.getStatus());
    }
}
