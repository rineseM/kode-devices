package io.kodelabs.devices.controllers;

import io.kodelabs.devices.entities.dtos.CreateModel;
import io.kodelabs.devices.utils.Pagination;
import io.kodelabs.devices.business.Service;
import io.kodelabs.devices.entities.ElectronicDevice;
import io.kodelabs.devices.entities.dtos.UpdateModel;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resource {
  @Inject Service service;

  @GET
  public Uni<List<ElectronicDevice>> getAll(@BeanParam @Valid Pagination pagination) {
    return service.getAll(
        pagination.getPage(),
        pagination.getLimit(),
        pagination.getSortMethod(),
        pagination.getFieldName());
  }

  @GET
  @Path("/{id}")
  public Uni<ElectronicDevice> getById(String id) {
    return service.getById(id);
  }

  @POST
  public Uni<ElectronicDevice> add(@Valid CreateModel createModel) {
    return service.add(createModel);
  }

  @DELETE
  @Path("/{id}")
  public Uni<Void> delete(String id) {
    return service.delete(id);
  }

  @PUT
  @Path("/{id}")
  public Uni<Void> update(@PathParam("id") String id, @Valid UpdateModel updateModel) {
    return service.update(id, updateModel);
  }
}
