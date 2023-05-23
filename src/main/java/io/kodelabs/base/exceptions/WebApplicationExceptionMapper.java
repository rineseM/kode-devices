package io.kodelabs.base.exceptions;

import io.kodelabs.base.exceptions.custom.BaseException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;

@Provider
public class WebApplicationExceptionMapper {
  @ServerExceptionMapper
  public Response toResponse(BaseException e) {
    int statusCode = e.getstatusCode();
    String message = e.getMessage();
    ErrorResponse errorResponse = new ErrorResponse(message, statusCode);
    return Response.status(statusCode).entity(errorResponse).build();
  }
}
