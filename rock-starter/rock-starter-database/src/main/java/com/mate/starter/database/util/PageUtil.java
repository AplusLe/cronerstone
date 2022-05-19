package com.mate.starter.database.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mate.starter.database.entity.Search;
import com.mate.starter.database.enums.PageTypeEnum;

import java.util.ArrayList;
import java.util.List;

/**
 * 分页工具类
 *
 * @author Kevin
 */
public class PageUtil {

	public static <T> Page<T> getPage(Search search) {
		// 从前端获取分页数据
		if (search.getCurrent() != null) {
			return new Page<T>(search.getCurrent(), search.getSize());
			// 如果为空则默认设置
		} else {
			return new Page<T>(PageTypeEnum.CURRENT.getNumber(), PageTypeEnum.SIZE.getNumber());
		}
	}

	/**
	 * 分页函数
	 *
	 * @param currentPage 当前页数
	 * @param pageSize    每一页的数据条数
	 * @param list        要进行分页的数据列表
	 * @return 当前页要展示的数据
	 */
	public static <T> Page<T> getPages(long currentPage, long pageSize, List<T> list) {
		Page<T> page = new Page<T>();
		int size = list.size();

		if (pageSize > size) {
			pageSize = size;
		}

		// 求出最大页数，防止currentPage越界
		long maxPage = size % pageSize == 0 ? size / pageSize : size / pageSize + 1;

		if (currentPage > maxPage) {
			currentPage = maxPage;
		}

		// 当前页第一条数据的下标
		long curIdx = currentPage > 1 ? (currentPage - 1) * pageSize : 0;

		List<T> pageList = new ArrayList<>();

		// 将当前页的数据放进pageList
		for (long i = 0; i < pageSize && curIdx + i < size; i++) {
			pageList.add(list.get((int) (curIdx + i)));
		}

		page.setCurrent(currentPage).setSize(pageSize).setTotal(list.size()).setRecords(pageList);
		return page;
	}
}
