package com.mate.starter.database.util;

import com.baomidou.mybatisplus.core.toolkit.AES;

/**
 * Mybatis Plus mpw加密工具类
 *
 * @author kevin
 */
public class Aes4MpwUtil {

	private final static String RANDOM_KEY = "ca20da3846d9d8d0";

	public static String getRandomKey() {
		return AES.generateRandomKey();
	}

	public static String encrypt(String data) {
		return encrypt(data, RANDOM_KEY);
	}

	public static String encrypt(String data, String randomKey) {
		return AES.encrypt(data, randomKey);
	}
}
