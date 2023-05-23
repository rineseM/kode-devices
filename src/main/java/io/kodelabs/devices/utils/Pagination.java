package io.kodelabs.devices.utils;

import io.kodelabs.devices.entities.enums.DeviceFieldName;
import io.kodelabs.devices.entities.enums.SortMethod;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.QueryParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Pagination {
  @QueryParam("limit")
  protected int limit;

  @QueryParam("page")
  protected int page;

  @QueryParam("sort")
  @NotNull
  protected SortMethod sortMethod;

  @NotNull
  @QueryParam("field")
  protected DeviceFieldName fieldName;
}
