package com;

import lombok.Getter;
import lombok.Setter;
import redis.clients.jedis.Jedis;
//Arquivo de inicialização do redis
@Getter
@Setter
public class RedisConfig {
    private Jedis jedis;

    public RedisConfig(){
        this.jedis = new Jedis("localhost", 6379);
    }
}
