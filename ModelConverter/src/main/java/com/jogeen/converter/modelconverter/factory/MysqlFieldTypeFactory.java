package com.jogeen.converter.modelconverter.factory;

import java.util.HashMap;
import java.util.Map;

import com.jogeen.converter.modelconverter.util.StringUtil;

public class MysqlFieldTypeFactory implements IFieldTypeFactory {

	private static MysqlFieldTypeFactory instance = new MysqlFieldTypeFactory();

	private MysqlFieldTypeFactory() {

	}

	public static synchronized MysqlFieldTypeFactory getInstance() {
		if (instance != null) {
			instance = new MysqlFieldTypeFactory();
		}
		return instance;
	}

	public IFieldType createFieldTypeMapping() {
		IFieldType iFieldType = new IFieldType() {
			Map<String, String> map = null;

			public String getReallyType(String dataBaseType) {
				if (map == null) {
					init();
				}
				String string = map.get(dataBaseType.toUpperCase());
				return StringUtil.isEmpty(string)?dataBaseType:string;
			}
			
			public void init() {
				map = new HashMap<String, String>();
				// 数值类型
				map.put("TINYINT", "java.lang.Integer");
				map.put("SMALLINT", "java.lang.Integer");
				map.put("MEDIUMINT", "java.lang.Integer");
				map.put("INT", "java.lang.Integer");
				map.put("INTEGER", "java.lang.Long");
				map.put("BIGINT", "java.math.BigInteger");
				map.put("FLOAT", "java.lang.Float");
				map.put("DOUBLE", "java.lang.Double");
				map.put("DECIMAL", "java.math.BigDecimal");
				// 时间类型
				map.put("DATE", "java.util.Date");
				map.put("TIME", "java.util.Date");
				map.put("YEAR", "java.util.Date");
				map.put("DATETIME", "java.util.Date");
				map.put("TIMESTAMP", "java.util.Date");
				// 字符串类型
				map.put("CHAR", "java.lang.String");
				map.put("VARCHAR", "java.lang.String");
				map.put("TINYBLOB", "java.lang.byte[]");
				map.put("TINYTEXT", "java.lang.String");
				map.put("BLOB", "java.lang.byte[]");
				map.put("TEXT", "java.lang.String");
				map.put("MEDIUMBLOB", "java.lang.String");
				map.put("MEDIUMTEXT", "java.lang.String");
				map.put("LONGBLOB", "java.lang.String");
				map.put("LONGTEXT", "java.lang.String");
			}
		};
		return iFieldType;
	}
}
