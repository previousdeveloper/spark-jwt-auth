package Controller;

import Bootstrapper.AppModule;
import ControllerHandler.AuthControllerHandler;
import DataAccess.ISignupRepository;
import Helper.GsonHelper;
import Helper.JsonHelper;
import Model.OauthRequest;
import Model.OauthResponse;
import Model.UserModel;
import Service.IJwtTokenService;
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
        AuthControllerHandler authControllerHandler = injector.getInstance(AuthControllerHandler.class);
        JsonHelper gsonHelper = injector.getInstance(GsonHelper.class);
        //TODO: DI CONTAINER
        Gson gson = new Gson();

        before("/protected/*", (request, response) -> {

            String xApiToken = request.headers("X-API-TOKEN");

            if (xApiToken != null) {
                boolean result =  authControllerHandler.validateRequest(xApiToken);

                if (!result) {
                    halt(401, "token expired");
                }

            } else {
                halt(401, "header token not found");
            }

        });

        //OLD version
//        post("/token", (request, response) -> {
//
//            UserModel userModel = null;
//            String token = null;
//            String result = null;
//
//            Map<String, String> keyValuePair = null;
//
//            try {
//
//                userModel = gson.fromJson(request.body(), UserModel.class);
//                String username = userModel.getUsername();
//                String password = userModel.getPassword();
//                if (username != null && password != null) {
//                    token = jwtAuthService.tokenGenerator(username, password);
//
//
//                    keyValuePair = new HashMap<>();
//                    keyValuePair.put("token", token);
//
//                    result = gson.toJson(keyValuePair);
//                }
//
//            } catch (JsonSyntaxException e) {
//                e.printStackTrace();
//                response.status(400);
//
//                return "INVALID JSON";
//            }
//
//
//            return result;
//        });

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
            OauthRequest oauthRequest = gsonHelper.fromJson(request.body(),OauthRequest.class);
            OauthResponse oauthResponse  = authControllerHandler.generateToken(oauthRequest);
            String result = gsonHelper.toJson(oauthResponse);

            return result;
        });
    }

}
