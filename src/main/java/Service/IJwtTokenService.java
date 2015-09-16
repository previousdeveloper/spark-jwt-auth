package Service;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public interface IJwtTokenService {

    String tokenGenerator(String username, String password);
}
