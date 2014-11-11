yYmVc
=====

第一款自己独自开发的JAVA MVC框架

模仿struts 2.0配置文件

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
