package com.jogeen.converter.modelconverter.handle;

import java.util.List;

import com.jogeen.converter.modelconverter.exception.HandlerException;
import com.jogeen.converter.modelconverter.model.DataBaseModel;
import com.jogeen.converter.modelconverter.model.OutputModel;
import com.jogeen.converter.modelconverter.model.TableModel;

/**
 * 
 * 将数据库数据模型转换成可以输出的模型的处理器接口
 * 
 * @author jogeen
 *
 */
public interface IModelHandler {
	/**
	 * 
	 * @param classModel
	 * @return
	 */
	public OutputModel dealModel(TableModel classModel) throws HandlerException;

	/**
	 * 处理数据库模型
	 * 
	 * @param dataBaseModel
	 * @return
	 */
	public List<OutputModel> dealModel(DataBaseModel dataBaseModel) throws HandlerException;

}
