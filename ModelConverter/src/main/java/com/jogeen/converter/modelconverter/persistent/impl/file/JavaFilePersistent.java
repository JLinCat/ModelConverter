package com.jogeen.converter.modelconverter.persistent.impl.file;

import java.io.File;
import java.util.List;

import com.jogeen.converter.modelconverter.model.OutputModel;
import com.jogeen.converter.modelconverter.persistent.IPersistent;
import com.jogeen.converter.modelconverter.util.FileUtil;
import com.jogeen.converter.modelconverter.util.StringUtil;

public class JavaFilePersistent implements IPersistent{
	/**
	 * 保存的本地目录
	 */
	private String dirName;
	

	public JavaFilePersistent(String dirName){
		this.dirName = dirName;
	}

	public void persistent(List<OutputModel> list) throws Exception {
		if(dirName==null||!dirName.matches("(^//.|^/|^[a-zA-Z])?:?/.+(/$)?")){
			throw new Exception("非法目录");
		}
		for (OutputModel outputModel : list) {
			String fileShortName = outputModel.getClassName()+".java";
			String nameSpace = outputModel.getNameSpace();
			String src=dirName.endsWith("/")?"src/":"/src/";
			String fullName=dirName+src+StringUtil.nameSpaceToPath(nameSpace)+fileShortName;
			FileUtil.createFile(fullName);
			FileUtil.writeStringBufferToFile(new File(fullName), outputModel.getClassContent());
		}
	}
	
	public static void main(String[] args) {
		String s="E:/迅雷下载/P2pSearcher_noad/P2PSearchers/";
		boolean matches = s.matches("(^//.|^/|^[a-zA-Z])?:?/.+(/$)?");
		System.out.println(matches);
	}

	
}
