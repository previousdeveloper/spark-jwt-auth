package DataAccess;


public interface ITokenRepository {

    boolean checkClientInfo(String clientId, String clientSecret);

    boolean checkRefreshToken(String refreshToken);
}
