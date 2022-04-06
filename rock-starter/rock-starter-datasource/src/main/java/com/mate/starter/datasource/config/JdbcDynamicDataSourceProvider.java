package com.mate.starter.datasource.config;

import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.mate.starter.datasource.constant.DataSourceConstant;
import com.mate.starter.datasource.enums.DsConfTypeEnum;
import com.mate.starter.datasource.enums.DsJdbcUrlEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * 从数据源里获取配置信息
 *
 * @author pangu
 * @date 2021-3-3
 */
public class JdbcDynamicDataSourceProvider extends AbstractJdbcDataSourceProvider {

	private final DruidDataSourceProperties properties;


	public JdbcDynamicDataSourceProvider(DruidDataSourceProperties properties) {
		super(properties.getDriverClassName(), properties.getUrl(), properties.getUsername(), properties.getPassword());
		this.properties = properties;
	}

	/**
	 * 执行语句获得数据源参数
	 *
	 * @param statement 语句
	 * @return 数据源参数
	 * @throws SQLException sql异常
	 */
	@Override
	protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
		ResultSet rs = statement.executeQuery(properties.getQueryDsSql());

		Map<String, DataSourceProperty> map = new HashMap<>(8);
		while (rs.next()) {
			String name = rs.getString(DataSourceConstant.NAME);
			String username = rs.getString(DataSourceConstant.DS_USER_NAME);
			String password = rs.getString(DataSourceConstant.DS_USER_PWD);
			Integer confType = rs.getInt(DataSourceConstant.DS_CONFIG_TYPE);
			String dsType = rs.getString(DataSourceConstant.DS_TYPE);

			String url;
			// JDBC 配置形式
			DsJdbcUrlEnum urlEnum = DsJdbcUrlEnum.get(dsType);
			if (DsConfTypeEnum.JDBC.getType().equals(confType)) {
				url = rs.getString(DataSourceConstant.DS_JDBC_URL);
			} else if (DsJdbcUrlEnum.MSSQL.getDbName().equals(dsType)) {
				String host = rs.getString(DataSourceConstant.DS_HOST);
				String port = rs.getString(DataSourceConstant.DS_PORT);
				String dsName = rs.getString(DataSourceConstant.DS_NAME);
				String instance = rs.getString(DataSourceConstant.DS_INSTANCE);
				url = String.format(urlEnum.getUrl(), host, instance, port, dsName);
			} else {
				String host = rs.getString(DataSourceConstant.DS_HOST);
				String port = rs.getString(DataSourceConstant.DS_PORT);
				String dsName = rs.getString(DataSourceConstant.DS_NAME);
				url = String.format(urlEnum.getUrl(), host, port, dsName);
			}

			DataSourceProperty property = new DataSourceProperty();
			property.setUsername(username);
			property.setPassword(password);
			property.setUrl(url);
			map.put(name, property);
		}

		// 添加默认主数据源
		DataSourceProperty property = new DataSourceProperty();
		property.setUsername(properties.getUsername());
		property.setPassword(properties.getPassword());
		property.setUrl(properties.getUrl());
		map.put(DataSourceConstant.DS_MASTER, property);
		return map;
	}
}
