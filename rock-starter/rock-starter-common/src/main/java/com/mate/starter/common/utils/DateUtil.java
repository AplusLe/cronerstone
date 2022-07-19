package com.mate.starter.common.utils;

import com.mate.starter.common.constant.format.DatePattern;
import org.springframework.lang.Nullable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.ChronoLocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.time.temporal.ChronoField.*;

/**
 * 日期工具类
 *
 * @author kevin
 */
public class DateUtil {

    /**
     * 自定义格式化
     */
    public static final String YEAR_MONTH_FORMATTER = "yyyy-MM";
    public static final String DATE_FORMATTER_STR = "yyyy-MM-dd";
    public static final String DATETIME_FORMATTER_STR = "yyyy-MM-dd HH:mm:ss";
    public static final String TIME_FORMATTER_STR = "HH:mm:ss";
    public static final String YEAR_MONTH_FORMATTER_SHORT = "yyyyMM";
    public static final String DATE_FORMATTER_SHORT = "yyyyMMdd";
    public static final String DATETIME_FORMATTER_SHORT = "yyyyMMddHHmmss";
    public static final String DATETIME_FORMATTER_LONG = "yyyyMMddHHmmssSSS";
    /**
     * 纳秒
     */
    public static final String DATETIME_FORMATTER_NANO = "yyyyMMddHHmmssSSSSSSSSS";
    public static final String DATETIME_FORMATTER_SIMPLE = "yyyyMMdd HH:mm:ss";
    public static final String TIME_FORMATTER_SHORT = "HHmmss";
    /**
     * 24小时时间正则表达式
     */
    public static final String MATCH_TIME_24 = "(([0-1][0-9])|2[0-3]):[0-5][0-9]:[0-5][0-9]";
    /**
     * 日期正则表达式
     */
    public static final String REGEX_DATA = "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))";


    /**
     * 获取当前时间戳
     *
     * @return timestamp
     */
    public static long getTimestamp() {
        // LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        // LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        // Timestamp.valueOf(LocalDateTime.now()).getTime();
        // Instant.now().toEpochMilli();
        return Instant.now().toEpochMilli();
    }

    /**
     * 获取当前年份
     *
     * @return yyyy
     */
    public static int getCurrentYear() {
        return LocalDate.now().getYear();
    }

    /**
     * 获取当前年月
     *
     * @return yyyy-MM
     */
    public static String getCurrentYearMonth() {
        return getCurrentDate(YEAR_MONTH_FORMATTER);
    }

    /**
     * 获取当前日期
     *
     * @return yyyy-MM-dd
     */
    public static String getCurrentDate() {
        return getCurrentDate(DATE_FORMATTER_STR);
    }

    /**
     * 获取下一天日期
     *
     * @return yyyy-MM-dd
     */
    public static String getNextDate() {
        return getNextDate(DATE_FORMATTER_STR);
    }

    /**
     * 获取当前时间
     *
     * @return HHmmss
     */
    public static String getCurrentTime() {
        return getCurrentTime(TIME_FORMATTER_STR);
    }

    /**
     * 获取当前日期时间
     *
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateTime() {
        return getCurrentDateTime(DATETIME_FORMATTER_STR);
    }

    /**
     * 获取当前年月
     *
     * @return yyyyMM
     */
    public static String getCurrentYearMonthShort() {
        return getCurrentDate(YEAR_MONTH_FORMATTER_SHORT);
    }

    /**
     * 获取当前日期
     *
     * @return yyyyMMdd
     */
    public static String getCurrentDateShort() {
        return getCurrentDate(DATE_FORMATTER_SHORT);
    }

    /**
     * 获取下一天日期
     *
     * @return yyyy-MM-dd
     */
    public static String getNextDateShort() {
        return getNextDate(DATE_FORMATTER_SHORT);
    }

    /**
     * 获取当前时间
     *
     * @return HHmmss
     */
    public static String getCurrentTimeShort() {
        return getCurrentTime(TIME_FORMATTER_SHORT);
    }

    /**
     * 获取当前日期时间
     *
     * @return yyyyMMddHHmmss
     */
    public static String getCurrentDateTimeShort() {
        return getCurrentDateTime(DATETIME_FORMATTER_SHORT);
    }

