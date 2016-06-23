package Validation;

import DataAccess.ITokenRepository;
import DataAccess.IUserRepository;
import RedisProvider.IRedis;
import Service.IJwtTokenService;
import Util.Constant;
import Util.ITimeProvider;
import com.google.inject.Inject;
import io.jsonwebtoken.Jwts;


public class TokenValidator implements ITokenValidator {


    private IRedis redis;
    private IUserRepository IUserRepository;
    private ITimeProvider timeProvider;
    private ITokenRepository tokenRepository;
    @Inject
    public TokenValidator(IRedis redis, IUserRepository IUserRepository, ITimeProvider timeProvider, ITokenRepository tokenRepository) {

        this.redis = redis;

        this.IUserRepository = IUserRepository;
        this.timeProvider = timeProvider;
        this.tokenRepository = tokenRepository;
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

        if (IUserRepository.getUser().equals(username) &&
                IUserRepository.getUser().getPassword().equals(password) &&
                (Long) expireTime > currentTimeInMilisecond) {
            valid = true;
        }

        return valid;
    }

    @Override
    public boolean validateOauth(String accessToken) {

        boolean valid = false;


        Object expireTime = Jwts.parser().setSigningKey(Constant.JWT_SECRET).parseClaimsJws(accessToken)
                .getBody().get("expireTime");

        Long currentTimeInMillisecond = timeProvider.getCurrentTime();

        //TODO Validation
        if ((Long) expireTime > currentTimeInMillisecond) {
            valid = true;
        }

        return valid;
    }
}
