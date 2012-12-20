package com.kmware.hrm;

import android.os.Bundle;
import android.widget.TextView;

public class InterviewGuest extends ZActivity {
	public static String LOGTAG = InterviewGuest.class.getSimpleName();

	TextView tv_Name;
	TextView tv_Project;
	TextView tv_Position;
	TextView tv_Date;
	TextView tv_Time;
	TextView tv_Address;
	TextView tv_Description;
	
	private String extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.interview_guest);
		super.onCreate(savedInstanceState);
		bar.setTitle(((TextView)findViewById(R.id.tv_guest_name)).getText().toString());
		getExtra();
		init();

	}

	private void init() {
		
		tv_Name = (TextView) findViewById(R.id.guest_name);
		tv_Project = (TextView) findViewById(R.id.guest_project);
		tv_Position = (TextView) findViewById(R.id.guest_position);
		tv_Date = (TextView) findViewById(R.id.guest_date);
		tv_Time = (TextView) findViewById(R.id.guest_time);
		tv_Address = (TextView) findViewById(R.id.guest_address);
		tv_Description = (TextView) findViewById(R.id.guest_description);
		
		
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
