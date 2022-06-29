package com.mate.starter.web.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 大整数序列化为 String 字符串，避免浏览器丢失精度
 *
 * <p>
 * 前端建议采用：
 * bignumber 库： https://github.com/MikeMcl/bignumber.js
 * decimal.js 库： https://github.com/MikeMcl/decimal.js
 * </p>
 *
 * @author kevin
 */
public class BigNumberModule extends SimpleModule {
	public static final BigNumberModule INSTANCE = new BigNumberModule();

	public BigNumberModule() {
		super(BigNumberModule.class.getName());
		this.addSerializer(Long.class, BigNumberSerializer.instance);
		this.addSerializer(Long.TYPE, BigNumberSerializer.instance);
		this.addSerializer(BigInteger.class, BigNumberSerializer.instance);
		this.addSerializer(BigDecimal.class, BigNumberSerializer.instance);
	}
}
