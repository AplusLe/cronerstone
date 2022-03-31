package com.mate.starter.redis.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author cfw
 */

@Getter
@AllArgsConstructor
public enum NoEnum {

    SAMPLE("YF", "yyyyMMddHHmmssSSSSSSSSS", 19, 2, 21,"样例");
    /**
     * 单号前缀
     * 为空时填""
     */
    private final String prefix;

    /**
     * 时间格式表达式
     * 例如：yyyyMMdd
     */
    private final String datePattern;

    /**
     * 流水号长度
     */
    private final Integer serialLength;
    /**
     * 随机数长度
     */
    private final Integer randomLength;

    /**
     * 总长度
     */
    private final Integer totalLength;

    private final String desc;
}
