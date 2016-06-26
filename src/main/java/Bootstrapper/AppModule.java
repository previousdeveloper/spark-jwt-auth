package Bootstrapper;

import ControllerHandler.AuthControllerHandler;
import ControllerHandler.AuthControllerHandlerImpl;
import DataAccess.*;
import Helper.GsonHelper;
import Helper.JsonHelper;
import RedisProvider.IRedis;
import RedisProvider.RedisImpl;
import Service.IJwtTokenService;
import Service.JwtTokenServiceImpl;
import Util.TimeProviderImpl;
import Util.ITimeProvider;
import Util.IKeyGenerator;
import Util.IKeyGeneratorImpl;
import Validation.ITokenValidator;
import Validation.TokenValidator;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

public class AppModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(ITimeProvider.class).to(TimeProviderImpl.class).in(Singleton.class);
        bind(IKeyGenerator.class).to(IKeyGeneratorImpl.class).in(Singleton.class);
        bind(IJwtTokenService.class).to(JwtTokenServiceImpl.class).in(Singleton.class);
        bind(ITokenValidator.class).to(TokenValidator.class).in(Singleton.class);
        bind(IRedis.class).to(RedisImpl.class);
        bind(ISignupRepository.class).to(SignupRepositoryImpl.class).asEagerSingleton();
        bind(IUserRepository.class).to(UserRepositoryImpl.class).asEagerSingleton();
        bind(ITokenRepository.class).to(TokenRepositoryImpl.class).asEagerSingleton();
        bind(AuthControllerHandler.class).to(AuthControllerHandlerImpl.class).asEagerSingleton();
        bind(JsonHelper.class).to(GsonHelper.class).asEagerSingleton();
    }
}
