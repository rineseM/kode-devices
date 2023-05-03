package io.kodelabs.devices.repo;

import io.kodelabs.devices.entities.ElectronicDevice;
import io.kodelabs.devices.entities.dtos.UpdateModel;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import io.kodelabs.base.exceptions.custom.BadRequestException;
import io.kodelabs.base.exceptions.custom.NotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class Repository {
  private Map<String, ElectronicDevice> electronicDevices = new HashMap<>();

  public Uni<List<ElectronicDevice>> getAll() {
    return Uni.createFrom().item(electronicDevices.values().stream().toList());
  }

  public Uni<ElectronicDevice> getById(String id) {
    return Uni.createFrom()
        .emitter(
            uniEmitter -> {
              if (electronicDevices.containsKey(id)) {
                uniEmitter.complete(electronicDevices.get(id));
              }
              uniEmitter.fail(new NotFoundException(""));
            });
  }

  public Uni<ElectronicDevice> add(ElectronicDevice device) {
    return Uni.createFrom()
        .emitter(
            uniEmitter -> {
              if (device != null) {
                electronicDevices.put(device.getId(), device);
                uniEmitter.complete(device);
              }
              uniEmitter.fail(new BadRequestException(""));
              //              Runnable addDevice = () -> electronicDevices.put(device.getId(),
              // device);
              //                try {
              //                    addDevice.run();
              //                    uniEmitter.complete(device);
              //                } catch (Exception e) {
              //                    uniEmitter.fail(e);
              //                }
            });
  }

  public Uni<Void> delete(String id) {
    return Uni.createFrom()
        .emitter(
            uniEmitter -> {
              if (electronicDevices.containsKey(id)) {
                electronicDevices.remove(id);
                uniEmitter.complete(null);
                return;
              }
              uniEmitter.fail(new NotFoundException(""));
            });
  }

  public Uni<Void> update(String id, ElectronicDevice electronicDevice) {
    return Uni.createFrom()
        .emitter(
            uniEmitter -> {
              electronicDevices.put(id, electronicDevice);
              uniEmitter.complete(null);
            });
  }
}
