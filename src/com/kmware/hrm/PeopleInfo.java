package com.kmware.hrm;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class PeopleInfo extends ZActivity {
	public static String LOGTAG = PeopleInfo.class.getSimpleName();

	TextView tv_People_Name;
	TextView tv_People_Position;
	TextView tv_People_Email;
	TextView tv_People_Phone;
	TextView tv_People_Skype;
	TextView tv_People_Info;
	ListView lv_People_Projects;

	String extra;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.people);
		super.onCreate(savedInstanceState);
		getExtra();

		setTitle(getResources().getString(R.string.position_edit));

		init();

	}

	private void init() {

		tv_People_Name = (TextView) findViewById(R.id.tv_people_name);
		tv_People_Position = (TextView) findViewById(R.id.tv_people_position);
		tv_People_Email = (TextView) findViewById(R.id.tv_people_email);
		tv_People_Phone = (TextView) findViewById(R.id.tv_people_phone);
		tv_People_Skype = (TextView) findViewById(R.id.tv_people_skype);
		lv_People_Projects = (ListView) findViewById(R.id.lv_people_projects);

	}

	private void getExtra() {
		Bundle extras = getIntent().getExtras();
		extra = Extras.EMPTY_STRING;
		if (extras != null) {
			try {
				extra = extras.getString(Extras.ADD_INTENT);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

}
