package edu.cqut.util;


public class StringUtil {
	//��ֹʵ����
	private StringUtil(){}
	public static boolean isEmpty(String str) {
		return (str == null) || (str.equals(""));
	}
	public static boolean isNotEmpty(String str) {
		return (str != null) && (!str.equals(""));
	}
}
