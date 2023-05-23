package io.kodelabs.base.exceptions.custom;

public class ConflictException extends BaseException {

  public ConflictException(String message) {
    super(message);
  }

  @Override
  public int getstatusCode() {
    return 409;
  }
}
