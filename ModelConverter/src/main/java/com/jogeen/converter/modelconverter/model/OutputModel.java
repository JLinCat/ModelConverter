package com.jogeen.converter.modelconverter.model;

public class OutputModel {
	/**
	 * 命名空间
	 */
	private String nameSpace;
	/**
	 * 类名
	 */
	private String className;
	/**
	 * 内容
	 */
	private StringBuffer classContent;

	public OutputModel(String nameSpace, String className,
			StringBuffer classContent) {
		super();
		this.nameSpace = nameSpace;
		this.className = className;
		this.classContent = classContent;
	}

	public OutputModel() {
	}

	public OutputModel(String nameSpace, String className) {
		super();
		this.nameSpace = nameSpace;
		this.className = className;
	}

	public String getNameSpace() {
		return nameSpace;
	}

	public void setNameSpace(String nameSpace) {
		this.nameSpace = nameSpace;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public StringBuffer getClassContent() {
		return classContent;
	}

	public void setClassContent(StringBuffer classContent) {
		this.classContent = classContent;
	}

}
