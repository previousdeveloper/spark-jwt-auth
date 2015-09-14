import DataAccess.ISignupRepository;
import Model.UserSignupModel;
import Service.IJwtAuthService;
import Validation.ITokenValidation;
import com.google.inject.Guice;
import com.google.inject.Injector;

import static spark.Spark.post;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class Server {

    public static void main(String[] args) {

        Injector injector = Guice.createInjector(new DIModule());
        ISignupRepository signupRepository = injector.getInstance(ISignupRepository.class);
        IJwtAuthService jwtAuthService = injector.getInstance(IJwtAuthService.class);
        ITokenValidation tokenValidation = injector.getInstance( ITokenValidation.class);


        String s = jwtAuthService.tokenGenerator("deneme", "deneme");
        System.out.println(s);

        boolean b = tokenValidation.validateToken(s);


        System.out.println(b);
        post("/salak", (request, response) -> {
            String author = request.queryParams("deneme");

            UserSignupModel userSignupModel = new UserSignupModel();

            userSignupModel.setPassword(author);

            signupRepository.saveUser(userSignupModel);

            return userSignupModel.getPassword();
        });


        //  System.out.println(jwtAuthServiceImpl.tokenGenerator("gokhan","karadas"));


        //System.out.println(new TokenValidation().validateToken());
    }
}
