package com.kmware.hrm;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class ProjectInfo extends ZActivity {
	public static String LOGTAG = ProjectInfo.class.getSimpleName();

	TextView tv_project_Name;
	TextView tv_project_status;
	TextView tv_project_startdate;
	TextView tv_project_enddate;
	TextView tv_project_email;
	TextView tv_project_phone;
	TextView tv_project_skype;
	TextView tv_project_description;
	ListView lv_project_employees;

	String extra;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.project);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		getExtra();

		setTitle(getResources().getString(R.string.people_info));

		init();

	}

	private void init() {

		tv_project_Name = (TextView) findViewById(R.id.tv_project_name);
		tv_project_status = (TextView) findViewById(R.id.tv_project_status);
		tv_project_startdate = (TextView) findViewById(R.id.tv_project_startdate);
		tv_project_enddate = (TextView) findViewById(R.id.tv_project_enddate);
		tv_project_email = (TextView) findViewById(R.id.tv_project_email);
		tv_project_phone = (TextView) findViewById(R.id.tv_project_phone);
		tv_project_skype = (TextView) findViewById(R.id.tv_project_skype);
		tv_project_description = (TextView) findViewById(R.id.tv_project_description);
		lv_project_employees = (ListView) findViewById(R.id.lv_project_employees);

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
