package com.jogeen.converter.modelconverter.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	private static int lastIndexOf;

	/**
	 * 首字母转大写
	 * 
	 * @param source
	 * @return
	 */
	public static String firstToUppper(String source) {
		if (isEmpty(source)) {
			return source;
		}
		return source.replaceFirst(source.substring(0, 1),
				source.substring(0, 1).toUpperCase());
	}

	/**
	 * 首字母转小写
	 * 
	 * @param source
	 * @return
	 */
	public static String firstTolower(String source) {
		if (isEmpty(source)) {
			return source;
		}
		return source.replaceFirst(source.substring(0, 1),
				source.substring(0, 1).toLowerCase());
	}

	/**
	 * 判断字符串是否为null或者空串
	 * 
	 * @param source
	 * @return
	 */
	public static boolean isEmpty(String source) {
		if (source == null || "".equals(source)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 下划线格式转成驼峰格式
	 * 
	 * @param underlineString
	 *            a_bb_bc
	 * @return aBbBc
	 */
	public static String underlineToHump(String underlineString) {
		if (isEmpty(underlineString)) {
			return underlineString;
		}
		String[] split = underlineString.split("_");
		StringBuffer sb = new StringBuffer();
		if (split.length == 1) {
			return firstTolower(split[0]);
		} else {
			sb.append(firstTolower(split[0]));
		}
		for (int i = 1; i < split.length; i++) {
			sb.append(firstToUppper(split[i]));
		}
		return sb.toString();
	}

	/**
	 * 驼峰格式转成下划线格式
	 * 
	 * @param underlineString
	 *            aBbBc
	 * @return a_bb_bc
	 */
	public static String humpToUnderline(String underlineString) {
		if (isEmpty(underlineString)) {
			return underlineString;
		}
		Pattern pattern = Pattern.compile("([A-Z][a-z]*)");
		Matcher matcher = pattern.matcher(underlineString);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String group = matcher.group();
			sb.append(firstTolower(group)).append("_");
		}
		return sb.substring(0, sb.length() - 1);
	}

	/**
	 * 从类全称中截取类名
	 * 
	 * @param fullName
	 * @return
	 */
	public static String getShortTypeName(String fullName) {
		if(isEmpty(fullName)){
			return fullName;
		}
		lastIndexOf = fullName.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return fullName;
		}
		String substring = fullName.substring(lastIndexOf+1, fullName.length());
		return substring;
	}
	
	
	/**
	 * 将命名空间转出目录路径
	 * @param nameSpace
	 * @return
	 */
	public static String nameSpaceToPath(String nameSpace){
		if(isEmpty(nameSpace)){
			return nameSpace;
		}
		String replaceAll = nameSpace.replaceAll("\\.","/")+"/";
		return replaceAll;
	}

	public static void main(String[] args) {
		String humpToUnderline = underlineToHump("a_bb_bc");
		System.out.println(humpToUnderline);
		System.out.println(humpToUnderline("ABbBc"));
	}
}
