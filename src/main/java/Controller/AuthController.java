package Controller;

import ControllerHandler.AuthControllerHandler;
import DataAccess.ISignupRepository;
import Helper.JsonHelper;
import Model.UserModel;
import com.google.gson.JsonSyntaxException;
import com.google.inject.Inject;

import java.util.HashMap;
import java.util.Map;

import static spark.Spark.*;


public class AuthController {

    private ISignupRepository signupRepository;
    private AuthControllerHandler authControllerHandler;
    private JsonHelper jsonHelper;

    @Inject
    public AuthController(ISignupRepository signupRepository, AuthControllerHandler authControllerHandler, JsonHelper jsonHelper) {
        this.signupRepository = signupRepository;
        this.authControllerHandler = authControllerHandler;
        this.jsonHelper = jsonHelper;


//        ISignupRepository signupRepository = injector.getInstance(ISignupRepository.class);
//        AuthControllerHandler authControllerHandler = injector.getInstance(AuthControllerHandler.class);
//        JsonHelper jsonHelper = injector.getInstance(GsonHelper.class);
        //TODO: DI CONTAINER

        before("/protected/*", (request, response) -> {

            String xApiToken = request.headers("X-API-TOKEN");

            if (xApiToken != null) {
                boolean result =  this.authControllerHandler.validateRequest(xApiToken);

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

                userModel = jsonHelper.fromJson(request.body(), UserModel.class);
                this.signupRepository.saveUser(userModel);
                user = new HashMap<>();
                user.put("Registered:", userModel.getUsername());

            } catch (JsonSyntaxException e) {
                e.printStackTrace();
                response.status(400);

                return "INVALID JSON";
            }

            return jsonHelper.toJson(user);
        });

        post("/protected/deneme", (request, response) -> {

            return "SELAM BASARILI GIRIS YAPTIN :)";
        });

        post("/oauth2/token", (request, response) -> {

            String result  = this.authControllerHandler.generateResponse(request);

            return result;
        });
    }

}
