package edu.cqut.util;

import java.util.HashMap;
import java.util.Map;

/**
 *对配置文件里面的信息进行封装,其中method默认为execute，返回结果默认为success
 */
public class ActionConfig {
    private String name;
    private String clzName;
    private String method;
    private Map<String, String> resultMap = new HashMap<String, String>();

    public ActionConfig() {
       super();
    }
 
    public ActionConfig(String name, String clzName, String method,
           Map<String, String> resultMap) {
       super();
       this.name = name;
       this.clzName = clzName;
       this.method = method;
       this.resultMap = resultMap;
    }
 
    public String getName() {
       return name;
    }
 
    public void setName(String name) {
       this.name = name;
    }
 
    public String getClzName() {
       return clzName;
    }
 
 
    public void setClzName(String clzName) {
       this.clzName = clzName;
    }
    public String getMethod() {
       return method;
 
    }
 
    public void setMethod(String method) {
       this.method = method;
    }
 
    public Map<String, String> getResultMap() {
       return resultMap;
    }
 
    public void setResultMap(Map<String, String> resultMap) {
       this.resultMap = resultMap;
    }
 
}
