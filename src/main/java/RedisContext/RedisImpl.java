package RedisContext;

import redis.clients.jedis.Jedis;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public class RedisImpl implements IRedis {

    public Jedis jedis() {
        Jedis jedisConnector = new Jedis("localhost");

        return jedisConnector;
    }
}
