package com.kmware.hrm.model;

import java.util.List;

public class People extends BaseModel {
	private String role;
	private String position;
	private String status;

	private List<BaseModel> projects;

	public People(int id, String name, String position) {
		super(id, name);
		this.position = position;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
