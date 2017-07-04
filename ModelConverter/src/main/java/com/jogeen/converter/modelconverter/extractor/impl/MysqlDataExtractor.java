package com.jogeen.converter.modelconverter.extractor.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.jogeen.converter.modelconverter.conn.DataConnectConfig;
import com.jogeen.converter.modelconverter.extractor.IDataExtractor;
import com.jogeen.converter.modelconverter.model.Column;
import com.jogeen.converter.modelconverter.model.DataBaseModel;
import com.jogeen.converter.modelconverter.model.TableModel;
import com.jogeen.converter.modelconverter.util.StringUtil;

/**
 * @version 1.0
 * @author jogeen
 * mysql的数据提取器
 *
 */
public class MysqlDataExtractor implements IDataExtractor {
	Logger logger = Logger.getLogger(MysqlDataExtractor.class);

	/**
	 * 链接配置信息
	 */
	private DataConnectConfig dataConnectConfig;

	/**
	 * 数据库链接
	 */
	private Connection connection;

	public DataConnectConfig getDataConnectConfig() {
		return dataConnectConfig;
	}

	public void setDataConnectConfig(DataConnectConfig dataConnectConfig) {
		this.dataConnectConfig = dataConnectConfig;
	}

	public MysqlDataExtractor(DataConnectConfig dataConnectConfig) {
		super();
		this.dataConnectConfig = dataConnectConfig;
		connection = createConnection();
	}

	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;
	}

	/**
	 * @return 返回数据库连接
	 */
	public Connection createConnection() {
		Connection connection = null;
		try {
			Class.forName(dataConnectConfig.getDriver());
			String dbURL = getURL();
			connection = DriverManager.getConnection(dbURL, dataConnectConfig.getUser(),
					dataConnectConfig.getPassword());
			logger.info("Connection Successful!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Connection failed!");
			connection = null;
		}
		return connection;
	}

	public List<String> getDataBaseNames() {
		List<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			if (connection.isClosed()) {
				connection = createConnection();
			}
			stmt = connection.createStatement();
			String sql = "SHOW DATABASES";
			ResultSet result = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			while (result.next()) {
				String string = result.getString(1);
				list.add(string);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<String> getTableNames(String dataBaseName) {
		if(StringUtil.isEmpty(dataBaseName)){
			dataBaseName=dataConnectConfig.getDataBaseName();
		}
		List<String> list = new ArrayList<String>();
		Statement stmt;
		try {
			if (connection.isClosed()) {
				connection = createConnection();
			}
			stmt = connection.createStatement();
			String sql = "SHOW TABLES FROM " + dataBaseName;
			ResultSet result = stmt.executeQuery(sql);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			while (result.next()) {
				String string = result.getString(1);
				list.add(string);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public List<Column> getColumns(String dataBaseName, String tableName) {
		List<Column> list = new ArrayList<Column>();
		Statement stmt;
		try {
			if (connection.isClosed()) {
				connection = createConnection();
			}
			stmt = connection.createStatement();
			stmt.executeQuery("use information_schema");
			String sql1 = "SELECT COLUMN_NAME,DATA_TYPE,COLUMN_COMMENT FROM columns WHERE table_name='" + tableName
					+ "'";
			ResultSet result = stmt.executeQuery(sql1);// executeUpdate语句会返回一个受影响的行数，如果返回-1就没有成功
			while (result.next()) {
				Column column = new Column();
				column.setField(result.getString(1));
				column.setType(result.getString(2));
				column.setComment(result.getString(3));
				list.add(column);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Connection TestConnect() {
		try {
			Class.forName(dataConnectConfig.getDriver());
			String dbURL = getURL();
			logger.info("init db url:"+dbURL);
			connection = DriverManager.getConnection(dbURL, dataConnectConfig.getUser(),
					dataConnectConfig.getPassword());
			logger.info("Connection Successful!");
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Connection failed!");
			return null;
		}
		return connection;
	}

	private String getURL() {
		String host = dataConnectConfig.getHost();
		Integer port = dataConnectConfig.getPort();
		String user = dataConnectConfig.getUser();
		String password = dataConnectConfig.getPassword();
		String dataBaseName = dataConnectConfig.getDataBaseName();
		String url = "jdbc:mysql://" + host + ":" + port + "/" + dataBaseName + "?" + "user=" + user + "&password="
				+ password + "&useUnicode=true&characterEncoding=UTF8";
		return url;
	}

	public DataBaseModel getDataBaseModel(String dataBaseName) {
		List<String> tableNames = getTableNames(dataBaseName);
		List<TableModel> tableList = new ArrayList<TableModel>();
		for (String tableName : tableNames) {
			List<Column> columns = getColumns(dataBaseName, tableName);
			TableModel tableModel=new TableModel();
			tableModel.setColumns(columns);
			tableModel.setTableName(tableName);
			tableList.add(tableModel);
		}
		DataBaseModel dataBaseModel = new DataBaseModel(dataBaseName,tableList);
		return dataBaseModel;
	}

	public TableModel getTableModel(String dataBaseName,String tableName) {
		List<Column> columns = getColumns(dataBaseName, tableName);
		TableModel tableModel=new TableModel();
		tableModel.setColumns(columns);
		tableModel.setTableName(tableName);
		return tableModel;
	}
}
