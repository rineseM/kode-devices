package io.kodelabs.devices.utils;

import io.kodelabs.devices.entities.ElectronicDevice;
import io.kodelabs.devices.entities.dtos.CreateModel;
import io.kodelabs.devices.entities.dtos.UpdateModel;
import io.kodelabs.devices.entities.enums.DeviceFieldName;

import java.util.Comparator;

public class Utils {
  private static String Id = "10000";

  public static String generateNextId() {
    int number = Integer.valueOf(Id);
    number++;
    Id = String.valueOf(number);
    return Id;
  }

  public static Comparator<ElectronicDevice> getComparator(DeviceFieldName fieldName) {
    switch (fieldName) {
      case NAME:
        return Comparator.comparing(ElectronicDevice::getName);
      case STATUS:
        return Comparator.comparing(ElectronicDevice::getStatus);
      case YEAR:
        return Comparator.comparing(ElectronicDevice::getYear);
      case MANUFACTURER:
        return Comparator.comparing(ElectronicDevice::getManufacturer);
      case PRICE:
        return Comparator.comparing(ElectronicDevice::getPrice);
      default:
        return Comparator.comparing(ElectronicDevice::getId);
    }
  }

  public static ElectronicDevice mapCreateDto(
      ElectronicDevice electronicDevice, CreateModel createModel) {
    electronicDevice.setPrice(createModel.getPrice());
    electronicDevice.setStatus(createModel.getStatus());
    electronicDevice.setManufacturer(createModel.getManufacturer());
    electronicDevice.setYear(createModel.getYear());
    electronicDevice.setName(createModel.getName());
    return electronicDevice;
  }

  public static ElectronicDevice mapUpdateDto(
      ElectronicDevice electronicDevice, UpdateModel updateModel) {
    electronicDevice.setPrice(updateModel.getPrice());
    electronicDevice.setStatus(updateModel.getStatus());
    return electronicDevice;
  }
}
