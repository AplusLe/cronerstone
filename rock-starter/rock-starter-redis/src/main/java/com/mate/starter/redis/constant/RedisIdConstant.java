package com.mate.starter.redis.constant;

/**
 * @author cfw
 */
public interface RedisIdConstant {
    String TABLE_ID_TAG = "table_id";

    String DIS_ID_PREFIX = "id_gen:{";

    String DIS_ID_SUFFIX = "}";

    Long EXPIRE = 3600 * 24L;

    String LONG_NO = "rock:long_no";

    String SERIAL_CACHE_PREFIX = "rock:NO_CACHE:";

    String DATETIME_FORMATTER_NANO = "yyyyMMddHHmmssSSSSSSSSS";

}
