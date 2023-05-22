package io.kodelabs.devices.entities.dtos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class CreateModel {
    String name;
    String manufacturer;
    double price;
    int year;
    String status;
}
