package com.mate.starter.common.ttl;

import lombok.experimental.UtilityClass;
import org.springframework.lang.Nullable;

import java.util.*;
import java.util.function.Supplier;

/**
 * ThreadLocal 工具类,通过在ThreadLocal存储map信息,来实现在ThreadLocal中维护多个信息
 * * <br>e.g.<code>
 * * ThreadLocalUtil.set("key",value);<br>
 * * ThreadLocalUtil.get("key");<br>
 * * ThreadLocalUtil.remove("key");<br>
 * * ThreadLocalUtil.getAndRemove("key");<br>
 * * ThreadLocalUtil.get("key",()-&gt;defaultValue);<br>
 * * ThreadLocalUtil.clear();<br>
 * * </code>
 *
 * @param <T> 对象
 * @author pangu
 * @author zhouhao
 */
@UtilityClass
@SuppressWarnings("unchecked")
public class ThreadLocalUtil<T> {

	private final ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(HashMap::new);

	/**
	 * @return 获取threadLocal
	 */
	public Map<String, Object> getThreadLocal() {
		return THREAD_LOCAL.get();
	}

	/**
	 * @return threadLocal中的全部值
	 */
	public Map<String, Object> getAll() {
		return new HashMap<>(THREAD_LOCAL.get());
	}

	public <T> T get(String key) {
		return (T) THREAD_LOCAL.get().get(key);
	}

	public <T> T get(String key, T defaultValue) {
		Map<String, Object> map = THREAD_LOCAL.get();
		return map.get(key) == null ? defaultValue : (T) map.get(key);
	}


	/**
	 * 设置一个值到ThreadLocal
	 *
	 * @param key   键
	 * @param value 值
	 * @param <T>   值的类型
	 * @return 被放入的值
	 * @see Map#put(Object, Object)
	 */
	public static <T> T set(String key, T value) {
		THREAD_LOCAL.get().put(key, value);
		return value;
	}

	/**
	 * 设置一个值到ThreadLocal
	 *
	 * @param keyValueMap Map
	 * @see Map#putAll(Map)
	 */
	public void set(Map<String, Object> keyValueMap) {
		THREAD_LOCAL.get().putAll(keyValueMap);
	}

	/**
	 * 删除所有值
	 */
	public void remove() {
		THREAD_LOCAL.remove();
	}

	/**
	 * 根据前缀匹配值
	 *
	 * @param prefix 前缀
	 * @param <T>    泛型对象
	 * @return 泛型对象
	 */
	public <T> Map<String, T> fetchVarsByPrefix(String prefix) {
		Map<String, T> vars = new HashMap<>();
		if (prefix == null) {
			return vars;
		}
		Map<String, T> map = (Map<String, T>) THREAD_LOCAL.get();
		Set<Map.Entry<String, T>> set = map.entrySet();
		for (Map.Entry<String, T> entry : set) {
			String key = entry.getKey();
			if (key != null) {
				if (key.startsWith(prefix)) {
					vars.put(key, (T) entry.getValue());
				}
			}
		}
		return vars;
	}

	/**
	 * 删除参数对应的值
	 *
	 * @param key 键
	 * @see Map#remove(Object)
	 */
	public <T> T remove(String key) {
		return (T) THREAD_LOCAL.get().remove(key);
	}

	/**
	 * 清除指定前缀的值
	 *
	 * @param prefix 前缀
	 */
	public void clear(String prefix) {
		if (prefix == null) {
			return;
		}
		Map<String, Object> map = THREAD_LOCAL.get();
		Set<Map.Entry<String, Object>> set = map.entrySet();
		List<String> removeKeys = new ArrayList<>();

		for (Map.Entry<String, Object> entry : set) {
			String key = entry.getKey();
			if (key != null) {
				if (key.startsWith(prefix)) {
					removeKeys.add(key);
				}
			}
		}
		for (String key : removeKeys) {
			map.remove(key);
		}
	}

	/**
	 * 从ThreadLocal中获取值,并指定一个当值不存在的提供者
	 *
	 * @see Supplier
	 */
	@Nullable
	public <T> T getIfAbsent(String key, Supplier<T> supplierOnNull) {
		return ((T) THREAD_LOCAL.get().computeIfAbsent(key, k -> supplierOnNull.get()));
	}

	/**
	 * 获取一个值后然后删除掉
	 *
	 * @param key 键
	 * @param <T> 值类型
	 * @return 值, 不存在则返回null
	 * @see this#get(String)
	 * @see this#remove(String)
	 */
	public <T> T getAndRemove(String key) {
		try {
			return get(key);
		} finally {
			remove(key);
		}
	}

}
