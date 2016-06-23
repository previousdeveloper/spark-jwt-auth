package Service;

import Util.Constant;
import Util.ITimeProvider;
import com.google.inject.Inject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtTokenServiceImpl implements IJwtTokenService {


    private ITimeProvider currentTime;

    @Inject
    public JwtTokenServiceImpl(ITimeProvider currentTime) {

        this.currentTime = currentTime;
    }

    public String tokenGenerator(String username, String password) {


        SignatureAlgorithm hs512 = SignatureAlgorithm.HS512;


        String token = Jwts.builder()
                .claim("username", username)
                .claim("password", password)
                        //Todo:Config
                .claim("expireTime", currentTime.getCurrentTime() + Constant.TOKEN_TIMEOUT_INMILISECOND)
                .signWith(hs512, Constant.JWT_SECRET)
                .compact();

        return token;
    }

    @Override
    public String generateAccessToken(String client_id, String client_secret) {
        SignatureAlgorithm hs512 = SignatureAlgorithm.HS512;


        String token = Jwts.builder()
                .claim("clientId", client_id)
                .claim("clientSecret", client_secret)
                //Todo:Config
                .claim("expireTime", currentTime.getCurrentTime() + Constant.TOKEN_TIMEOUT_INMILISECOND)
                .signWith(hs512, Constant.JWT_SECRET)
                .compact();

        return token;
    }

    @Override
    public String getRefreshToken() {
        return "d29af487-7c07-4030-b21c-dbb850013ee1";
    }

    @Override
    public String generateAccessToken(String refreshToken) {
        SignatureAlgorithm hs512 = SignatureAlgorithm.HS512;

        String token = Jwts.builder()
                .claim("refreshToken", refreshToken)
                //Todo:Config
                .claim("expireTime", currentTime.getCurrentTime() + Constant.TOKEN_TIMEOUT_INMILISECOND)
                .signWith(hs512, Constant.JWT_SECRET)
                .compact();

        return token;
    }
}


