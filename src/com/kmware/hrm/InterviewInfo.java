package com.kmware.hrm;

import java.util.GregorianCalendar;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;
import com.kmware.hrm.model.Interviewer;
import com.kmware.hrm.model.Position;
import com.kmware.hrm.model.Project;

public class InterviewInfo extends ZActivity {
	public static String LOGTAG = InterviewInfo.class.getSimpleName();

	TextView tv_interview_Name;
	TextView tv_interview_Date;
	TextView tv_interview_Time;
	TextView tv_interview_Candidate;
	TextView tv_interview_Position;
	TextView tv_interview_Status;

	Interviewer interview;
	Project project;
	Position position;

	private int position_id;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.interview_guest);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));

		init();
	}

	private void init() {

		interview = new Interviewer();
		position_id = getIntent().getIntExtra("ID", 0);
		interview.setInterview(Test_Data.list_interview.get(position_id));

		bar.setTitle(interview.getName());

		tv_interview_Name = (TextView) findViewById(R.id.tv_interviewer_name);
		tv_interview_Date = (TextView) findViewById(R.id.tv_interviewer_date);
		tv_interview_Time = (TextView) findViewById(R.id.tv_interviewer_time);
		tv_interview_Candidate = (TextView) findViewById(R.id.tv_interviewer_candidate);
		tv_interview_Position = (TextView) findViewById(R.id.tv_interviewer_position);
		tv_interview_Status = (TextView) findViewById(R.id.tv_interviewer_status);

		enterData();
	}

	private void enterData() {

		tv_interview_Name.setText(interview.getName());

		String parser = interview.getDate();
		if (parser != null) {
			String[] date = parser.split(":");
			GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
			tv_interview_Date.setText("" + DateFormat.format("dd MMMM yyyy", calendar.getTime()));
		}

		tv_interview_Time.setText(interview.getTime());

		int i = 0;
		while (Test_Data.list_people.get(i).getId() != interview.getCandidate() && i < Test_Data.list_people.size()) {
			i++;
		}
	//	if (i != -1) {
			tv_interview_Candidate.setText("" + Test_Data.list_people.get(i).getLastname() + " " + Test_Data.list_people.get(i).getName());
//		} else {
//			tv_interview_Candidate.setText(getResources().getString(R.string.sp_none));
//		}

		i = 0;
		while (Test_Data.list_position.get(i).getId() != interview.getPosition() && i < Test_Data.list_position.size()) {
			i++;
		}
//		if (i != -1) {
			tv_interview_Position.setText("" + Test_Data.list_position.get(i).getName());
//		} else {
//			tv_interview_Position.setText(getResources().getString(R.string.sp_none));
//		}
		tv_interview_Status.setText(getResources().getStringArray(R.array.interview_status)[interview.getStatus()]);
		
	}
}
