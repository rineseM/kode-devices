package io.kodelabs.devices.business;

import io.kodelabs.base.exceptions.custom.BadRequestException;
import io.kodelabs.devices.entities.ElectronicDevice;
import io.kodelabs.devices.entities.dtos.UpdateModel;
import io.kodelabs.devices.entities.enums.SortMethod;
import io.kodelabs.devices.repo.Repository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import io.kodelabs.base.exceptions.custom.NotFoundException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class Service {
    @Inject
    Repository repository;

    public Uni<List<ElectronicDevice>> getAll(int page, int limit, SortMethod sortMethod, String field) {
        return repository.getAll()
                .map(electronicDevices -> {
                    int start = (page - 1) * limit;
                    int end = Math.min(start + limit, electronicDevices.size());
                    if (sortMethod == SortMethod.DESCENDING){
                        Comparator<ElectronicDevice> comparator = Comparator.comparing(ElectronicDevice::getPrice);
                        return electronicDevices.stream()
                                .sorted(comparator.reversed())
                                .skip(start)
                                .limit(end - start)
                                .collect(Collectors.toList());
                    }
                    return electronicDevices.subList(start, end);
                })
                .onFailure()
                .transform(t -> new BadRequestException("Parameters are not valid"));
    }


    /**
     * fix exceptions, there can be other types of exceptions thrown. (use predicates)
     */
    public Uni<ElectronicDevice> getById(String id) {
        return repository.getById(id).onItem().ifNull().failWith(new NotFoundException("Device not found with id: " + id));
    }

    public Uni<ElectronicDevice> add(ElectronicDevice device) {
        return repository
                .add(device)
                .onItem()
                .ifNull()
                .failWith(new BadRequestException("Cannot add null device"));
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
                .flatMap(electronicDevice -> {
                    electronicDevice.setPrice(updateModel.getPrice());
                    electronicDevice.setStatus(updateModel.getStatus());
                    return repository.update(id, electronicDevice);
                })
                .onFailure(throwable -> throwable instanceof NotFoundException)
                .transform(t -> new NotFoundException("Device not found for update"));
    }
}
