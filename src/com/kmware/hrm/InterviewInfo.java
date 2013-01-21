package com.kmware.hrm;

import android.os.Bundle;
import android.widget.TextView;

public class InterviewInfo extends ZActivity {
	public static String LOGTAG = InterviewInfo.class.getSimpleName();

	TextView tv_Name;
	TextView tv_Project;
	TextView tv_Position;
	TextView tv_Date;
	TextView tv_Time;
	TextView tv_Address;
	TextView tv_Description;

	String extra;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.interview_guest);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		bar.setTitle(((TextView)findViewById(R.id.tv_interviewer_name)).getText().toString());
		getExtra();

		init();

	}

	private void init() {

		tv_Name = (TextView) findViewById(R.id.tv_interviewer_name);
		tv_Project = (TextView) findViewById(R.id.tv_interviewer_project);
		tv_Position = (TextView) findViewById(R.id.tv_interviewer_position);
		tv_Date = (TextView) findViewById(R.id.tv_interviewer_date);
		tv_Time = (TextView) findViewById(R.id.tv_interviewer_time);
		tv_Address = (TextView) findViewById(R.id.tv_interviewer_address);
		tv_Description = (TextView) findViewById(R.id.tv_interviewer_description);

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
