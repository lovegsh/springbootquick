package com.gsh.springbootquick.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author GSH
 * @create 2022/5/10 10:15
 */
@SpringBootTest
public class redisTest {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Test
    public void redis(){
        redisTemplate.opsForValue().set("key", "a");
        redisTemplate.opsForHash().put("hash", "a", "b");
        BoundValueOperations<String, String> value = redisTemplate.boundValueOps("key1");
        BoundHashOperations hash = redisTemplate.boundHashOps("hash");
        BoundHashOperations hash2 = redisTemplate.boundHashOps("hash2");

        Boolean key = redisTemplate.hasKey("key");
        System.err.println(key);
        System.err.println(hash.hasKey("a"));
        System.err.println(hash2.hasKey("a"));
        System.err.println(hash.entries());
        System.err.println(hash2.entries());
    }





}
