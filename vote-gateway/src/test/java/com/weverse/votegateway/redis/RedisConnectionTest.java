package com.weverse.votegateway.redis;


import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.ReactiveRedisTemplate;

@SpringBootTest
public class RedisConnectionTest {

    @Autowired
    private ReactiveRedisTemplate<String, String> redisTemplate;


    @PostConstruct
    public void testRedisConnection() {
        redisTemplate.opsForValue().set("testKey", "testValue").subscribe();

        redisTemplate.opsForValue().get("testKey").subscribe(value -> {
            System.out.println("Redis testKey value: " + value);
        });
    }

    @Test
    void contextLoads() {}

}
