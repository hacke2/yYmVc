package edu.cqut.util;


public class StringUtil {
	//½ûÖ¹ÊµÀý»¯
	private StringUtil(){}
	public static boolean isEmpty(String str) {
		return (str == null) || (str.equals(""));
	}
	public static boolean isNotEmpty(String str) {
		return (str != null) && (!str.equals(""));
	}
}
