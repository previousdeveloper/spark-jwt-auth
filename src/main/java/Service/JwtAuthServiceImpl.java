package Service;

import Util.Constant;
import Util.ITimeProvider;
import com.google.inject.Inject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthServiceImpl implements IJwtAuthService {


    private ITimeProvider currentTime;

    @Inject
    public JwtAuthServiceImpl(ITimeProvider currentTime) {

        this.currentTime = currentTime;
    }

    public String tokenGenerator(String username, String password) {


        SignatureAlgorithm hs512 = SignatureAlgorithm.HS512;


        String token = Jwts.builder()
                .claim("username", username)
                .claim("password", password)
                .claim("expireTime", currentTime.getCurrentTime())
                .signWith(hs512, Constant.JWT_SECRET)
                .compact();

        return token;
    }
}


