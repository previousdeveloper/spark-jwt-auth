import DataAccess.ISignupRepository;
import DataAccess.SignupRepositoryImpl;
import RedisProvider.IRedis;
import RedisProvider.RedisImpl;
import Service.IJwtAuthService;
import Service.JwtAuthServiceImpl;
import Util.TimeProviderImpl;
import Util.ITimeProvider;
import Util.IKeyGenerator;
import Util.IKeyGeneratorImpl;
import Validation.ITokenValidator;
import Validation.TokenValidator;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class AppModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(ITimeProvider.class).to(TimeProviderImpl.class).in(Singleton.class);
        bind(IKeyGenerator.class).to(IKeyGeneratorImpl.class).in(Singleton.class);
        bind(IJwtAuthService.class).to(JwtAuthServiceImpl.class).in(Singleton.class);
        bind(ITokenValidator.class).to(TokenValidator.class).in(Singleton.class);
        bind(IRedis.class).to(RedisImpl.class);
        bind(ISignupRepository.class).to(SignupRepositoryImpl.class).asEagerSingleton();
    }
}
