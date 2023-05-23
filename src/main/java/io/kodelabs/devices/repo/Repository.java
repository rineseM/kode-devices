package io.kodelabs.devices.repo;

import io.kodelabs.devices.entities.ElectronicDevice;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
              uniEmitter.complete(electronicDevices.get(id));
            });
  }

  public Uni<ElectronicDevice> add(ElectronicDevice device) {
    return Uni.createFrom()
        .emitter(
            uniEmitter -> {
              electronicDevices.put(device.getId(), device);
              uniEmitter.complete(device);
            });
  }

  public Uni<Void> delete(String id) {
    return Uni.createFrom()
        .emitter(
            uniEmitter -> {
              electronicDevices.remove(id);
              uniEmitter.complete(null);
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
