package ControllerHandler;

import Model.OauthRequest;
import Model.OauthResponse;
import spark.Request;

public interface AuthControllerHandler {
    OauthResponse generateToken(OauthRequest request);

    OauthRequest mapOauthRequest(Request request);

    boolean validateRequest(String token);
}
