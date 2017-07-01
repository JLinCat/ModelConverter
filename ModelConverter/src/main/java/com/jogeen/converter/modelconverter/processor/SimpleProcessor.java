package com.jogeen.converter.modelconverter.processor;

import java.util.List;

import com.jogeen.converter.modelconverter.conn.DataConnectConfig;
import com.jogeen.converter.modelconverter.conn.IDataExtractor;
import com.jogeen.converter.modelconverter.conn.impl.MysqlDataExtractor;
import com.jogeen.converter.modelconverter.factory.IFieldType;
import com.jogeen.converter.modelconverter.factory.MysqlFieldTypeFactory;
import com.jogeen.converter.modelconverter.handle.IModelHandler;
import com.jogeen.converter.modelconverter.handle.java.JavaConfig;
import com.jogeen.converter.modelconverter.handle.java.JavaModelHandler;
import com.jogeen.converter.modelconverter.model.OutputModel;
import com.jogeen.converter.modelconverter.persistent.IPersistent;
import com.jogeen.converter.modelconverter.persistent.impl.JavaFilePersistent;

public class SimpleProcessor {
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
	 * 数据库名称
	 */
	private String dataBaseName;

	/**
	 * 包名.
	 */
	private String nameSpace = "com.jogeen.model";

	/**
	 * 使用表名作为类名
	 */
	private Boolean isUseTableName = true;
	
	/**
	 * 保存的本地目录
	 */
	private String dirName;

	
	
	public SimpleProcessor(String host, Integer port, String user, String password, String dataBaseName,
			String nameSpace, Boolean isUseTableName, String dirName) {
		super();
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.dataBaseName = dataBaseName;
		this.nameSpace = nameSpace;
		this.isUseTableName = isUseTableName;
		this.dirName = dirName;
	}

	public void execute() throws Exception {
		DataConnectConfig config = new DataConnectConfig(host, port, user, password, dataBaseName);
		IDataExtractor dataExtractor = new MysqlDataExtractor(config);
		
		IFieldType fieldType = MysqlFieldTypeFactory.getInstance().createFieldTypeMapping();
		JavaConfig javaConfig = new JavaConfig(nameSpace, isUseTableName);
		IModelHandler handler = new JavaModelHandler(fieldType, javaConfig);
		
		List<OutputModel> list = handler.dealModel(dataExtractor.getDataBaseModel(dataBaseName));
		
		IPersistent  persistent=new JavaFilePersistent(dirName);
		persistent.persistent(list);
		
	}
	
	public static void main(String[] args) {
		
	}
}
