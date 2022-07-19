package com.mate.starter.common.context;

import cn.hutool.core.convert.Convert;
import com.mate.starter.common.constant.MateConstant;
import com.mate.starter.common.constant.pool.StringPool;
import com.mate.starter.common.ttl.ThreadLocalUtil;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Mate上下文传递
 * <p>
 * 主要用于传递用户信息、租户信息、链路追踪信息等
 * </p>
 *
 * @author kevin
 */
public final class MateContextHolder {

	private MateContextHolder() {
	}

	public static Map<String, Object> getLocalMap() {
		Map<String, Object> map = ThreadLocalUtil.getThreadLocal();
		if (map == null) {
			map = new ConcurrentHashMap<>(10);
			ThreadLocalUtil.set(map);
		}
		return map;
	}

	public static void setLocalMap(Map<String, Object> localMap) {
		ThreadLocalUtil.set(localMap);
	}

	public static <T> T get(String key, Class<T> type) {
		Map<String, Object> map = getLocalMap();
		return Convert.convert(type, map.get(key));
	}

	public static <T> T get(String key, Class<T> type, Object def) {
		Map<String, Object> map = getLocalMap();
		return Convert.convert(type, map.getOrDefault(key, String.valueOf(def == null ? StringPool.EMPTY : def)));
	}

	public static String get(String key) {
		Map<String, Object> map = getLocalMap();
		return (String) map.getOrDefault(key, StringPool.EMPTY);
	}

	public static void set(String key, Object value) {
		Map<String, Object> map = getLocalMap();
		map.put(key, value == null ? StringPool.EMPTY : value.toString());
	}

	/**
	 * 用户ID
	 *
	 * @return 用户ID
	 */
	public static Long getUserId() {
		return get(MateConstant.MATE_USER_ID, Long.class, 0L);
	}

	/**
	 * 用户ID
	 *
	 * @param userId 用户ID
	 */
	public static void setUserId(Long userId) {
		set(MateConstant.MATE_USER_ID, userId);
	}

	/**
	 * 登录账号
	 *
	 * @return 登录账号
	 */
	public static String getUserName() {
		return get(MateConstant.MATE_USER_NAME, String.class);
	}

	/**
	 * 登录账号
	 *
	 * @param userName 登录账号
	 */
	public static void setUserName(String userName) {
		set(MateConstant.MATE_USER_NAME, userName);
	}

	/**
	 * 姓名
	 *
	 * @return 姓名
	 */
	public static String getName() {
		return get(MateConstant.MATE_NAME, String.class);
	}

	/**
	 * 姓名
	 *
	 * @param name 姓名
	 */
	public static void setName(String name) {
		set(MateConstant.MATE_NAME, name);
	}

	/**
	 * 获取租户编码
	 *
	 * @return 租户编码
	 */
	public static String getTenantId() {
		return get(MateConstant.MATE_TENANT_ID, String.class, StringPool.EMPTY);
	}

	public static void setTenantId(String val) {
		set(MateConstant.MATE_TENANT_ID, val);
	}

	/**
	 * 企业ID
	 *
	 * @return 用户ID
	 */
	public static Long getOrgId() {
		return get(MateConstant.MATE_USER_ID, Long.class, 0L);
	}

	/**
	 * 企业ID
	 *
	 * @param orgId 企业ID
	 */
	public static void setOrgId(Long orgId) {
		set(MateConstant.MATE_ORG_ID, orgId);
	}

	public static void remove() {
		ThreadLocalUtil.remove();
	}

}