    /**
     * 获取当前日期
     *
     * @param pattern 格式化
     */
    public static String getCurrentDate(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取下一天日期
     *
     * @param pattern 格式化
     */
    public static String getNextDate(String pattern) {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间
     *
     * @param pattern 格式化
     */
    public static String getCurrentTime(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前日期时间
     *
     * @param pattern 格式化
     */
    public static String getCurrentDateTime(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间戳转日期
     *
     * @param timestamp 时间戳
     */
    public static LocalDateTime timestampToLocalDateTime(long timestamp) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 日期转时间戳
     *
     * @param localDateTime 日期
     */
    public static long localDateTimeToTimestamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 时间戳转日期时间
     *
     * @param timestamp 时间戳
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatTimestamp(long timestamp) {
        return formatTimestamp(timestamp, DATETIME_FORMATTER_STR);
    }

    /**
     * 时间戳转日期时间
     *
     * @param timestamp 时间戳
     * @return yyyyMMddHHmmss
     */
    public static String formatTimestampShort(long timestamp) {
        return formatTimestamp(timestamp, DATETIME_FORMATTER_SHORT);
    }

    /**
     * 时间戳转日期
     *
     * @param timestamp 时间戳
     * @param pattern   格式化
     */
    public static String formatTimestamp(long timestamp, String pattern) {
        Instant instant = Instant.ofEpochMilli(timestamp);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return formatLocalDateTime(localDateTime, pattern);
    }

    /**
     * 日期格式化字符串
     *
     * @param localDate 日期
     * @return yyyy-MM-dd
     */
    public static String formatLocalDate(LocalDate localDate) {
        return formatLocalDate(localDate, DATE_FORMATTER_STR);
    }

    /**
     * 日期格式化字符串
     *
     * @param localDate 日期
     * @return yyyyMMdd
     */
    public static String formatLocalDateShort(LocalDate localDate) {
        return formatLocalDate(localDate, DATE_FORMATTER_SHORT);
    }

    /**
     * 时间格式化字符串
     *
     * @param localTime 时间
     * @return HH:mm:ss
     */
    public static String formatLocalTime(LocalTime localTime) {
        return formatLocalTime(localTime, TIME_FORMATTER_STR);
    }

    /**
     * 时间格式化字符串
     *
     * @param localTime 时间
     * @return HHmmss
     */
    public static String formatLocalTimeShort(LocalTime localTime) {
        return formatLocalTime(localTime, TIME_FORMATTER_SHORT);
    }

    /**
     * 日期时间格式化字符串
     *
     * @param localDateTime 日期时间
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, DATETIME_FORMATTER_STR);
    }

    /**
     * 日期时间格式化字符串
     *
     * @param localDateTime 日期时间
     * @return yyyyMMddHHmmss
     */
    public static String formatLocalDateTimeShort(LocalDateTime localDateTime) {
        return formatLocalDateTime(localDateTime, DATETIME_FORMATTER_SHORT);
    }

    /**
     * 日期格式化字符串
     *
     * @param localDate 日期
     * @param pattern   格式化
     */
    public static String formatLocalDate(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间格式化字符串
     *
     * @param localTime 时间
     * @param pattern   格式化
     */
    public static String formatLocalTime(LocalTime localTime, String pattern) {
        return localTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期时间格式化字符串
     *
     * @param localDateTime 日期时间
     * @param pattern       格式化
     */
    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期字符串转日期
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate parseLocalDate(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 时间字符串转时间
     *
     * @param time    时间字符串
     * @param pattern 格式化
     */
    public static LocalTime parseLocalTime(String time, String pattern) {
        return LocalTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期时间字符串转日期时间
     *
     * @param dateTime 日期时间字符串
     * @param pattern  格式化
     */
    public static LocalDateTime parseLocalDateTime(String dateTime, String pattern) {
        return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取本周第一天
     */
    public static LocalDate getCurrentWeekFirstDate() {
        return LocalDate.now().minusWeeks(0).with(DayOfWeek.MONDAY);
    }

    /**
     * 获取本周最后一天
     */
    public static LocalDate getCurrentWeekLastDate() {
        return LocalDate.now().minusWeeks(0).with(DayOfWeek.SUNDAY);
    }

    /**
     * 获取本月第一天
     */
    public static LocalDate getCurrentMonthFirstDate() {
        return LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取本月最后一天
     */
    public static LocalDate getCurrentMonthLastDate() {
        return LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取指定周第一天
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate getWeekFirstDate(String date, String pattern) {
        return parseLocalDate(date, pattern).minusWeeks(0).with(DayOfWeek.MONDAY);
    }

    /**
     * 获取指定周第一天
     *
     * @param localDate 日期
     */
    public static LocalDate getWeekFirstDate(LocalDate localDate) {
        return localDate.minusWeeks(0).with(DayOfWeek.MONDAY);
    }

    /**
     * 获取指定周最后一天
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate getWeekLastDate(String date, String pattern) {
        return parseLocalDate(date, pattern).minusWeeks(0).with(DayOfWeek.SUNDAY);
    }

    /**
     * 获取指定周最后一天
     *
     * @param localDate 日期
     */
    public static LocalDate getWeekLastDate(LocalDate localDate) {
        return localDate.minusWeeks(0).with(DayOfWeek.SUNDAY);
    }


    /**
     * 获取指定月份月第一天
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static LocalDate getMonthFirstDate(String date, String pattern) {
        return parseLocalDate(date, pattern).with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取指定月份月第一天
     *
     * @param localDate
     */
    public static LocalDate getMonthFirstDate(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.firstDayOfMonth());
    }

    /**
     * 获取当前星期
     *
     * @return 1:星期一、2:星期二、3:星期三、4:星期四、5:星期五、6:星期六、7:星期日
     */
    public static int getCurrentWeek() {
        return LocalDate.now().getDayOfWeek().getValue();
    }


    /**
     * 获取当前星期
     *
     * @param localDate 日期
     */
    public static int getWeek(LocalDate localDate) {
        return localDate.getDayOfWeek().getValue();
    }

    /**
     * 获取指定日期的星期
     *
     * @param date    日期字符串
     * @param pattern 格式化
     */
    public static int getWeek(String date, String pattern) {
        return parseLocalDate(date, pattern).getDayOfWeek().getValue();
    }

    /**
     * 日期相隔天数
     *
     * @param startLocalDate 起日期
     * @param endLocalDate   止日期
     */
    public static long intervalDays(LocalDate startLocalDate, LocalDate endLocalDate) {
        return endLocalDate.toEpochDay() - startLocalDate.toEpochDay();
    }

    /**
     * 日期相隔小时
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalHours(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toHours();
    }

    /**
     * 日期相隔分钟
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalMinutes(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toMinutes();
    }

    /**
     * 日期相隔毫秒数
     *
     * @param startLocalDateTime 起日期时间
     * @param endLocalDateTime   止日期时间
     */
    public static long intervalMillis(LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime) {
        return Duration.between(startLocalDateTime, endLocalDateTime).toMillis();
    }

    /**
     * 当前是否闰年
     */
    public static boolean isCurrentLeapYear() {
        return LocalDate.now().isLeapYear();
    }

    /**
     * 是否闰年
     */
    public static boolean isLeapYear(LocalDate localDate) {
        return localDate.isLeapYear();
    }

    /**
     * 是否当天
     *
     * @param localDate 日期
     */
    public static boolean isToday(LocalDate localDate) {
        return LocalDate.now().equals(localDate);
    }

    /**
     * 获取此日期时间与默认时区<Asia/Shanghai>组合的时间毫秒数
     *
     * @param localDateTime 日期时间
     */
    public static Long toEpochMilli(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取此日期时间与指定时区组合的时间毫秒数
     *
     * @param localDateTime 日期时间
     */
    public static Long toSelectEpochMilli(LocalDateTime localDateTime, ZoneId zoneId) {
        return localDateTime.atZone(zoneId).toInstant().toEpochMilli();
    }

    /**
     * 计算距今天指定天数的日期
     *
     * @param days
     * @return
     */
    public static String getDateAfterDays(int days) {
        Calendar date = Calendar.getInstance();// today
        date.add(Calendar.DATE, days);
        SimpleDateFormat simpleDate = new SimpleDateFormat(DATE_FORMATTER_STR);
        return simpleDate.format(date.getTime());
    }

    /**
     * 计算距今天指定天数的日期(按指定格式返回)
     *
     * @param days    天数
     * @param pattern 返回格式
     * @return
     */
    public static String getDateAfterDays(int days, String pattern) {
        // Today
        Calendar date = Calendar.getInstance();
        date.add(Calendar.DATE, days);
        SimpleDateFormat simpleDate = new SimpleDateFormat(pattern);
        return simpleDate.format(date.getTime());
    }
    /**
     * 在指定的日期的前几天或后几天
     *
     * @param source 源日期(yyyy-MM-dd)
     * @param days   指定的天数,正负皆可
     * @return
     * @throws ParseException
     */
    public static String addDays(String source, int days) {
        Date date = localDateToDate(parseLocalDate(source, DATE_FORMATTER_STR));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, days);
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMATTER_STR);
        return dateFormat.format(calendar.getTime());
    }

    /**
     * LocalDate转换成Date
     * @param localDate
     * @return
     */
    public static Date localDateToDate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 24小时时间校验
     *
     * @param time
     * @return
     */
    public static boolean isValidate24(String time) {
        Pattern p = Pattern.compile(MATCH_TIME_24);
        return p.matcher(time).matches();
    }

    /**
     * 日期校验
     *
     * @param date
     * @return
     */
    public static boolean isDate(String date) {
        Pattern pat = Pattern.compile(REGEX_DATA);
        Matcher mat = pat.matcher(date);
        return mat.matches();
    }

    /**
     * 判断当前时间是否在指定时间范围
     *
     * @param from 开始时间
     * @param to   结束时间
     * @return 结果
     */
    public static boolean between(LocalTime from, LocalTime to) {
        LocalTime now = LocalTime.now();
        return now.isAfter(from) && now.isBefore(to);
    }


    public static final String PATTERN_DATETIME = DatePattern.NORM_DATETIME_PATTERN;
    public static final String PATTERN_DATE = DatePattern.NORM_DATE_PATTERN;
    public static final String PATTERN_TIME = DatePattern.NORM_TIME_PATTERN;
    /**
     * java 8 时间格式化
     */
    public static final DateTimeFormatter DATETIME_FORMATTER = DatePattern.NORM_DATETIME_FORMAT;
    public static final DateTimeFormatter DATE_FORMATTER = DatePattern.NORM_DATE_FORMAT;
    public static final DateTimeFormatter TIME_FORMATTER = DatePattern.NORM_TIME_FORMAT;

    /**
     * 添加年
     *
     * @param date       时间
     * @param yearsToAdd 添加的年数
     * @return 设置后的时间
     */
    public static Date plusYears(Date date, int yearsToAdd) {
        return DateUtil.plusAtUtc(date, Period.ofYears(yearsToAdd));
    }

    /**
     * 添加月
     *
     * @param date        时间
     * @param monthsToAdd 添加的月数
     * @return 设置后的时间
     */
    public static Date plusMonths(Date date, int monthsToAdd) {
        return DateUtil.plusAtUtc(date, Period.ofMonths(monthsToAdd));
    }

    /**
     * 添加周
     *
     * @param date       时间
     * @param weeksToAdd 添加的周数
     * @return 设置后的时间
     */
    public static Date plusWeeks(Date date, int weeksToAdd) {
        return DateUtil.plus(date, Period.ofWeeks(weeksToAdd));
    }

    /**
     * 添加天
     *
     * @param date      时间
     * @param daysToAdd 添加的天数
     * @return 设置后的时间
     */
    public static Date plusDays(Date date, long daysToAdd) {
        return DateUtil.plus(date, Duration.ofDays(daysToAdd));
    }

    /**
     * 添加小时
     *
     * @param date       时间
     * @param hoursToAdd 添加的小时数
     * @return 设置后的时间
     */
    public static Date plusHours(Date date, long hoursToAdd) {
        return DateUtil.plus(date, Duration.ofHours(hoursToAdd));
    }

    /**
     * 添加分钟
     *
     * @param date         时间
     * @param minutesToAdd 添加的分钟数
     * @return 设置后的时间
     */
    public static Date plusMinutes(Date date, long minutesToAdd) {
        return DateUtil.plus(date, Duration.ofMinutes(minutesToAdd));
    }

    /**
     * 添加秒
     *
     * @param date         时间
     * @param secondsToAdd 添加的秒数
     * @return 设置后的时间
     */
    public static Date plusSeconds(Date date, long secondsToAdd) {
        return DateUtil.plus(date, Duration.ofSeconds(secondsToAdd));
    }

    /**
     * 添加毫秒
     *
     * @param date        时间
     * @param millisToAdd 添加的毫秒数
     * @return 设置后的时间
     */
    public static Date plusMillis(Date date, long millisToAdd) {
        return DateUtil.plus(date, Duration.ofMillis(millisToAdd));
    }

    /**
     * 添加纳秒
     *
     * @param date       时间
     * @param nanosToAdd 添加的纳秒数
     * @return 设置后的时间
     */
    public static Date plusNanos(Date date, long nanosToAdd) {
        return DateUtil.plus(date, Duration.ofNanos(nanosToAdd));
    }

    /**
     * 日期添加时间量
     *
     * @param date   时间
     * @param amount 时间量
     * @return 设置后的时间
     */
    public static Date plusAtUtc(Date date, TemporalAmount amount) {
        Objects.requireNonNull(date, "The date must not be null");
        Instant instant = date.toInstant()
                .atZone(ZoneOffset.UTC)
                .plus(amount)
                .toInstant();
        return Date.from(instant);
    }

    /**
     * 日期添加时间量
     *
     * @param date   时间
     * @param amount 时间量
     * @return 设置后的时间
     */
    public static Date plus(Date date, TemporalAmount amount) {
        Objects.requireNonNull(date, "The date must not be null");
        Instant instant = date.toInstant()
                .plus(amount);
        return Date.from(instant);
    }

    /**
     * 减少年
     *
     * @param date  时间
     * @param years 减少的年数
     * @return 设置后的时间
     */
    public static Date minusYears(Date date, int years) {
        return DateUtil.minusAtUtc(date, Period.ofYears(years));
    }

    /**
     * 减少月
     *
     * @param date   时间
     * @param months 减少的月数
     * @return 设置后的时间
     */
    public static Date minusMonths(Date date, int months) {
        return DateUtil.minusAtUtc(date, Period.ofMonths(months));
    }

    /**
     * 减少周
     *
     * @param date  时间
     * @param weeks 减少的周数
     * @return 设置后的时间
     */
    public static Date minusWeeks(Date date, int weeks) {
        return DateUtil.minus(date, Period.ofWeeks(weeks));
    }

    /**
     * 减少天
     *
     * @param date 时间
     * @param days 减少的天数
     * @return 设置后的时间
     */
    public static Date minusDays(Date date, long days) {
        return DateUtil.minus(date, Duration.ofDays(days));
    }

    /**
     * 减少小时
     *
     * @param date  时间
     * @param hours 减少的小时数
     * @return 设置后的时间
     */
    public static Date minusHours(Date date, long hours) {
        return DateUtil.minus(date, Duration.ofHours(hours));
    }

    /**
     * 减少分钟
     *
     * @param date    时间
     * @param minutes 减少的分钟数
     * @return 设置后的时间
     */
    public static Date minusMinutes(Date date, long minutes) {
        return DateUtil.minus(date, Duration.ofMinutes(minutes));
    }

    /**
     * 减少秒
     *
     * @param date    时间
     * @param seconds 减少的秒数
     * @return 设置后的时间
     */
    public static Date minusSeconds(Date date, long seconds) {
        return DateUtil.minus(date, Duration.ofSeconds(seconds));
    }

    /**
     * 减少毫秒
     *
     * @param date   时间
     * @param millis 减少的毫秒数
     * @return 设置后的时间
     */
    public static Date minusMillis(Date date, long millis) {
        return DateUtil.minus(date, Duration.ofMillis(millis));
    }

    /**
     * 减少纳秒
     *
     * @param date  时间
     * @param nanos 减少的纳秒数
     * @return 设置后的时间
     */
    public static Date minusNanos(Date date, long nanos) {
        return DateUtil.minus(date, Duration.ofNanos(nanos));
    }

    /**
     * 日期减少时间量
     *
     * @param date   时间
     * @param amount 时间量
     * @return 设置后的时间
     */
    public static Date minusAtUtc(Date date, TemporalAmount amount) {
        Objects.requireNonNull(date, "The date must not be null");
        Instant instant = date.toInstant()
                .atZone(ZoneOffset.UTC)
                .minus(amount)
                .toInstant();
        return Date.from(instant);
    }

    /**
     * 日期减少时间量
     *
     * @param date   时间
     * @param amount 时间量
     * @return 设置后的时间
     */
    public static Date minus(Date date, TemporalAmount amount) {
        Objects.requireNonNull(date, "The date must not be null");
        Instant instant = date.toInstant()
                .minus(amount);
        return Date.from(instant);
    }

    /**
     * 日期时间格式化
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    @Nullable
    public static String formatDateTime(@Nullable Date date) {
        return format(date, DATETIME_FORMATTER);
    }

    /**
     * 日期格式化
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    @Nullable
    public static String formatDate(@Nullable Date date) {
        return format(date, DATE_FORMATTER);
    }

    /**
     * 时间格式化
     *
     * @param date 时间
     * @return 格式化后的时间
     */
    @Nullable
    public static String formatTime(@Nullable Date date) {
        return format(date, TIME_FORMATTER);
    }

    /**
     * 日期格式化
     *
     * @param date    时间
     * @param pattern 表达式
     * @return 格式化后的时间
     */
    @Nullable
    public static String format(@Nullable Date date, String pattern) {
        return format(date, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期格式化
     *
     * @param date      时间
     * @param formatter 格式化
     * @return 格式化后的时间
     */
    @Nullable
    public static String format(@Nullable Date date, DateTimeFormatter formatter) {
        if (date == null) {
            return null;
        }
        return format(date.toInstant(), formatter);
    }

    /**
     * 日期格式化
     *
     * @param instant   时间
     * @param formatter 格式化
     * @return 格式化后的时间
     */
    @Nullable
    public static String format(Instant instant, DateTimeFormatter formatter) {
        ZoneId zone = formatter.getZone();
        if (zone == null) {
            return formatter.withZone(ZoneId.systemDefault()).format(instant);
        }
        return formatter.format(instant);
    }

    /**
     * java8 日期时间格式化
     *
     * @param temporal 时间
     * @return 格式化后的时间
     */
    public static String formatDateTime(TemporalAccessor temporal) {
        return DATETIME_FORMATTER.format(temporal);
    }

    /**
     * java8 日期时间格式化
     *
     * @param temporal 时间
     * @return 格式化后的时间
     */
    public static String formatDate(TemporalAccessor temporal) {
        return DATE_FORMATTER.format(temporal);
    }

    /**
     * java8 时间格式化
     *
     * @param temporal 时间
     * @return 格式化后的时间
     */
    public static String formatTime(TemporalAccessor temporal) {
        return TIME_FORMATTER.format(temporal);
    }

    /**
     * java8 日期格式化
     *
     * @param temporal 时间
     * @param pattern  表达式
     * @return 格式化后的时间
     */
    public static String format(TemporalAccessor temporal, String pattern) {
        return DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).format(temporal);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param pattern 表达式
     * @return 时间
     */
    public static Date parse(String dateStr, String pattern) {
        return DateUtil.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param format  DateTimeFormatter
     * @return 时间
     */
    public static Date parse(String dateStr, DateTimeFormatter format) {
        if (format.getZone() == null) {
            format = format.withZone(ZoneId.systemDefault());
        }
        Instant instant = format.parse(dateStr, instantQuery());
        return Date.from(instant);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param pattern 表达式
     * @return 时间
     */
    public static <T> T parse(String dateStr, String pattern, TemporalQuery<T> query) {
        return DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault()).parse(dateStr, query);
    }

    /**
     * LocalDateTime 转 Instant
     *
     * @param dateTime 时间
     * @return Instant
     */
    public static Instant toInstant(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant();
    }

    /**
     * Instant 转 LocalDateTime
     *
     * @param instant Instant
     * @return Instant
     */
    public static LocalDateTime toDateTime(Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 某天开始时间 yyyy-MM-dd 00:00:00
     *
     * @param localDate LocalDate
     * @return Instant
     */
    public static LocalDateTime toStartOfDay(LocalDate localDate) {
        return localDate.atStartOfDay();
    }

    /**
     * 某天结束时间 yyyy-MM-dd 23:59:59
     *
     * @param localDate LocalDate
     * @return Instant
     */
    public static LocalDateTime toEndOfDay(LocalDate localDate) {
        return LocalDateTime.of(localDate, LocalTime.MAX);
    }

    /**
     * Date 转 LocalDateTime
     *
     * @param date Date
     * @return Instant
     */
    public static LocalDateTime toDateTime(Date date) {
        return DateUtil.toDateTime(date.toInstant());
    }

    /**
     * LocalDateTime 转换成 date
     *
     * @param dateTime LocalDateTime
     * @return Date
     */
    public static Date toDate(LocalDateTime dateTime) {
        return Date.from(DateUtil.toInstant(dateTime));
    }

    /**
     * LocalDate 转换成 date
     *
     * @param localDate LocalDate
     * @return Date
     */
    public static Date toDate(final LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime 转换成 Calendar.
     */
    public static Calendar toCalendar(final LocalDateTime localDateTime) {
        return GregorianCalendar.from(ZonedDateTime.of(localDateTime, ZoneId.systemDefault()));
    }

    /**
     * localDateTime 转换成毫秒数
     *
     * @param localDateTime LocalDateTime
     * @return long
     */
    public static long toMilliseconds(final LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * localDate 转换成毫秒数
     *
     * @param localDate LocalDate
     * @return long
     */
    public static long toMilliseconds(LocalDate localDate) {
        return toMilliseconds(localDate.atStartOfDay());
    }

    /**
     * 转换成java8 时间
     *
     * @param calendar 日历
     * @return LocalDateTime
     */
    public static LocalDateTime fromCalendar(final Calendar calendar) {
        TimeZone tz = calendar.getTimeZone();
        ZoneId zid = tz == null ? ZoneId.systemDefault() : tz.toZoneId();
        return LocalDateTime.ofInstant(calendar.toInstant(), zid);
    }

    /**
     * 转换成java8 时间
     *
     * @param instant Instant
     * @return LocalDateTime
     */
    public static LocalDateTime fromInstant(final Instant instant) {
        return LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
    }

    /**
     * 转换成java8 时间
     *
     * @param date Date
     * @return LocalDateTime
     */
    public static LocalDateTime fromDate(final Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 转换成java8 时间
     *
     * @param milliseconds 毫秒数
     * @return LocalDateTime
     */
    public static LocalDateTime fromMilliseconds(final long milliseconds) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(milliseconds), ZoneId.systemDefault());
    }

    /**
     * 判断 A 的时间是否在 B 的时间 "之后"
     *
     * @param self ChronoLocalDateTime
     * @param other ChronoLocalDateTime
     * @return {boolean}
     */
    public static boolean isAfter(ChronoLocalDateTime<?> self, ChronoLocalDateTime<?> other) {
        return self.isAfter(other);
    }

    /**
     * 判断 A 的时间是否在 B 的时间 "之前"
     *
     * @param self ChronoLocalDateTime
     * @param other ChronoLocalDateTime
     * @return {boolean}
     */
    public static boolean isBefore(ChronoLocalDateTime<?> self, ChronoLocalDateTime<?> other) {
        return self.isBefore(other);
    }

    /**
     * 判断 A 的时间是否与 B 的时间 "相同"
     *
     * @param self ChronoLocalDateTime
     * @param other ChronoLocalDateTime
     * @return {boolean}
     */
    public static boolean isEqual(ChronoLocalDateTime<?> self, ChronoLocalDateTime<?> other) {
        return self.isEqual(other);
    }

    /**
     * 比较2个时间差，跨度比较小
     *
     * @param startInclusive 开始时间
     * @param endExclusive   结束时间
     * @return 时间间隔
     */
    public static Duration between(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive);
    }



    /**
     * 比较2个时间差，跨度比较大，年月日为单位
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 时间间隔
     */
    public static Period between(LocalDate startDate, LocalDate endDate) {
        return Period.between(startDate, endDate);
    }

    /**
     * 比较2个 时间差
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return 时间间隔
     */
    public static Duration between(Date startDate, Date endDate) {
        return Duration.between(startDate.toInstant(), endDate.toInstant());
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param pattern 表达式
     * @return 时间
     */
    public static LocalDateTime parseDateTime(CharSequence dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return DateUtil.parseDateTime(dateStr, formatter);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr   时间字符串
     * @param formatter DateTimeFormatter
     * @return 时间
     */
    public static LocalDateTime parseDateTime(CharSequence dateStr, DateTimeFormatter formatter) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @return 时间
     */
    public static LocalDateTime parseDateTime(CharSequence dateStr) {
        return DateUtil.parseDateTime(dateStr, DateUtil.DATETIME_FORMATTER);
    }

    /**
     * 将字符串转换为时间
     *
     * @param text          时间字符串
     * @param parsePatterns 时间正则数组
     * @return 时间
     */
    public static LocalDateTime parseDateTime(@Nullable CharSequence text, @Nullable String[] parsePatterns) {
        return parseDateTime(text, null, parsePatterns);
    }

    /**
     * 将字符串转换为时间
     *
     * @param text          时间字符串
     * @param locale        Locale
     * @param parsePatterns 时间正则数组
     * @return 时间
     */
    public static LocalDateTime parseDateTime(@Nullable CharSequence text, @Nullable Locale locale, @Nullable String[] parsePatterns) {
        return parse(text, locale, parsePatterns, LocalDateTime::from);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param pattern 表达式
     * @return 时间
     */
    public static LocalDate parseDate(CharSequence dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return DateUtil.parseDate(dateStr, formatter);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr   时间字符串
     * @param formatter DateTimeFormatter
     * @return 时间
     */
    public static LocalDate parseDate(CharSequence dateStr, DateTimeFormatter formatter) {
        return LocalDate.parse(dateStr, formatter);
    }

    /**
     * 将字符串转换为日期
     *
     * @param dateStr 时间字符串
     * @return 时间
     */
    public static LocalDate parseDate(CharSequence dateStr) {
        return DateUtil.parseDate(dateStr, DateUtil.DATE_FORMATTER);
    }

    /**
     * 将字符串转换为日期
     *
     * @param text          时间字符串
     * @param parsePatterns 时间正则数组
     * @return 时间
     */
    public static LocalDate parseDate(@Nullable CharSequence text, @Nullable String[] parsePatterns) {
        return parseDate(text, null, parsePatterns);
    }

    /**
     * 将字符串转换为日期
     *
     * @param text          时间字符串
     * @param locale        Locale
     * @param parsePatterns 时间正则数组
     * @return 时间
     */
    public static LocalDate parseDate(@Nullable CharSequence text, @Nullable Locale locale, @Nullable String[] parsePatterns) {
        return parse(text, locale, parsePatterns, LocalDate::from);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @param pattern 时间正则
     * @return 时间
     */
    public static LocalTime parseTime(CharSequence dateStr, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        return DateUtil.parseTime(dateStr, formatter);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr   时间字符串
     * @param formatter DateTimeFormatter
     * @return 时间
     */
    public static LocalTime parseTime(CharSequence dateStr, DateTimeFormatter formatter) {
        return LocalTime.parse(dateStr, formatter);
    }

    /**
     * 将字符串转换为时间
     *
     * @param dateStr 时间字符串
     * @return 时间
     */
    public static LocalTime parseTime(CharSequence dateStr) {
        return DateUtil.parseTime(dateStr, DateUtil.TIME_FORMATTER);
    }

    /**
     * 将字符串转换为时间
     *
     * @param text          时间字符串
     * @param parsePatterns 时间正则数组
     * @return 时间
     */
    public static LocalTime parseTime(@Nullable CharSequence text, @Nullable String[] parsePatterns) {
        return parseTime(text, null, parsePatterns);
    }

    /**
     * 将字符串转换为时间
     *
     * @param text          时间字符串
     * @param locale        Locale
     * @param parsePatterns 时间正则数组
     * @return 时间
     */
    public static LocalTime parseTime(@Nullable CharSequence text, @Nullable Locale locale, @Nullable String[] parsePatterns) {
        return parse(text, locale, parsePatterns, LocalTime::from);
    }

    /**
     * 将字符串转换为时间
     *
     * @param text          时间字符串
     * @param locale        Locale
     * @param parsePatterns 时间正则数组
     * @param query         TemporalQuery
     * @param <T>           泛型
     * @return 时间
     */
    public static <T> T parse(@Nullable CharSequence text, @Nullable Locale locale, @Nullable String[] parsePatterns, TemporalQuery<T> query) {
        if (text == null || parsePatterns == null) {
            throw new IllegalArgumentException("Date and Patterns must not be null");
        }
        final Locale lcl = locale == null ? Locale.getDefault() : locale;
        final ZoneId systemZone = ZoneId.systemDefault();
        DateTimeFormatter formatter = null;
        for (final String parsePattern : parsePatterns) {
            formatter = DateTimeFormatter.ofPattern(parsePattern, lcl).withZone(systemZone);
            try {
                return formatter.parse(text, query);
            } catch (final DateTimeParseException ignore) {
                // leniency is preventing calendar from being set
            }
        }
        throw new DateTimeParseException("Unable to parse the date: " + text, text, -1);
    }

    /**
     * 支持日期、时间、时间日期格式转换成 Instant
     */
    private static final TemporalQuery<Instant> INSTANT_QUERY = new TemporalQuery<Instant>() {

        @Nullable
        @Override
        public Instant queryFrom(TemporalAccessor temporal) {
            if (temporal.isSupported(INSTANT_SECONDS) && temporal.isSupported(NANO_OF_SECOND)) {
                long instantSecs = temporal.getLong(INSTANT_SECONDS);
                int nanoOfSecond = temporal.get(NANO_OF_SECOND);
                return Instant.ofEpochSecond(instantSecs, nanoOfSecond);
            }
            // 获取时区
            ZoneId zoneId = temporal.query(TemporalQueries.zoneId());
            Objects.requireNonNull(zoneId, "Unable to obtain Instant from TemporalAccessor: zoneId is null.");
            if (temporal.isSupported(NANO_OF_DAY)) {
                return LocalTime.ofNanoOfDay(temporal.getLong(NANO_OF_DAY))
                        .atDate(DateUtil.EPOCH)
                        .atZone(zoneId)
                        .toInstant();
            } else if (temporal.isSupported(EPOCH_DAY)) {
                return LocalDate.ofEpochDay(temporal.getLong(EPOCH_DAY))
                        .atStartOfDay()
                        .atZone(zoneId)
                        .toInstant();
            }
            return null;
        }

        @Override
        public String toString() {
            return "Instant";
        }
    };

    /**
     * 兼容 java 8
     * <p>
     * The epoch year {@code LocalDate}, '1970-01-01'.
     */
    public static final LocalDate EPOCH = LocalDate.of(1970, 1, 1);

    public static TemporalQuery<Instant> instantQuery() {
        return INSTANT_QUERY;
    }

}
