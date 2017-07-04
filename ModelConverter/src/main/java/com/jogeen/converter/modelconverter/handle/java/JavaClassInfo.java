package com.jogeen.converter.modelconverter.handle.java;

public class JavaClassInfo implements IClassInfo{

	public String getClassName(String tableName) {
		return tableName;
	}

	public String getSpaceName(String tableName) {
		return "com.jogeen";
	}

}
