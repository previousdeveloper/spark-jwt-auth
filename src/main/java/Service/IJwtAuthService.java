package Service;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public interface IJwtAuthService {

    String tokenGenerator(String username, String password);
}
