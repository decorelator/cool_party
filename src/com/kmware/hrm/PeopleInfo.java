package com.kmware.hrm;

import java.util.GregorianCalendar;
import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.People;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

public class PeopleInfo extends ZActivity {
	public static String LOGTAG = PeopleInfo.class.getSimpleName();

	TextView tv_People_Name;
	TextView tv_People_LastName;
	TextView tv_People_Position;
	TextView tv_People_Status;
	TextView tv_People_Email;
	TextView tv_People_Phone;
	TextView tv_People_Skype;
	TextView tv_People_Info;
	ListView lv_People_Projects;

	private DatabaseHandler db;
	private People person;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.people);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));

		setTitle(getResources().getString(R.string.people_info));
		init();

	}

	private void init() {

		db = new DatabaseHandler(this);

		tv_People_Name = (TextView) findViewById(R.id.tv_people_name);
		bar.setTitle(tv_People_Name.getText().toString());
		tv_People_LastName = (TextView) findViewById(R.id.tv_people_lastname);
		tv_People_Position = (TextView) findViewById(R.id.tv_people_position);
		tv_People_Status = (TextView) findViewById(R.id.tv_people_status);
		tv_People_Email = (TextView) findViewById(R.id.tv_people_email);
		tv_People_Phone = (TextView) findViewById(R.id.tv_people_phone);
		tv_People_Skype = (TextView) findViewById(R.id.tv_people_skype);
		tv_People_Info = (TextView) findViewById(R.id.tv_people_description);
		lv_People_Projects = (ListView) findViewById(R.id.lv_people_projects);

		try {
			person = new People();
			person.setPerson(db.getPerson(getIntent().getIntExtra("ID", 0)));
			tv_People_Name.setText(person.getName());
			tv_People_LastName.setText(person.getLastname());
			tv_People_Position.setText(db.getPosition(person.getPosition()).getName());
			tv_People_Status.setText("" + this.getResources().getStringArray(R.array.people_status)[person.getStatus_id()]);
			tv_People_Email.setText(person.getEmail());
			if (person.getPhone() != 0) {
				tv_People_Phone.setText("" + person.getPhone());
			} else
				tv_People_Phone.setText("");
			tv_People_Skype.setText(person.getSkype());

			String parser = person.getEmployment_date();// db.getPerson(getIntent().getIntExtra("ID",
														// 0)).getEmployment_date();
			if (parser != null) {

				String[] date = parser.split(":");
				GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
				tv_People_Info.setText("Employee day: " + DateFormat.format("dd MMMM yyyy", calendar.getTime()));
			}
		} catch (SQLiteException ex) {
			ex.printStackTrace();
			Log.w(LOGTAG, "People DB have not id = " + getIntent().getIntExtra("ID", 0));
		}
		// lv_People_Projects.setText(person.getName());
	}
}
