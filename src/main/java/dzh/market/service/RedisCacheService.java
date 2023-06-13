package dzh.market.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisCacheService
{
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    public void saveSession() {

    }
}
