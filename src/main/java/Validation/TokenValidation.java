package Validation;

import Util.IKeyGenerator;
import com.google.inject.Inject;
import io.jsonwebtoken.Jwts;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class TokenValidation {

    private IKeyGenerator keyGenerator;

    @Inject
    public TokenValidation(IKeyGenerator keyGenerator) {
        this.keyGenerator = keyGenerator;
    }

    public Object validateToken(String token) {


        Object username = Jwts.parser().setSigningKey(keyGenerator.getKey()).parseClaimsJws(token)
                .getHeader().get("username");

        Object password = Jwts.parser().setSigningKey(keyGenerator.getKey()).parseClaimsJws(token)
                .getHeader().get("password");

        Object expireTime = Jwts.parser().setSigningKey(keyGenerator.getKey()).parseClaimsJws(token)
                .getHeader().get("expireTime");

        return expireTime;
    }
}
