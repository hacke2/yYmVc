package edu.cqut.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil
{
	private static final String filePath = PropertiesUtil.class.getClassLoader().getResource("webConfig.properties").getFile();
	private static long curModifiedTime;
	private static Map<String, String> propertiesMap;
	static {
		try {
			curModifiedTime = new File(filePath).lastModified();
			
			//��ʼ��
			propertiesMap = new HashMap<String, String>();
			Properties props = new Properties();
			InputStream in = new BufferedInputStream(new FileInputStream(filePath));
      props.load(in);
      for(Object t:props.keySet()){
      	propertiesMap.put(t.toString(), props.getProperty(t.toString()));
  		} 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
  public static String getRealValue(String filePath, String key)
  {
    Properties props = new Properties();
    try {
      InputStream in = new BufferedInputStream(new FileInputStream(filePath));
      props.load(in);
      String value = props.getProperty(key);
      return value;
    } catch (Exception e) {
      e.printStackTrace();
    }return null;
  }
  
  //Ĭ�϶�ȡwebConfig.properties�µ�ֵ
  public static String getRealValue(String key)
  {
    Properties props = new Properties();
    try {
      InputStream in = new BufferedInputStream(new FileInputStream(filePath));
      props.load(in);
      String value = props.getProperty(key);
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
  
  public static String getValue(String key) {
  	if(propertiesMap.containsKey(key)) {
  		return propertiesMap.get(key);
  	} else {
  		return null;
  	}
  }
}