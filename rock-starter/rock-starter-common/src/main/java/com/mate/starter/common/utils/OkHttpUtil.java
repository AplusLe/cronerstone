package com.mate.starter.common.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * OkHttpUtil http请求工具
 * Created by Kevin on 2022/3/24 18:15
 */

@Slf4j
public class OkHttpUtil {

    private static OkHttpClient client;

    private static final String DEFAULT_MEDIA_TYPE = "application/json; charset=utf-8";

    private static final int connectTimeout = 5;

    private static final int readTimeout = 20;

    /**
     * 单例模式  获取类实例
     *
     * @return client
     */
    private static OkHttpClient getInstance() {
        if (client == null) {
            synchronized (OkHttpClient.class) {
                if (client == null) {
                    client = new OkHttpClient.Builder()
                            .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                            .readTimeout(readTimeout, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
        return client;
    }


    public static String doPost(String url, Map<String, Object> paramMap) {
        return doPost(url, paramMap, Maps.newHashMap());
    }

    public static String doPost(String url, Map<String, Object> paramMap, Map<String, Object> headerMap) {
        long start = System.currentTimeMillis();
        try {
            FormBody.Builder builder = new FormBody.Builder();
            if (MapUtils.isNotEmpty(paramMap)) {
                paramMap.keySet().forEach(paramName -> {
                    String paramValue = String.valueOf(paramMap.get(paramName));
                    builder.add(paramName, paramValue);
                });
            }

            FormBody formBody = builder.build();
            Request.Builder requestBuilder = new Request.Builder().url(url).post(formBody);
            return getResponse(url, headerMap, start, requestBuilder);
        } catch (Exception e) {
            log.error("请求url: {}，异常：{}", url, ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String doPostJson(String url, String requestJson) {
        long start = System.currentTimeMillis();
        try {
            log.info("请求url地址：{}，请求参数：{}", url, requestJson);
            RequestBody body = RequestBody.create(MediaType.parse(DEFAULT_MEDIA_TYPE), requestJson);
            Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
            Request request = requestBuilder.build();
            Response response = getInstance().newCall(request).execute();
            String string = String.valueOf(response.body());
            addResponseLog(start, url, string);
            return string;
        } catch (Exception e) {
            log.error("请求url: {}，异常：{}", url, ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String doPostJson(String url, String requestJson, Map<String, Object> headerMap) {
        long start = System.currentTimeMillis();
        try {
            log.info("请求url地址：{}，请求参数：{}", url, requestJson);
            RequestBody body = RequestBody.create(MediaType.parse(DEFAULT_MEDIA_TYPE), requestJson);
            Request.Builder requestBuilder = new Request.Builder().url(url).post(body);
            return getResponse(url, headerMap, start, requestBuilder);
        } catch (Exception e) {
            log.error("请求url: {}，异常：{}", url, ExceptionUtils.getStackTrace(e));
        }
        return "";
    }

    public static String doGet(String url) {
        return doGet(url, Maps.newHashMap());
    }

    public static String doGet(String url, Map<String, Object> paramMap) {
        return doGet(url, paramMap, Maps.newHashMap());
    }

    public static String doGet(String url, Map<String, Object> paramMap, Map<String, Object> headerMap) {
        long start = System.currentTimeMillis();
        try {
            Request.Builder requestBuilder = new Request.Builder();
            HttpUrl.Builder urlBuilder = Objects.requireNonNull(HttpUrl.parse(url)).newBuilder();
            if (MapUtils.isNotEmpty(paramMap)) {
                paramMap.keySet().forEach(paramName -> {
                    String paramValue = String.valueOf(paramMap.get(paramName));
                    urlBuilder.addQueryParameter(paramName, paramValue);
                });
            }
            if (MapUtils.isNotEmpty(headerMap)) {
                headerMap.keySet().forEach(headerName -> {
                    String headerValue = String.valueOf(headerMap.get(headerName));
                    requestBuilder.addHeader(headerName, headerValue);
                });
            }
            addRequestLog(url, paramMap, headerMap);
            Request request = requestBuilder.url(urlBuilder.build()).build();
            log.info("请求接口地址+参数：{}", urlBuilder.build().url());
            Response response = getInstance().newCall(request).execute();
            String string = String.valueOf(response.body());
            addResponseLog(start,url,string);
            return string;
        } catch (Exception e) {
            log.error("请求url: {}，异常：{}", url, ExceptionUtils.getStackTrace(e));
        }
        return "";
    }


    private static void addRequestLog(String url, Map<String, Object> paramMap, Map<String, Object> headerMap) {
        log.info("请求url: {}, 请求form参数：{}, 请求header参数：{}", url, JSON.toJSONString(paramMap), JSON.toJSONString(headerMap));
    }

    private static void addResponseLog(long start, String url, String response) {
        long end = System.currentTimeMillis();
        log.info("[{}]地址->响应完成，响应结果：{}，耗时：{} ms", url, response, (end - start));
    }

    private static String getResponse(String url, Map<String, Object> headerMap, long start, Request.Builder requestBuilder) throws IOException {
        if (MapUtils.isNotEmpty(headerMap)) {
            headerMap.keySet().forEach(headerName -> {
                String headerValue = String.valueOf(headerMap.get(headerName));
                requestBuilder.addHeader(headerName, headerValue);
            });
        }
        Request request = requestBuilder.build();
        Response response = getInstance().newCall(request).execute();
        String string = String.valueOf(response.body());
        addResponseLog(start, url, string);
        return string;
    }
}
