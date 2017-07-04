package com.jogeen.converter.modelconverter.processor;

import java.util.List;

import com.jogeen.converter.modelconverter.conn.DataConnectConfig;
import com.jogeen.converter.modelconverter.extractor.IDataExtractor;
import com.jogeen.converter.modelconverter.extractor.impl.MysqlDataExtractor;
import com.jogeen.converter.modelconverter.factory.IFieldType;
import com.jogeen.converter.modelconverter.factory.MysqlFieldTypeFactory;
import com.jogeen.converter.modelconverter.handle.IModelHandler;
import com.jogeen.converter.modelconverter.handle.java.IClassInfo;
import com.jogeen.converter.modelconverter.handle.java.JavaClassInfo;
import com.jogeen.converter.modelconverter.handle.java.JavaModelHandler;
import com.jogeen.converter.modelconverter.model.OutputModel;
import com.jogeen.converter.modelconverter.persistent.IPersistent;
import com.jogeen.converter.modelconverter.persistent.impl.file.JavaFilePersistent;

public class BaseProcessor implements IConverterProcessor{
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
	 * 保存的本地目录
	 */
	private String dirName;

	
	
	public BaseProcessor(String host, Integer port, String user, String password, String dataBaseName, String dirName) {
		this.host = host;
		this.port = port;
		this.user = user;
		this.password = password;
		this.dataBaseName = dataBaseName;
		this.dirName = dirName;
	}

	public void execute() throws Exception {
		DataConnectConfig config = new DataConnectConfig(host, port, user, password, dataBaseName);
		IDataExtractor dataExtractor = new MysqlDataExtractor(config);
		
		IFieldType fieldType = MysqlFieldTypeFactory.getInstance().createFieldTypeMapping();
		
		IClassInfo classInfo=new JavaClassInfo();
		IModelHandler handler = new JavaModelHandler(fieldType, classInfo);
		
		List<OutputModel> list = handler.dealModel(dataExtractor.getDataBaseModel(dataBaseName));
		
		IPersistent  persistent=new JavaFilePersistent(dirName);
		persistent.persistent(list);
		
	}
	
	public static void main(String[] args) throws Exception {
		BaseProcessor simpleProcessor = new BaseProcessor("localhost", 3306, "root", "root", "plan_manage","D:/MC");
		simpleProcessor.execute();
	}
}
