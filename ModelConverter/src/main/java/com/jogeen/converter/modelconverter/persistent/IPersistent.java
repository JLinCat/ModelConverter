package com.jogeen.converter.modelconverter.persistent;

import java.util.List;

import com.jogeen.converter.modelconverter.model.OutputModel;

public interface IPersistent {
	/**
	 * @param outputModel
	 * @return
	 */
	public void persistent(List<OutputModel> list)throws Exception;
}
