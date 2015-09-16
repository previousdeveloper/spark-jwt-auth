package Validation;

import DataAccess.IUserRepository;
import RedisProvider.IRedis;
import Util.Constant;
import Util.ITimeProvider;
import com.google.inject.Inject;
import io.jsonwebtoken.Jwts;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class TokenValidator implements ITokenValidator {


    private IRedis redis;
    private IUserRepository IUserRepository;
    private ITimeProvider timeProvider;

    @Inject
    public TokenValidator(IRedis redis, IUserRepository IUserRepository, ITimeProvider timeProvider) {

        this.redis = redis;

        this.IUserRepository = IUserRepository;
        this.timeProvider = timeProvider;
    }

    public boolean validate(String token) {

        boolean valid = false;


        Object username = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token)
                .getBody().get("username");

        Object password = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token)
                .getBody().get("password");

        Object expireTime = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(token)
                .getBody().get("expireTime");

        Long currentTimeInMilisecond = timeProvider.getCurrentTime();

        if (IUserRepository.getUser().getUsername().equals(username) &&
                IUserRepository.getUser().getPassword().equals(password) &&
                (Long) expireTime > currentTimeInMilisecond) {
            valid = true;
        }

        return valid;
    }
}
