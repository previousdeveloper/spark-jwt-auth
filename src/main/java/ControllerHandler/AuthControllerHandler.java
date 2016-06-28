package ControllerHandler;

import spark.Request;

public interface AuthControllerHandler {

    boolean validateRequest(String token);

    boolean checkClientInfo(String client_id, String client_secret);

    String generateAccessToken(String client_id, String client_secret);

    boolean checkRefreshToken(String refresh_token);

    String generateRefreshToken();

    String generateAccessToken(String refreshToken);
}
