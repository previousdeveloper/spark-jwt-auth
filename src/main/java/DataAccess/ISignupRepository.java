package DataAccess;

import Model.UserModel;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public interface ISignupRepository {

    void saveUser(UserModel userModel);

}
