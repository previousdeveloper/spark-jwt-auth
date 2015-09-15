package Validation;

import RedisProvider.IRedis;
import Util.IKeyGenerator;
import com.google.inject.Inject;
import io.jsonwebtoken.Jwts;

import java.security.Key;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class TokenValidator implements ITokenValidator {

    private IKeyGenerator keyGenerator;
    private IRedis redis;

    @Inject
    public TokenValidator(IKeyGenerator keyGenerator, IRedis redis) {
        this.keyGenerator = keyGenerator;
        this.redis = redis;

    }

    public boolean validate(String token) {

        boolean valid = false;

        Key key = keyGenerator.getKey();

        Object username = Jwts.parser().setSigningKey(new byte[1]).parseClaimsJws(token)
                .getBody().get("username");

        Object password = Jwts.parser().setSigningKey(new byte[1]).parseClaimsJws(token)
                .getHeader().get("password");

        Object expireTime = Jwts.parser().setSigningKey(new byte[1]).parseClaimsJws(token)
                .getHeader().get("expireTime");

        String signUp = redis.get("signUp");

        return valid;
    }
}
