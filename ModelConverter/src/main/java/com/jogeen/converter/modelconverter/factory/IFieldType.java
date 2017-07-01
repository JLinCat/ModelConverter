package com.jogeen.converter.modelconverter.factory;

public interface IFieldType {
	/**
	 * 获取数据库字段类型对应的变成语言类型
	 * @param dataBaseType
	 * @return
	 */
	public String getReallyType(String dataBaseType);
}
