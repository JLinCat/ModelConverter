package com.jogeen.converter.modelconverter.factory;

public class DefaultFieldType implements IFieldType{

	public String getReallyType(String dataBaseType) {
		return dataBaseType;
	}

}
