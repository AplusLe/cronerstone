package com.mate.starter.mybatis.injector.methods;


import com.mate.starter.mybatis.injector.MateSqlMethod;

/**
 * 插入一条数据（选择字段插入）插入如果中已经存在相同的记录，则忽略当前新数据
 *
 * @author kevin
 */
public class InsertIgnore extends AbstractInsertMethod {

	public InsertIgnore() {
		super(MateSqlMethod.INSERT_IGNORE_ONE);
	}
}
