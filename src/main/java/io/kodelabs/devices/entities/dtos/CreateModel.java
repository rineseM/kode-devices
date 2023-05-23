package io.kodelabs.devices.entities.dtos;

import io.kodelabs.devices.entities.enums.Status;
import io.kodelabs.devices.entities.validator.ValidStatus;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateModel {
  @NotBlank(message = "Name of device should not be blank")
  String name;

  @NotNull(message = "Manufacturer cannot be null")
  String manufacturer;

  @Min(message = "An electronic device costs at least 10$", value = 10)
  double price;

  @Min(message = "Devices must be produced after 2000", value = 2000)
  @Max(message = "Devices must beproduced earlier than 2024", value = 2024)
  int year;

  @ValidStatus(enumClass = Status.class, message = "Invalid status")
  String status;
}
