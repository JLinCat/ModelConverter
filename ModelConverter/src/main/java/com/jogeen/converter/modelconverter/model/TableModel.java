package com.jogeen.converter.modelconverter.model;

import java.util.List;

/**
 * @author tashan
 *
 */
public class TableModel {

	/**
	 * 表名.
	 */
	private String tableName;

	/**
	 * 列属性.
	 */
	private List<Column> columns;

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}


}
