package Controller;

import ControllerHandler.AuthControllerHandler;
import DataAccess.ISignupRepository;
import Helper.JsonHelper;
import Model.BaseOauthRequest;
import Model.OauthResponse;
import Model.OauthRequest;
import Validation.ITokenValidator;
import com.google.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;

import static spark.Spark.*;


public class AuthController {

    private ISignupRepository signupRepository;
    private AuthControllerHandler authControllerHandler;
    private JsonHelper jsonHelper;
    private ITokenValidator tokenValidator;

    @Inject
    public AuthController(ISignupRepository signupRepository, AuthControllerHandler authControllerHandler, JsonHelper jsonHelper, ITokenValidator tokenValidator) {
        this.signupRepository = signupRepository;
        this.authControllerHandler = authControllerHandler;
        this.jsonHelper = jsonHelper;
        this.tokenValidator = tokenValidator;


        before("/protected/*", (request, response) -> {

            String xApiToken = request.headers("X-API-TOKEN");

            if (xApiToken != null) {
                boolean result = this.tokenValidator.validateOauth(xApiToken);

                if (!result) {
                    halt(401, "token expired");
                }

            } else {
                halt(401, "header token not found");
            }

        });

        before("/oauth2/token", ((request, response) -> {
            String body = request.body();

            if (body != null) {

                boolean isValid = tokenValidator.validateTokenRequest();

                if (!isValid) {
                    halt(HttpStatus.BAD_REQUEST_400, "Invalid info");
                }
            } else {
                halt(HttpStatus.BAD_REQUEST_400, "Invalid request");
            }

        }));


        post("/oauth2/token", (request, response) -> {

            OauthRequest oauthRequest = jsonHelper.fromJson(request.body(), OauthRequest.class);
            OauthResponse oauthResponse = new OauthResponse();

            if (oauthRequest.getGrantType().equals("client_credentials")) {
                if (authControllerHandler.checkClientInfo(oauthRequest.getClientId(), oauthRequest.getClientSecret())) {
                    oauthResponse.setAccessToken(authControllerHandler.generateAccessToken(oauthRequest.getClientId(), oauthRequest.getClientSecret()));
                    oauthResponse.setRefreshToken(authControllerHandler.generateRefreshToken());
                }
            } else if (oauthRequest.getGrantType().equals("refresh_token")) {
                if (authControllerHandler.checkRefreshToken(oauthRequest.getRefreshToken())) {
                    oauthResponse.setAccessToken(authControllerHandler.generateAccessToken(oauthRequest.getRefreshToken()));
                    oauthResponse.setRefreshToken(authControllerHandler.generateRefreshToken());
                }
            }

            String result = jsonHelper.toJson(oauthResponse);

            return result;
        });

//        post("/register", (request, response) -> {
//            UserModel userModel = null;
//            Map<String, Object> user;
//
//            try {
//
//                userModel = jsonHelper.fromJson(request.body(), UserModel.class);
//                this.signupRepository.saveUser(userModel);
//                user = new HashMap<>();
//                user.put("Registered:", userModel.getUsername());
//
//            } catch (JsonSyntaxException e) {
//                e.printStackTrace();
//                response.status(400);
//
//                return "INVALID JSON";
//            }
//
//            return jsonHelper.toJson(user);
//        });
//
//        post("/protected/deneme", (request, response) -> {
//
//            return "SELAM BASARILI GIRIS YAPTIN :)";
//        });

    }

}
