package com.jogeen.converter.modelconverter.conn;

import java.sql.Connection;
import java.util.List;

import com.jogeen.converter.modelconverter.model.Column;
import com.jogeen.converter.modelconverter.model.DataBaseModel;
import com.jogeen.converter.modelconverter.model.TableModel;

public interface IDataExtractor {
	/**
	 * @return 返回数据库连接
	 */
	public Connection TestConnect();
	
	/**
	 * 获取所有库名
	 * 
	 * @return
	 */
	public List<String> getDataBaseNames();
	
	/**
	 * 获取数据库的所有表名
	 * 
	 * @return
	 */
	public List<String> getTableNames(String dataBaseName);

	/**
	 * 获取表的所有有列信息
	 * 
	 * @param tableNames
	 * @return
	 */
	public List<Column> getColumns(String dataBaseName,String tableName);
	
	/**
	 * 获取对应的数据库模型
	 * 
	 * @return
	 */
	public DataBaseModel getDataBaseModel(String dataBaseName);
	
	/**
	 * 获取对应的表模型
	 * 
	 * @return
	 */
	public TableModel getTableModel(String dataBaseName,String tableName);
	
}
