package Service;

import Util.ICurrentTime;
import Util.IKeyGenerator;
import com.google.inject.Inject;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JwtAuthServiceImpl implements IJwtAuthService {

    private ICurrentTime currentTime;
    private IKeyGenerator keyGenerator;

    @Inject
    public JwtAuthServiceImpl(ICurrentTime currentTime, IKeyGenerator keyGenerator) {

        this.currentTime = currentTime;
        this.keyGenerator = keyGenerator;
    }

    public String tokenGenerator(String username, String password) {


        SignatureAlgorithm hs512 = SignatureAlgorithm.HS512;

        String token = Jwts.builder()
                .claim("username", username)
                .claim("password", password)
                .claim("expireTime", currentTime.getCurrentTime())
                .signWith(hs512, keyGenerator.getKey())
                .compact();

        return token;
    }
}


