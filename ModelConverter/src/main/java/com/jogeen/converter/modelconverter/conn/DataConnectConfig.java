package com.jogeen.converter.modelconverter.conn;

public class DataConnectConfig {
	/**
	 * mysql驱动
	 */
	public static final String MYSQL_DRIVER="com.mysql.jdbc.Driver";
	/**
	 * 主机地址
	 */
	private String host;
	/**
	 * 端口
	 */
	private Integer port;
	/**
	 * 用户名
	 */
	private String user;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 驱动
	 */
	private String driver=MYSQL_DRIVER;
	
	/**
	 * 数据库名称
	 */
	private String dataBaseName;

	public DataConnectConfig(String host, Integer port, String user,
			String password, String dataBaseName) {
		super();
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.dataBaseName = dataBaseName;
	}
	
	public DataConnectConfig(String host, Integer port, String user,
			String password, String driver, String dataBaseName) {
		super();
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.driver = driver;
		this.dataBaseName = dataBaseName;
	}

	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	
}
