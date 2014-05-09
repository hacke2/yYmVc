package edu.cqut.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil
{
	private static final String filePath = PropertiesUtil.class.getClassLoader().getResource("webConfig.properties").getFile();
	private static long curModifiedTime;
	static {
		try {
			curModifiedTime = new File(filePath).lastModified();
			System.out.println("当前文件修改时间为 " +curModifiedTime);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}

	
  public static String readValue(String filePath, String key)
  {
    Properties props = new Properties();
    try {
      InputStream in = new BufferedInputStream(new FileInputStream(filePath));
      props.load(in);
      String value = props.getProperty(key);
      System.out.println(key + "=" + value);
      return value;
    } catch (Exception e) {
      e.printStackTrace();
    }return null;
  }
  
  //默认读取webConfig.properties下的值
  public static String readValue(String key)
  {
    Properties props = new Properties();
    try {
      InputStream in = new BufferedInputStream(new FileInputStream(filePath));
      props.load(in);
      String value = props.getProperty(key);
      System.out.println(key + "=" + value);
      return value;
    } catch (Exception e) {
      e.printStackTrace();
    }return null;
  }
  
  /**
  * @Title: isModifiedValue 
  * @Description:重新获取配置文件修改时间，如果修改了，则返回真,同时将修改时间更新
  * @param key
  * @return    描述 
  * @return boolean    返回类型 
  * @throws
   */
  public static boolean isModifiedValue()
  {
  	boolean flag = false;
  	long lastModifiedTime = new File(filePath).lastModified();
  	if(lastModifiedTime > curModifiedTime) {
  		curModifiedTime= lastModifiedTime;
  		flag = true;
  	}
  	return flag;
  }
}