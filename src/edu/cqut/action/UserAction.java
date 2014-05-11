package edu.cqut.action;

import edu.cqut.support.ActionSupport;
import edu.cqut.util.StringUtil;


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
	
	public String logon() {
		result = "我是返回结果----退出成功";
		return SUCCESS;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserName() {
		return userName;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getResult() {
		return result;
	}

}
