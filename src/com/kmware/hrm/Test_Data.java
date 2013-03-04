package com.kmware.hrm;

import java.util.ArrayList;
import java.util.List;

import com.kmware.hrm.model.Interviewer;
import com.kmware.hrm.model.People;
import com.kmware.hrm.model.Position;
import com.kmware.hrm.model.Project;

public class Test_Data {

	public static List<People> list_people;
	public static List<Project> list_projects;
	public static List<Position> list_position;
	public static List<Interviewer> list_interview;
	
	public Test_Data() {
		setPeopleList();
		setProjectsList();
		setPositionsList();
		setInterviewsList();
	}

	public void setPeopleList(){
		list_people = new ArrayList<People>();
		list_people.add(new People(1, "Ivan", "Sverdlov", "sv@mail.com", 1));
		list_people.add(new People(3, "Dima", "Nikitin", "nik@mail.com", 1));
		list_people.add(new People(4, "Alexander", "Plachtiy", "pl@mail.com", 1));
		list_people.add(new People(6, "Bill", "Clinton", "Clinton@mail.com", 2));
		list_people.add(new People(7, "Alexey", "Bogatirev", "alex@mail.com", 2));
	}
	
	public void setProjectsList(){
		list_projects = new ArrayList<Project>();
		list_projects.add(new Project(1,"HRM", "KM-Ware" ,"12:11:2012","15:3:2013", "None"));
		list_projects.add(new Project(2,"eFarmer", "KM-Ware" ,"8:3:2010","1:1:2015", "None"));
		list_projects.add(new Project(4,"TTK", "Moskow tel" ,"9:8:2011","1:4:2013", "Yahoo"));
	}
	
	private void setPositionsList() {
		list_position = new ArrayList<Position>();
		list_position.add(new Position(1, "Developer Java", 100, 1, 2, ""));
		list_position.add(new Position(2, "Developer Java", 100, 2, 1, ""));
		list_position.add(new Position(3, "Developer PHP", 50, 2, 2, ""));
		list_position.add(new Position(5, "QA PHP", 50, 2, 2, ""));
	}
	
	private void setInterviewsList() {
		list_interview = new ArrayList<Interviewer>();
		list_interview.add(new Interviewer(1, "Developer java for HRM", "12:4:2013", "11:30", 1, 2, 2));
		list_interview.add(new Interviewer(3, "Developer php for eFarmer", "15:4:2013", "10:00", 6, 3, 0));
		list_interview.add(new Interviewer(4, "QA PHP for eFarmer", "15:4:2013", "11:00", 4, 5, 1));
		
	}
}
