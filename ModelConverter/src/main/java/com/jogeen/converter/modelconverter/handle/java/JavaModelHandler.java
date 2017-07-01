package com.jogeen.converter.modelconverter.handle.java;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.jogeen.converter.modelconverter.exception.HandlerException;
import com.jogeen.converter.modelconverter.factory.IFieldType;
import com.jogeen.converter.modelconverter.handle.IModelHandler;
import com.jogeen.converter.modelconverter.model.Column;
import com.jogeen.converter.modelconverter.model.DataBaseModel;
import com.jogeen.converter.modelconverter.model.OutputModel;
import com.jogeen.converter.modelconverter.model.TableModel;
import com.jogeen.converter.modelconverter.util.StringUtil;

public class JavaModelHandler implements IModelHandler {

	protected static final String NEW_LINE_T = "\r\n\t";
	protected static final String NEW_LINE = "\r\n";
	protected static final String TAB = "    ";
	
	/**
	 * 字段类型转换接口
	 */
	protected IFieldType fieldType;
	
	/**
	 * java配置信息
	 */
	protected JavaConfig javaConfig;

	
	public JavaModelHandler(IFieldType fieldType,JavaConfig javaConfig) {
		super();
		this.fieldType = fieldType;
		this.javaConfig = javaConfig;
	}

	public List<OutputModel> dealModel(DataBaseModel dataBaseModel)
			throws HandlerException {
		List<OutputModel> result=new ArrayList<OutputModel>();
		List<TableModel> list = dataBaseModel.getList();
		for (TableModel classModel : list) {
			OutputModel outputModel = dealModel(classModel);
			result.add(outputModel);
		}
		return result;
	}

	public OutputModel dealModel(TableModel classModel) throws HandlerException {
		transType(classModel);
		String nameSpace = javaConfig.getNameSpace();
		// 是否使用数据库表名当作类名
		String className = javaConfig.getIsUseTableName() ? classModel
				.getTableName() : javaConfig.getClassName();
		if(StringUtil.isEmpty(className)){
			throw new HandlerException("表名不能未空");
		}
		OutputModel outputModel = new OutputModel(nameSpace, className);
		List<Column> columns = classModel.getColumns();
		StringBuffer classBuffer = new StringBuffer();
		// 加包名
		classBuffer.append("package ").append(nameSpace).append(";").append(NEW_LINE);
		StringBuffer importBuffer = new StringBuffer();
		Set<String> set = new HashSet<String>();
		StringBuffer contentBuffer=new StringBuffer();
		contentBuffer.append("public class ").append(className).append("{").append(NEW_LINE_T);
		// 加入属性
		buildFields(columns, contentBuffer, set);
		//加Get、Set方法
		buildSetGet(columns, contentBuffer);
		contentBuffer.append(NEW_LINE).append("}");
		// 加入 类的导入信息
		for (String string : set) {
			importBuffer.append("import ").append(string).append(";").append(NEW_LINE);
		}
		outputModel.setClassContent(classBuffer.append(importBuffer).append(contentBuffer));
		return outputModel;
	}

	/**
	 * 构建java的get和设方法
	 * @param columns
	 * @param classBuffer
	 */
	private void buildSetGet(List<Column> columns, StringBuffer classBuffer) {
		for (Column column : columns) {
			String comment = column.getComment();
			String field = column.getField();
			String buildDocument = buildDocument(StringUtil.isEmpty(comment) ? field: comment);
			// get方法
			classBuffer.append(buildDocument).append(NEW_LINE_T);
			classBuffer.append(buildGet(column));
			// put方法
			classBuffer.append(buildDocument).append(NEW_LINE_T);
			classBuffer.append(buildSet(column));
		}
	}

	/**
	 * 构建java的get方法字段
	 * @param column
	 * @return
	 */
	private StringBuffer buildGet(Column column) {
		StringBuffer getBuffer=new StringBuffer();
		getBuffer.append("public {TYPE} get{FIELD}(){").append(NEW_LINE_T)
		.append(TAB).append("return this.{field}").append(";").append(NEW_LINE_T)
		.append("}").append(NEW_LINE_T);
		String method = getBuffer.toString()
		.replaceAll("\\{TYPE\\}", StringUtil.getShortTypeName(column.getType()))
		.replaceAll("\\{FIELD\\}", StringUtil.firstToUppper(column.getField()))
		.replaceAll("\\{field\\}", StringUtil.firstTolower(column.getField()));
		return new StringBuffer(method);
	}

	/**
	 * 构建java的set
	 * @param column
	 * @return
	 */
	private StringBuffer buildSet(Column column) {
		StringBuffer setBuffer=new StringBuffer();
		setBuffer.append("public void set{FIELD}({TYPE} {field}){").append(NEW_LINE_T)
		.append(TAB).append("this.{field}={field};").append(NEW_LINE_T)
		.append("}").append(NEW_LINE_T);
		String method = setBuffer.toString()
		.replaceAll("\\{TYPE\\}", StringUtil.getShortTypeName(column.getType()))
		.replaceAll("\\{FIELD\\}", StringUtil.firstToUppper(column.getField()))
		.replaceAll("\\{field\\}", StringUtil.firstTolower(column.getField()));
		return new StringBuffer(method);
	}

	/**
	 * @param columns
	 *            列信息
	 * @param classBuffer
	 * @param set
	 */
	private void buildFields(List<Column> columns, StringBuffer classBuffer,
			Set<String> set) {
		for (Column column : columns) {
			String comment = column.getComment();
			String field = column.getField();
			String buildDocument = buildDocument(StringUtil.isEmpty(comment) ? field
					: comment);
			classBuffer.append(buildDocument).append(NEW_LINE_T);
			classBuffer.append("private ")
					.append(StringUtil.getShortTypeName(column.getType()))
					.append(" ").append(StringUtil.firstTolower(field)).append(";").append(NEW_LINE_T);
			if (!set.contains(column.getType())) {
				set.add(column.getType());
			}
		}
	}

	/**
	 * @param document
	 * @return
	 */
	private String buildDocument(String document) {
		String arttributeNote = "/**"+NEW_LINE_T+"* DOCUMENT."+NEW_LINE_T+"*/";
		return arttributeNote.replaceFirst("DOCUMENT", document);
	}
	
	private void transType(TableModel classModel){
		List<Column> columns = classModel.getColumns();
		for (Column column : columns) {
			column.setType(fieldType.getReallyType(column.getType()));
		}
	}
}
