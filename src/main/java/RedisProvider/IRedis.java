package RedisProvider;

public interface IRedis {

    String set(String key, String value);

    String get(String key);
}
