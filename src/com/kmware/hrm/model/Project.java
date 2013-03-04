package com.kmware.hrm.model;

public class Project extends BaseModel {

	private String customer;
	private String sData;
	private String eData;
	private String description;

	public Project() {
	}

	public Project(int id, String name) {
		super(id, name);
	}

	public  Project(int id, String name, String customer, String sDate, String eDate, String description) {
		super(id, name);
		this.customer = customer;
		this.sData = sDate;
		this.eData = eDate;
		this.description = description;
	}
	
	public void setProject(Project project) {
		setName(project.getName());
		this.customer = project.getCustomer();
		this.sData = project.getsData();
		this.eData = project.geteData();
		this.description = project.getDescription();
	}
	
	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}

	public String getsData() {
		return sData;
	}

	public void setsData(String sData) {
		this.sData = sData;
	}

	public String geteData() {
		return eData;
	}

	public void seteData(String eData) {
		this.eData = eData;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;

	}
	
	@Override
	public String toString() {
//		return super.toString();
		return getName();
	}
}
