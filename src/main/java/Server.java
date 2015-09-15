import DataAccess.ISignupRepository;
import Model.UserModel;
import Service.IJwtAuthService;
import Validation.ITokenValidator;
import com.google.inject.Guice;
import com.google.inject.Injector;

import static spark.Spark.post;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class Server {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new AppModule());
        ISignupRepository signupRepository = injector.getInstance(ISignupRepository.class);
        IJwtAuthService jwtAuthService = injector.getInstance(IJwtAuthService.class);
        ITokenValidator tokenValidation = injector.getInstance( ITokenValidator.class);


        String s = jwtAuthService.tokenGenerator("deneme", "deneme");
        System.out.println(s);

        boolean b = tokenValidation.validate(s);


        System.out.println(b);
        post("/salak", (request, response) -> {
            String author = request.queryParams("deneme");

            UserModel userModel = new UserModel();

            userModel.setPassword(author);

            signupRepository.saveUser(userModel);

            return userModel.getPassword();
        });


        //  System.out.println(jwtAuthServiceImpl.tokenGenerator("gokhan","karadas"));


        //System.out.println(new TokenValidator().validate());
    }
}
