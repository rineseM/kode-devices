package io.kodelabs.devices.business;

import io.kodelabs.devices.entities.ElectronicDevice;
import io.kodelabs.devices.entities.dtos.UpdateModel;
import io.kodelabs.devices.repo.Repository;
import io.quarkus.runtime.Startup;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import io.kodelabs.base.exceptions.custom.NotFoundException;
import java.util.List;

@ApplicationScoped
public class Service {
  @Inject Repository repository;

  public Uni<List<ElectronicDevice>> getAll() {
    return repository.getAll();
  }
  /** fix exceptions, there can be other types of exceptions thrown. (use predicates) */
  public Uni<ElectronicDevice> getById(String id) {
    return repository
        .getById(id)
        .onFailure()
        .transform(t -> new NotFoundException("Device not found with id: " + id));
  }

  public Uni<ElectronicDevice> add(ElectronicDevice device) {
    return repository
        .add(device)
        .onFailure()
        .transform(
            t -> new BadRequestException("Device cannot be added due to bad request from client"));
  }

  public Uni<Void> delete(String id) {
    return repository
        .delete(id)
        .onFailure()
        .transform(t -> new NotFoundException("Device not found for delete"));
  }

  public Uni<Void> update(String id, UpdateModel updateModel) {
    return getById(id)
        .flatMap(
            electronicDevice -> {
              electronicDevice.setPrice(updateModel.getPrice());
              electronicDevice.setStatus(updateModel.getStatus());
              return repository.update(id, electronicDevice);
            })
        .onFailure(throwable -> throwable instanceof NotFoundException)
        .transform(t -> new NotFoundException("Device not found for update"));
  }
}
