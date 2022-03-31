package com.mate.starter.redis.core;

import com.mate.starter.redis.constant.RedisIdConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * redis生成编号工具类
 *
 * @author cfw
 */
@Slf4j
public class RedisNoUtil {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    DefaultRedisScript<List> redisNoScript;

    private final List<String> keys = Collections.singletonList(RedisIdConstant.LONG_NO);

    /**
     * 获取业务编号
     *
     * @param noEnum
     * @return
     */
//    public String getBizNo(NoEnum noEnum) {
//        if (noEnum.getDatePattern().equals(RedisIdConstant.DATETIME_FORMATTER_NANO)) {
//            String dateTime = genLongNoFromRedis();
//            String s = noEnum.getPrefix() + dateTime;
//            return NoSerialUtil.completionRandom(s, noEnum);
//        }
//        String formNoPrefix = NoSerialUtil.getFormNoPrefix(noEnum);
//        String cacheKey = NoSerialUtil.getCacheKey(noEnum);
//        Long incr = redisService.incr(cacheKey, 1L);
//        redisService.expire(cacheKey, RedisIdConstant.EXPIRE);
//        return NoSerialUtil.completionSerial(formNoPrefix, incr, noEnum);
//    }
//
//
//    public String getBizNo(String prefix, String datePattern, Integer randomLength, Integer totalLength) {
//        if (datePattern.equals(RedisIdConstant.DATETIME_FORMATTER_NANO)) {
//            String dateTime = genLongNoFromRedis();
//            String s = prefix + dateTime;
//            return NoSerialUtil.completionRandom(s, randomLength);
//        }
//        String formNoPrefix = NoSerialUtil.getFormNoPrefix(prefix, datePattern);
//        String cacheKey = NoSerialUtil.getCacheKey(prefix);
//        Long incr = redisService.incr(cacheKey, 1L);
//        redisService.expire(cacheKey, RedisIdConstant.EXPIRE);
//        return NoSerialUtil.completionSerial(formNoPrefix, incr, totalLength);
//    }
//
//    private String genLongNoFromRedis() {
//        List<Object> result = redisTemplate.execute(redisNoScript, keys);
//        Object second = result.get(0);
//        Object microSecond = result.get(1);
//        LocalDateTime datetime = LocalDateTime.ofEpochSecond(Long.parseLong(String.valueOf(second)), Integer.parseInt(String.valueOf(microSecond)) * 1000, ZoneOffset.ofHours(8));
//        return DateUtil.formatLocalDateTime(datetime, RedisIdConstant.DATETIME_FORMATTER_NANO);
//    }

}
