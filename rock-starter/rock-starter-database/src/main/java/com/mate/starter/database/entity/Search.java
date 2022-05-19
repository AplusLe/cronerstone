package com.mate.starter.database.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索封装类
 *
 * @author Kevin
 */
@Data
public class Search implements Serializable {


	private static final long serialVersionUID = 9163037146504408923L;

	/**
	 * 当前页
	 */
	private Integer current = 1;

	/**
	 * 每页的数量
	 */
	private Integer size = 10;

	/**
	 * 开始日期
	 */
	private String startDate;

	/**
	 * 结束日期
	 */
	private String endDate;

	/**
	 * 数据权限
	 */
	private List<Long> dataScope;

	/**
	 * 通用批量设置的Id集
	 */
	private List<Long> ids;

	/**
	 * 通用ID查询
	 */
	private String id;

}
