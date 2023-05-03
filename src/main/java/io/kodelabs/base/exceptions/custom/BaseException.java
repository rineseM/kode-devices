package io.kodelabs.base.exceptions.custom;

import java.util.List;

public class BaseException extends Exception {
  protected String message;

  private List<Field> validations;

  public BaseException(String message) {
    super(message);
    this.message = message;
  }

  public static class Field {
    public String name;
    public String error;
  }

  @Override
  public String getMessage() {
    return this.message;
  }

  public int getstatusCode() {
    return 400;
  }

  public List<Field> getValidations() {
    return validations;
  }
}
