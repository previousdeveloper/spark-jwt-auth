package DataAccess;

import Model.UserModel;
import RedisProvider.IRedis;
import com.google.gson.Gson;
import com.google.inject.Inject;


public class UserRepositoryImpl implements IUserRepository {

    private IRedis redis;
    private Gson gson;
    private UserModel userModel = null;

    @Inject
    public UserRepositoryImpl(IRedis redis) {
        this.redis = redis;
        gson = new Gson();
    }

    @Override
    public UserModel getUser() {

        String user = redis.get("user");
        this.userModel = gson.fromJson(user, UserModel.class);

        return userModel;
    }
}
