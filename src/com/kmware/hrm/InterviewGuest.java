package com.kmware.hrm;

import java.util.GregorianCalendar;
import java.util.List;

import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.Interviewer;
import com.kmware.hrm.model.Position;
import com.kmware.hrm.model.Project;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

public class InterviewGuest extends ZActivity {
	public static String LOGTAG = InterviewGuest.class.getSimpleName();

	TextView tv_Name;
	TextView tv_Phone;
	TextView tv_Project;
	TextView tv_Position;
	TextView tv_Date;
	TextView tv_Time;
	TextView tv_Address;
	TextView tv_Description;

	DatabaseHandler db;
	Interviewer interview;
	Project project;
	Position position;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.interview_guest);
		super.onCreate(savedInstanceState);
		bar.setTitle(((TextView) findViewById(R.id.tv_interviewer_name)).getText().toString());
		init();

	}

	private void init() {

		db = new DatabaseHandler(this);
		interview = new Interviewer();
		
		tv_Name = (TextView) findViewById(R.id.tv_interviewer_name);
		tv_Phone = (TextView) findViewById(R.id.tv_interviewer_phone);
		tv_Project = (TextView) findViewById(R.id.tv_interviewer_project);
		tv_Position = (TextView) findViewById(R.id.tv_interviewer_position);
		tv_Date = (TextView) findViewById(R.id.tv_interviewer_date);
		tv_Time = (TextView) findViewById(R.id.tv_interviewer_time);
		tv_Address = (TextView) findViewById(R.id.tv_interviewer_address);
		tv_Description = (TextView) findViewById(R.id.tv_interviewer_description);

		enterData();
		
	}

	private void enterData() {
		try {
			List<Interviewer> in = db.getAllInterviewer();
			int i = in.get(0).getId();
			interview.setInterview(db.getInterview(i));

			tv_Name.setText(interview.getName());
			bar.setTitle(tv_Name.getText().toString());

			if (interview.getPhone() != 0)
				tv_Phone.setText("" + interview.getPhone());
			try {
				tv_Project.setText(db.getProject(interview.getProject()).getName());
			} catch (SQLiteException ex) {
				ex.printStackTrace();
				Log.w(LOGTAG, "Projetc DB have not project with id = " + interview.getProject());
			}
			try {
				tv_Position.setText(db.getPosition(interview.getPosition()).getName());
			} catch (SQLiteException ex) {
				ex.printStackTrace();
				Log.w(LOGTAG, "Position DB have not position with id = " + interview.getProject());
			}

			String parser = interview.getDate();
			if (parser != null) {
				String[] date = parser.split(":");
				GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
				tv_Date.setText("" + DateFormat.format("dd MMMM yyyy", calendar.getTime()));
			}

			tv_Time.setText(interview.getTime());
			// tv_Address
			tv_Description.setText(interview.getDescription());

		} catch (SQLiteException ex) {
			ex.printStackTrace();

		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case DialogInterface.BUTTON_POSITIVE:
						finish();
						System.exit(0);
						break;
					}
				}
			};

			MessageToast.showDialog(this, getString(R.string.app_exit), getString(R.string.app_exit_mess), listener);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

}
