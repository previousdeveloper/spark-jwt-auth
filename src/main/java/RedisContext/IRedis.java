package RedisContext;

import redis.clients.jedis.Jedis;

/**
 * Created by previousdeveloper on 14.09.2015.
 */
public interface IRedis {

    Jedis jedis();
}
