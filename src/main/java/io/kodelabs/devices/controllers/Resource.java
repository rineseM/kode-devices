package io.kodelabs.devices.controllers;

import io.kodelabs.devices.business.Service;
import io.kodelabs.devices.entities.ElectronicDevice;
import io.kodelabs.devices.entities.dtos.UpdateModel;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/devices")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Resource {
  @Inject Service service;

  @GET
  /** Add pagination: page, limit, sort(optional) */
  public Uni<List<ElectronicDevice>> getAll() {
    return service.getAll();
  }

  @GET
  @Path("/{id}")
  public Uni<ElectronicDevice> getById(String id) {
    return service.getById(id);
  }

  @POST
  public Uni<ElectronicDevice> add(ElectronicDevice device) {
    return service.add(device);
  }

  @DELETE
  @Path("/{id}")
  public Uni<Void> delete(String id) {
    return service.delete(id);
  }

  @PUT
  @Path("/{id}")
  public Uni<Void> update(@PathParam("id") String id, UpdateModel updateModel) {
    return service.update(id, updateModel);
  }
}
