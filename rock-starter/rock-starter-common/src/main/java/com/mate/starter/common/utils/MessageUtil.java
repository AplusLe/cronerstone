package com.mate.starter.common.utils;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Optional;

/**
 * 消息模板工具类
 */
public class MessageUtil {

    /**
     * 格式化消息模板
     *
     * @param pattern   消息模板
     * @param args      模板参数
     * @return          格式化后的消息
     */
    public static String format(String pattern, Object... args) {
        return Optional.ofNullable(pattern)
                .map(pat -> MessageFormat.format(pat, args))
                .orElse(null);
    }

    /**
     * 解析源文本并提取相应位置的参数
     *
     * @param pattern   消息模板
     * @param source    待解析源文本
     * @param position  要提取的参数索引
     * @return          参数值
     * @throws ParseException   解析异常
     */
    public static Object parse(String pattern, String source, int position) throws ParseException {
        return Optional.of(Arrays.asList(parse(pattern, source)))
                .filter(lst -> lst.size() > position)
                .map(lst -> lst.get(position))
                .orElseThrow(() -> new ParseException("解析源文本失败！", position));
    }

    /**
     * 解析源文本并提取相应位置的参数
     *
     * @param pattern   消息模板
     * @param source    待解析源文本
     * @return          参数值列表
     * @throws ParseException   解析异常
     */
    public static Object[] parse(String pattern, String source) throws ParseException {
        return Optional.ofNullable(new MessageFormat(pattern).parse(source))
                .filter(ls -> ls.length > 0)
                .orElse(new Object[0]);
    }
}
