package model;

import java.util.List;

public class Project extends BaseModel{

	private String sData;
	private String eData;
	private List<People> people;
	
	public Project(int id, String name) {
		super(id, name);
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

}
