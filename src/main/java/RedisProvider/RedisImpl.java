package RedisProvider;

import redis.clients.jedis.Jedis;


public class RedisImpl implements IRedis {

    Jedis jedisConnector = new Jedis("localhost");

    @Override
    public String set(String key, String value) {
        return jedisConnector.set(key, value);
    }


    @Override
    public String get(String key) {
        return jedisConnector.get(key);
    }
}
