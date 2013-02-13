package com.kmware.hrm.model;

public class Roles {

	private static final String LOGTAG = Roles.class.getSimpleName();

	private int appId;
	private int roleId;
	private String roleName;
	private String loweredRoleName;
	private String description;

	public Roles(){
	}
	
	public Roles(String name){
		this.roleName = name;
	}
	
	public Roles(int id, String name){
		this.roleId = id;
		this.roleName = name;
	}
	
	public int getAppId() {
		return appId;
	}

	public void setAppId(int appId) {
		this.appId = appId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getLoweredRoleName() {
		return loweredRoleName;
	}

	public void setLoweredRoleName(String loweredRoleName) {
		this.loweredRoleName = loweredRoleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
