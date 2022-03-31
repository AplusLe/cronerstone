package com.mate.starter.redis.core;

import com.mate.starter.redis.constant.RedisIdConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * redis分布式id工具类
 * @author cfw
 */
@Slf4j
public class RedisIdUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    DefaultRedisScript<List> redisScript;

    /**
     * @param tag 为哪一类服务生成ID
     * @return 返回long类型
     */
    public Long nextId(String tag) {
        List<String> keys =  Collections.singletonList(RedisIdConstant.DIS_ID_PREFIX + tag + RedisIdConstant.DIS_ID_SUFFIX);
        List<Long> result = redisTemplate.execute(redisScript, keys);
        assert result != null;
        return buildId(result.get(0), result.get(1), result.get(2), result.get(3));
    }

    /**
     * @param tag 唯一标签
     * @return 返回string类型
     */
    public String nextIdStr(String tag) {
        return nextId(tag).toString();
    }

    private static long buildId(long second, long microSecond, long shardId, long seq) {
        long milliSecond = (second * 1000 + microSecond / 1000);
        return (milliSecond << (12 + 10)) + (shardId << 10) + seq;
    }
}
