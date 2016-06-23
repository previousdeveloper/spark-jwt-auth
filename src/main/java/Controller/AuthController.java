package Controller;

import Bootstrapper.AppModule;
import DataAccess.ISignupRepository;
import DataAccess.ITokenRepository;
import Model.UserModel;
import Service.IJwtTokenService;
import Util.Constant;
import Validation.ITokenValidator;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Guice;
import com.google.inject.Injector;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class AuthController {

    public AuthController() {


        Injector injector = Guice.createInjector(new AppModule());
        ISignupRepository signupRepository = injector.getInstance(ISignupRepository.class);
        IJwtTokenService jwtAuthService = injector.getInstance(IJwtTokenService.class);
        ITokenValidator tokenValidation = injector.getInstance(ITokenValidator.class);
        ITokenRepository tokenRepository = injector.getInstance(ITokenRepository.class);
        //TODO: DI CONTAINER
        Gson gson = new Gson();

        before("/protected/*", (request, response) -> {

            String xApiToken = request.headers("X-API-TOKEN");


            if (xApiToken != null) {
                boolean result = tokenValidation.validateOauth(xApiToken);

                if (!result) {
                    halt(401, "token expired");
                }

            } else {
                halt(401, "header token not found");
            }

        });

        post("/token", (request, response) -> {

            UserModel userModel = null;
            String token = null;
            String result = null;

            Map<String, String> keyValuePair = null;

            try {

                userModel = gson.fromJson(request.body(), UserModel.class);
                String username = userModel.getUsername();
                String password = userModel.getPassword();
                if (username != null && password != null) {
                    token = jwtAuthService.tokenGenerator(username, password);


                    keyValuePair = new HashMap<>();
                    keyValuePair.put("token", token);

                    result = gson.toJson(keyValuePair);
                }

            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                response.status(400);

                return "INVALID JSON";
            }


            return result;
        });

        post("/register", (request, response) -> {
            UserModel userModel = null;
            Map<String, Object> user;

            try {

                userModel = gson.fromJson(request.body(), UserModel.class);
                signupRepository.saveUser(userModel);
                user = new HashMap<>();
                user.put("Registered:", userModel.getUsername());

            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                response.status(400);

                return "INVALID JSON";
            }

            return gson.toJson(user);
        });

        post("/protected/deneme", (request, response) -> {

            return "SELAM BASARILI GIRIS YAPTIN :)";
        });

        post("/oauth2/token", (request, response) -> {
            OauthRequest oauthRequest = new OauthRequest();
            OauthResponse oauthResponse = new OauthResponse();
            oauthRequest = gson.fromJson(request.body(), OauthRequest.class);

            if (oauthRequest.getGrant_Type().equals("client_credentials")) {
                if (tokenRepository.checkClientInfo(oauthRequest.getClient_Id(), oauthRequest.getClient_Secret())) {
                    oauthResponse.access_token = jwtAuthService.generateAccessToken(oauthRequest.getClient_Id(), oauthRequest.getClient_Secret());
                    oauthResponse.refresh_token = jwtAuthService.getRefreshToken();
                }
            } else if (oauthRequest.getGrant_Type().equals("refresh_token")) {
                if (tokenRepository.checkRefreshToken(oauthRequest.getRefresh_Token())) {
                    oauthResponse.access_token = jwtAuthService.generateAccessToken(oauthRequest.getRefresh_Token());
                    oauthResponse.refresh_token = jwtAuthService.getRefreshToken();
                }
            }

            return gson.toJson(oauthResponse);
        });
    }
}
