package ControllerHandler;

import spark.Request;

public interface AuthControllerHandler {
    String generateResponse(Request request);

    boolean validateRequest(String token);
}
