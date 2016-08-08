package com.dc.bean;

public class LoginBean {
	private String user_name = null;
	private String user_password = null;
	private String org_id = null;
	private String net_id = null;
	private String login_no = null;
	
	public String getLogin_no() {
		return login_no;
	}
	public void setLogin_no(String loginNo) {
		login_no = loginNo;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String userName) {
		user_name = userName;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String userPassword) {
		user_password = userPassword;
	}
	public String getOrg_id() {
		return org_id;
	}
	public void setOrg_id(String orgId) {
		org_id = orgId;
	}
	public String getNet_id() {
		return net_id;
	}
	public void setNet_id(String netId) {
		net_id = netId;
	}
	
}
