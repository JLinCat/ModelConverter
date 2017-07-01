package com.jogeen.converter.modelconverter.model;

public class Column {
	private String field;
	private String type;
	private String comment;

	public Column() {

	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Column(String field, String type, String comment) {
		super();
		this.field = field;
		this.type = type;
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "Column [field=" + field + ", type=" + type + ", comment="
				+ comment + "]";
	}

}
