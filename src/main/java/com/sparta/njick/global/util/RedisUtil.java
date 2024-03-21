package com.sparta.njick.global.util;

import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class RedisUtil {
    private final RedissonClient redissonClient;
    private final RedisTemplate<String, Object> redisTemplate;

    public String getData(String key) {
        return (String)redisTemplate.opsForValue().get(key);
    }

    public boolean existData(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void setDataExpire(String key, String value, long duration) {
        RLock lock = redissonClient.getLock("key");
        System.out.println(lock.isLocked());
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        Duration expireDuration = Duration.ofSeconds(duration);
        valueOperations.set(key, value, expireDuration);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
}
