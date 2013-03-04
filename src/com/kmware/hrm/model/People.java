package com.kmware.hrm.model;

public class People extends BaseModel {
	private String lastname;
	private String email;
	private int facility;
	
	public People() {
	}
	
	public People(String name, String lastname) {
		super(name);
		this.lastname = lastname;
	}
	
	public People(int id, String name) {
		super(id, name);
	}
	
	public People(int id, String name, String lastname, String email, int facility){
		super(id, name);
		this.lastname = lastname;
		this.email = email;
		this.facility = facility;
	}
	
	public void setPerson(People person){
		setName(person.getName());
		this.lastname = person.getLastname();
		this.email = person.getEmail();
		this.facility = person.getFacility();
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

	public int getFacility() {
		return facility;
	}

	public void setFacility(int facility) {
		this.facility = facility;
	}



	@Override
	public String toString() {
		return super.toString();
	}
	
}
