package edu.cqut.action;

import edu.cqut.support.ActionSupport;
import edu.cqut.util.StringUtil;


public class UserAction extends ActionSupport {
	
	private String userName;
	private String password;
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
		System.out.println("Ïú»Ùsession");
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

}
