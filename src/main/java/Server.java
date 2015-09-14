import DataAccess.ISignupRepository;
import Model.UserSignupModel;
import Service.IJwtAuthService;
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


        String s = jwtAuthService.tokenGenerator("gokhan", "karadas");
        System.out.println(s);


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
