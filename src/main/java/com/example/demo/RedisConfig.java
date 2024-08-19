package com.example.demo;

import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.Jedis;

@Getter
@Setter
public class RedisConfig {
    private Jedis jedis;

    public RedisConfig(){
        this.jedis = new Jedis("localhost", 6379);
    }
}
