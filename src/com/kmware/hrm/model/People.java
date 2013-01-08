package com.kmware.hrm.model;

import java.util.List;

public class People extends BaseModel{
	private String role;
	private List<BaseModel> projects;
	public People(int id, String name) {
		super(id, name);
		// TODO Auto-generated constructor stub
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<BaseModel> getProjects() {
		return projects;
	}
	public void setProjects(List<BaseModel> projects) {
		this.projects = projects;
	}

}
