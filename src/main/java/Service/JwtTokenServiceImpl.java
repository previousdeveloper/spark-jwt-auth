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
}


