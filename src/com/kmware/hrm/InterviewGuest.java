package com.kmware.hrm;

import java.util.GregorianCalendar;
import com.kmware.hrm.model.Interviewer;
import com.kmware.hrm.model.Position;
import com.kmware.hrm.model.Project;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.format.DateFormat;
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

		interview = new Interviewer();
		
		tv_Name = (TextView) findViewById(R.id.tv_interviewer_name);
		tv_Position = (TextView) findViewById(R.id.tv_interviewer_position);
		tv_Date = (TextView) findViewById(R.id.tv_interviewer_date);
		tv_Time = (TextView) findViewById(R.id.tv_interviewer_time);

		enterData();
		
	}

	private void enterData() {
		try {
//			interview.setInterview(db.getInterview(i));

			tv_Name.setText(interview.getName());
			bar.setTitle(tv_Name.getText().toString());


			String parser = interview.getDate();
			if (parser != null) {
				String[] date = parser.split(":");
				GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
				tv_Date.setText("" + DateFormat.format("dd MMMM yyyy", calendar.getTime()));
			}

			tv_Time.setText(interview.getTime());
			// tv_Address

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
