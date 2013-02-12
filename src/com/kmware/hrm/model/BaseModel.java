package com.kmware.hrm.model;

public class BaseModel {
	private int id;
	private String name;

	public BaseModel() {
	}

	public BaseModel(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public BaseModel(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
