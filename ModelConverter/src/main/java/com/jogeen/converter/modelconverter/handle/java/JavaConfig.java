package com.jogeen.converter.modelconverter.handle.java;

public class JavaConfig {
	/**
	 * 类名.
	 */
	private String className;

	/**
	 * 包名.
	 */
	private String nameSpace="com.jogeen.model";
	
	/**
	 * 使用表名作为类名
	 */
	private Boolean isUseTableName=true;

	/**
	 * 是否使用java包装类型
	 */
	private Boolean isUserPackingType;
	
	

	public JavaConfig(String nameSpace) {
		super();
		this.nameSpace = nameSpace;
	}

	public JavaConfig(String nameSpace, Boolean isUseTableName) {
		super();
		this.nameSpace = nameSpace;
		this.isUseTableName = isUseTableName;
	}

	public JavaConfig(String className, String nameSpace, Boolean isUseTableName, Boolean isUserPackingType) {
		super();
		this.className = className;
		this.nameSpace = nameSpace;
		this.isUseTableName = isUseTableName;
		this.isUserPackingType = isUserPackingType;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public Boolean getIsUseTableName() {
		return isUseTableName;
	}

	public void setIsUseTableName(Boolean isUseTableName) {
		this.isUseTableName = isUseTableName;
	}

	public Boolean getIsUserPackingType() {
		return isUserPackingType;
	}

	public void setIsUserPackingType(Boolean isUserPackingType) {
		this.isUserPackingType = isUserPackingType;
	}
	
	
}
