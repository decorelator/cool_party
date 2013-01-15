package com.kmware.hrm.model;

import java.util.List;

public class Project extends BaseModel {

	private String sData;
	private String eData;
	private List<People> people;

	public Project(int id, String project) {
		super(id, project);
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

	public int getCount() {
		return people.size();

	}

	public List<People> getPeople() {
		return people;
	}

	public void setPeople(List<People> people) {
		this.people = people;
	}

}
