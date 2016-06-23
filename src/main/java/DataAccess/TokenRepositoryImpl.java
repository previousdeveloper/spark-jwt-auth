package DataAccess;


import com.google.inject.Inject;

public class TokenRepositoryImpl implements ITokenRepository {

    @Inject
    public TokenRepositoryImpl() {
    }

    @Override
    public boolean checkClientInfo(String clientId, String clientSecret) {

        String CLIENT_ID = "a51417bf-9264-4791-ad0a-d4c27a91c0b0";
        String CLIENT_SECRET = "e6b45bdb-340c-4ecd-9a6e-c11efdc9662f";
        boolean result = clientId.equals(CLIENT_ID) && clientSecret.equals(CLIENT_SECRET);
        return result;
    }

    @Override
    public boolean checkRefreshToken(String refreshToken) {
        return "d29af487-7c07-4030-b21c-dbb850013ee1".equals(refreshToken);
    }
}
