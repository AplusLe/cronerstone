package com.mate.starter.redis.config;

import com.mate.starter.redis.core.RedisIdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

/**
 * Created by Kevin on 2022/3/31 14:21
 */
@Slf4j
@AutoConfigureAfter({RedisConfig.class})
@Configuration
public class RedisIdConfig {

    @Bean
    public DefaultRedisScript<List> redisScript() {
        DefaultRedisScript<List> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("script/id_gen.lua")));
        redisScript.setResultType(List.class);
        return redisScript;
    }

    @Bean("redisNoScript")
    public DefaultRedisScript<List> redisNoScript() {
        DefaultRedisScript<List> redisScript = new DefaultRedisScript(buildLuaScript(),List.class);
        return redisScript;
    }

    private String buildLuaScript() {
        //秒 微秒 增长
        return "local incrKey = KEYS[1];" +
                " local step = 1" +
                " local count;" +
                " count = tonumber(redis.call('incrby', incrKey, step));" +
                " local now = redis.call('time');" +
                " return {now[1], now[2], count}";
    }

    @Bean
    @ConditionalOnBean(name = "redisTemplate")
    public RedisIdUtil redisIdUtil() {
        RedisIdUtil redisIdUtil = new RedisIdUtil();
        log.info("[RedisIdUtil]组装完毕");
        return redisIdUtil;
    }
}
