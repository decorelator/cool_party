package com.kmware.hrm.model;

public class Interviewer extends BaseModel {

	private String time;
	private String date;
	private int project;
	private int position;
	private int phone;
	private String description;

	public Interviewer() {
	}

	public Interviewer(int id, String name) {
		super(id, name);
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getProject() {
		return project;
	}

	public void setProject(int project) {
		this.project = project;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInterview(Interviewer interview) {
		setName(interview.getName());
		this.phone = interview.getPhone();
		this.project = interview.getProject();
		this.position = interview.getPosition();
		this.date = interview.getDate();
		this.time = interview.getTime();
		this.description = interview.getDescription();
	}

}
