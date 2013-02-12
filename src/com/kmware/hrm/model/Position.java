package com.kmware.hrm.model;

public class Position extends BaseModel {

	private String description;

	public Position() {
	}

	public Position(int id, String name) {
		super(id, name);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
