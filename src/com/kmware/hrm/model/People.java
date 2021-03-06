package com.kmware.hrm.model;

import java.util.List;

public class People extends BaseModel {
	private String role;
	private int position;
	private String lastname;
	private String email;
	private int phone;
	private String skype;
	private String employment_date;
	private int status_id;

	private List<Project> projects;
	
	public People() {
	}
	
	public People(String name, String lastname) {
		super(name);
		this.lastname = lastname;
	}
	
	public People(int id, String name, int position) {
		super(id, name);
		this.position = position;
	}

	public People(int id, String name) {
		super(id, name);
	}
	
	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getEmployment_date() {
		return employment_date;
	}

	public void setEmployment_date(String employment_date) {
		this.employment_date = employment_date;
	}

	public int getStatus_id() {
		return status_id;
	}

	public void setStatus_id(int status_id) {
		this.status_id = status_id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}
	
	public void setPerson(People person){
		setName(person.getName());
		this.role = person.getRole();
		this.position = person.getPosition();
		this.lastname = person.getLastname();
		this.email = person.getEmail();
		this.phone = person.getPhone();
		this.skype = person.getSkype();
		this.employment_date = person.getEmployment_date();
		this.status_id = person.getStatus_id();
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
}
