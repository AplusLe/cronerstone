package com.mate.starter.database.handler;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.mate.starter.redis.constant.RedisIdConstant;
import com.mate.starter.redis.core.RedisIdUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author Kevin
 */
@Component
public class RedisIdGenerator implements IdentifierGenerator {


    @Resource
    RedisIdUtil redisIdUtil;

    @Override
    public Number nextId(Object entity) {
        Long id = redisIdUtil.nextId(RedisIdConstant.TABLE_ID_TAG);
        return id;
    }
}
