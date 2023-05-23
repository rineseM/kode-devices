package io.kodelabs.base.exceptions;

public class ErrorResponse {
  private final String message;
  private int statusCode;

  public ErrorResponse(String message) {
    this.message = message;
  }

  public ErrorResponse(String message, int statusCode) {
    this.message = message;
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public int getStatusCode() {
    return statusCode;
  }
}
