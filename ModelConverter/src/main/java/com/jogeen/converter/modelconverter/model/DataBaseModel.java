package com.jogeen.converter.modelconverter.model;

import java.util.List;

/**
 * @author jogeen
 *
 */
public class DataBaseModel {
	/**
	 * 数据库名称
	 */
	private String dataBaseName;
	/**
	 * 类信息列表
	 */
	private List<TableModel> list;
	
	
	
	public DataBaseModel(String dataBaseName, List<TableModel> list) {
		super();
		this.dataBaseName = dataBaseName;
		this.list = list;
	}
	public String getDataBaseName() {
		return dataBaseName;
	}
	public void setDataBaseName(String dataBaseName) {
		this.dataBaseName = dataBaseName;
	}
	public List<TableModel> getList() {
		return list;
	}
	public void setList(List<TableModel> list) {
		this.list = list;
	}


	
}
