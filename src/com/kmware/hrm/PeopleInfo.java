package com.kmware.hrm;

import com.kmware.hrm.model.People;
import android.os.Bundle;
import android.widget.TextView;

public class PeopleInfo extends ZActivity {
	public static String LOGTAG = PeopleInfo.class.getSimpleName();

	TextView tv_People_Name;
	TextView tv_People_LastName;
	TextView tv_People_Email;
	TextView tv_People_Facility;

	private People person;
	private int position;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.people);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));

		init();

	}

	private void init() {

		position = getIntent().getIntExtra("ID", 0);
		person = new People();
		person.setPerson(Test_Data.list_people.get(position));

		tv_People_Name = (TextView) findViewById(R.id.tv_people_name);
		bar.setTitle(""+person.getLastname() + " " + person.getName());
		tv_People_LastName = (TextView) findViewById(R.id.tv_people_lastname);
		tv_People_Email = (TextView) findViewById(R.id.tv_people_email);
		tv_People_Facility = (TextView) findViewById(R.id.tv_people_facility);
		
		tv_People_Name.setText(person.getName());
		tv_People_LastName.setText(person.getLastname());
		tv_People_Email.setText(person.getEmail());
		if (person.getFacility() != 0) {
			tv_People_Facility.setText("" + getResources().getStringArray(R.array.people_facility)[person.getFacility()-1]);
		} else
			tv_People_Facility.setText("");
	}
}
