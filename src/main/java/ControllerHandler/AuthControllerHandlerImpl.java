package ControllerHandler;

import Model.OauthRequest;
import Model.OauthResponse;
import DataAccess.ITokenRepository;
import Helper.JsonHelper;
import Service.IJwtTokenService;
import Validation.ITokenValidator;
import com.google.inject.Inject;
import spark.Request;

public class AuthControllerHandlerImpl implements AuthControllerHandler {

    private JsonHelper gsonHelper;
    private ITokenRepository tokenRepository;
    private IJwtTokenService jwtTokenService;
    private ITokenValidator tokenValidator;

    @Inject
    public AuthControllerHandlerImpl(JsonHelper gsonHelper, ITokenRepository tokenRepository, IJwtTokenService jwtTokenService, ITokenValidator tokenValidator) {
        this.gsonHelper = gsonHelper;
        this.tokenRepository = tokenRepository;
        this.jwtTokenService = jwtTokenService;
        this.tokenValidator = tokenValidator;
    }

    @Override
    public OauthResponse generateToken(OauthRequest oauthRequest) {


        OauthResponse result = new OauthResponse();
        if (oauthRequest.getGrant_Type().equals("client_credentials")) {
            if (tokenRepository.checkClientInfo(oauthRequest.getClient_Id(), oauthRequest.getClient_Secret())) {
                result.access_token = jwtTokenService.generateAccessToken(oauthRequest.getClient_Id(), oauthRequest.getClient_Secret());
                result.refresh_token = jwtTokenService.getRefreshToken();
            }
        } else if (oauthRequest.getGrant_Type().equals("refresh_token")) {
            if (tokenRepository.checkRefreshToken(oauthRequest.getRefresh_Token())) {
                result.access_token = jwtTokenService.generateAccessToken(oauthRequest.getRefresh_Token());
                result.refresh_token = jwtTokenService.getRefreshToken();
            }
        }
        return result;
    }

    @Override
    public OauthRequest mapOauthRequest(Request request) {

        OauthRequest result = gsonHelper.fromJson(request.body(), OauthRequest.class);
        return result;
    }

    @Override
    public boolean validateRequest(String token) {

        boolean result = tokenValidator.validateOauth(token);
        return result;
    }
}
