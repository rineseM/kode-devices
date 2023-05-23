package io.kodelabs.devices.business;

import io.kodelabs.base.exceptions.custom.BadRequestException;
import io.kodelabs.devices.entities.ElectronicDevice;
import io.kodelabs.devices.entities.dtos.CreateModel;
import io.kodelabs.devices.entities.dtos.UpdateModel;
import io.kodelabs.devices.entities.enums.DeviceFieldName;
import io.kodelabs.devices.entities.enums.SortMethod;
import io.kodelabs.devices.repo.Repository;
import io.kodelabs.devices.utils.Utils;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.kodelabs.base.exceptions.custom.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class Service {
  @Inject Repository repository;

  public Uni<List<ElectronicDevice>> getAll(
      int page, int limit, SortMethod sortMethod, DeviceFieldName fieldName) {
    if (page <= 0 || limit <= 0) {
      return Uni.createFrom().failure(new BadRequestException("Invalid page or limit value"));
    }
    return repository
        .getAll()
        .map(
            electronicDevices -> {
              int start = (page - 1) * limit;
              int end = Math.min(start + limit, electronicDevices.size());
              Comparator<ElectronicDevice> comparator = Utils.getComparator(fieldName);
              if (sortMethod == SortMethod.DESCENDING) {
                comparator = comparator.reversed();
              }
              return electronicDevices.stream()
                  .sorted(comparator)
                  .skip(start)
                  .limit(end - start)
                  .collect(Collectors.toList());
            })
        .onFailure()
        .transform(t -> new BadRequestException("Parameters are not valid"));
  }

  /** fix exceptions, there can be other types of exceptions thrown. (use predicates) */
  public Uni<ElectronicDevice> getById(String id) {
    return repository
        .getById(id)
        .onItem()
        .ifNull()
        .failWith(() -> new NotFoundException("Device not found with id: " + id))
        .onFailure(throwable -> throwable instanceof NullPointerException)
        .transform(t -> new BadRequestException("ID parameter cannot be null"))
        .onFailure(throwable -> throwable instanceof UnsupportedOperationException)
        .transform(t -> new BadRequestException("Unsupported operation on device list"));
  }

  public Uni<ElectronicDevice> add(CreateModel createModel) {
    if (createModel == null) {
      return Uni.createFrom().failure(new BadRequestException("NULLLL"));
    }
    return repository
        .add(Utils.mapCreateDto(new ElectronicDevice(), createModel))
        .onItem()
        .ifNull()
        .failWith(new BadRequestException("Cannot add null device"))
        .onFailure()
        .transform(t -> new BadRequestException("NULl"));
  }

  public Uni<Void> delete(String id) {
    return getById(id)
        .flatMap(electronicDevice -> repository.delete(id))
        .onFailure(throwable -> throwable instanceof NotFoundException)
        .transform(t -> new NotFoundException("Device not found for delete"));
  }

  public Uni<Void> update(String id, UpdateModel updateModel) {
    if (updateModel == null) {
      return Uni.createFrom().failure(new BadRequestException("Update model is null"));
    }
    return getById(id)
        .flatMap(
            electronicDevice -> {
              Utils.mapUpdateDto(electronicDevice, updateModel);
              return repository.update(id, electronicDevice);
            })
        .onFailure(throwable -> throwable instanceof NotFoundException)
        .transform(t -> new NotFoundException("Device not found for update"));
  }
}
