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
			System.out.println("��ǰ�ļ��޸�ʱ��Ϊ " +curModifiedTime);
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
  
  //Ĭ�϶�ȡwebConfig.properties�µ�ֵ
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
  * @Description:���»�ȡ�����ļ��޸�ʱ�䣬����޸��ˣ��򷵻���,ͬʱ���޸�ʱ�����
  * @param key
  * @return    ���� 
  * @return boolean    �������� 
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