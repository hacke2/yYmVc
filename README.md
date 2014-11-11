yYmVc
=====

第一款自己独自开发的JAVA MVC框架

1.基于配置，降低耦合性
mvc.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<mvc>  
	<package>  
		<action name="login" method="login"  
			class="edu.cqut.action.UserAction">  
			<result name="success">/success.jsp</result>
			<result name="failed">/failed.jsp</result>  
		</action> 
		<action name="logon" method="logon"  
			class="edu.cqut.action.UserAction">  
			<result name="success">/logon.jsp</result>
		</action> 
	</package>  
</mvc>  
```
2.支持自定义后缀
webConfig.properties
```
action-suffix=action
```
3.模拟struts 2，无学习成本
```java
public class UserAction extends ActionSupport {
	
	private String userName;
	private String password;
	
	private String result;
	@Override
	public String execute() {
		return SUCCESS;
	}
	
	public String login() {
		if(StringUtil.isNotEmpty(userName) && StringUtil.isNotEmpty(password)) {
			System.out.println("userName: " +  userName + "password: " + password);
			return SUCCESS;
		} else {
			return FAILED;
		}
	}
	//其他省略....
}
```
