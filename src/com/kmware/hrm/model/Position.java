package com.kmware.hrm.model;

public class Position extends BaseModel {

	private int work_load;
	private int department;
	private int project;
	private String description;

	public Position() {
	}

	public Position(int id, String name) {
		super(id, name);
	}
	
	public Position(int id, String name, int work_load, int department, int project, String description) {
		super(id, name);
		this.work_load = work_load;
		this.department = department;
		this.project = project;
		this.description = description;
	}
	
	public void setPosition(Position position) {
		setName(position.getName());
		this.work_load = position.getWork_load();
		this.department = position.getDepartment();
		this.project = position.getProject();
		this.description = position.getDescription();
	}

	public int getWork_load() {
		return work_load;
	}

	public void setWork_load(int work_load) {
		this.work_load = work_load;
	}

	public int getDepartment() {
		return department;
	}

	public void setDepartment(int department) {
		this.department = department;
	}

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
