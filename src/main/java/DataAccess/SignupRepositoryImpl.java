package DataAccess;

import Model.UserModel;
import RedisProvider.IRedis;
import Util.JsonTransformer;
import com.google.inject.Inject;

public class SignupRepositoryImpl implements ISignupRepository {

    private IRedis redis;

    @Inject
    public SignupRepositoryImpl(IRedis redis) {

        this.redis = redis;
    }

    public void saveUser(UserModel userModel) {

        String value = null;

        try {
            value = new JsonTransformer().render(userModel);
            redis.set("user", value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
