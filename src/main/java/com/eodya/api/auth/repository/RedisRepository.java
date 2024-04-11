package com.eodya.api.auth.repository;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class RedisRepository {

    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations<String, String, Object> hashOperations;

    public RedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public <T> void saveHash(String key, String field, T value, Long duration) {
        hashOperations.put(key, field, value);
        redisTemplate.expire(key, duration, TimeUnit.SECONDS);
    }

    public <T> T findHash(String key, String field) {
        return (T) hashOperations.get(key, field);
    }

    public Boolean existsHash(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    public void deleteHash(String key, String field) {
        hashOperations.delete(key, field);
    }
}
