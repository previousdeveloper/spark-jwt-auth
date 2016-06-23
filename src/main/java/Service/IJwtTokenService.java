package Service;

public interface IJwtTokenService {

    String tokenGenerator(String username, String password);

    String generateAccessToken(String client_id, String client_secret);

    String getRefreshToken();

    String generateAccessToken(String refreshToken);
}
