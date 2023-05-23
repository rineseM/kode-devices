package io.kodelabs.base.exceptions.custom;

public class NotFoundException extends BaseException {

  public NotFoundException(String message) {
    super(message);
  }

  @Override
  public int getstatusCode() {
    return 404;
  }
}
