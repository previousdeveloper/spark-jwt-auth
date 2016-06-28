package ControllerHandler;

import DataAccess.ITokenRepository;
import Helper.JsonHelper;
import Service.IJwtTokenService;
import Validation.ITokenValidator;
import com.google.inject.Inject;

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
    public boolean validateRequest(String token) {

        boolean result = tokenValidator.validateOauth(token);
        return result;
    }

    @Override
    public boolean checkClientInfo(String client_id, String client_secret) {
        return false;
    }

    @Override
    public String generateAccessToken(String client_id, String client_secret) {
        return null;
    }

    @Override
    public boolean checkRefreshToken(String refresh_token) {
        return false;
    }

    @Override
    public String generateRefreshToken() {
        return null;
    }
}
