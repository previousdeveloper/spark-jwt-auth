package DataAccess;

import Model.UserSignupModel;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public interface ISignupRepository {

    void saveUser(UserSignupModel userSignupModel);

}
