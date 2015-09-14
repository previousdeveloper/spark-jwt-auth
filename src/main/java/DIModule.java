import DataAccess.ISignupRepository;
import DataAccess.SignupRepositoryImpl;
import RedisContext.IRedis;
import RedisContext.RedisImpl;
import Service.IJwtAuthService;
import Service.JwtAuthServiceImpl;
import Util.CurrentTimeImpl;
import Util.ICurrentTime;
import Util.IKeyGenerator;
import Util.IKeyGeneratorImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class DIModule extends AbstractModule {
    @Override
    protected void configure() {

        bind(ICurrentTime.class).to(CurrentTimeImpl.class).in(Singleton.class);
        bind(IKeyGenerator.class).to(IKeyGeneratorImpl.class).in(Singleton.class);
        bind(IJwtAuthService.class).to(JwtAuthServiceImpl.class).in(Singleton.class);
        bind(IRedis.class).to(RedisImpl.class);
        bind(ISignupRepository.class).to(SignupRepositoryImpl.class).asEagerSingleton();
    }
}
