package io.kodelabs.devices.entities.dtos;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateModel {
  @Min(message = "An electronic device costs at least 10$", value = 10)
  double price;

  String status;
}
