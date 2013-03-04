package com.kmware.hrm.model;

public class Interviewer extends BaseModel {

	private String date;
	private String time;
	private int candidate;
	private int position;
	private int status;

	public Interviewer() {
	}

	public Interviewer(int id, String name) {
		super(id, name);
	}
	
	public Interviewer(int id, String name, String date, String time, int candidate, int position, int status) {
		super(id, name);
		this.date = date;
		this.time = time;
		this.candidate = candidate;
		this.position = position;
		this.status = status;
	}
	
	public void setInterview(Interviewer interview) {
		setName(interview.getName());
		this.date = interview.getDate();
		this.time = interview.getTime();
		this.candidate = interview.getCandidate();
		this.position = interview.getPosition();
		this.status = interview.getStatus();
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

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public int getCandidate() {
		return candidate;
	}

	public void setCandidate(int candidate) {
		this.candidate = candidate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}


	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}
	
}
