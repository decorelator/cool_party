package com.kmware.hrm;

import java.util.GregorianCalendar;
import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.Project;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
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

	private DatabaseHandler db;
	private Project project;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.project);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));

		setTitle(getResources().getString(R.string.people_info));

		init();

	}

	private void init() {

		db = new DatabaseHandler(this);

		tv_project_Name = (TextView) findViewById(R.id.tv_project_name);
		tv_project_status = (TextView) findViewById(R.id.tv_project_status);
		tv_project_startdate = (TextView) findViewById(R.id.tv_project_startdate);
		tv_project_enddate = (TextView) findViewById(R.id.tv_project_enddate);
		tv_project_email = (TextView) findViewById(R.id.tv_project_email);
		tv_project_phone = (TextView) findViewById(R.id.tv_project_phone);
		tv_project_skype = (TextView) findViewById(R.id.tv_project_skype);
		tv_project_description = (TextView) findViewById(R.id.tv_project_description);
		lv_project_employees = (ListView) findViewById(R.id.lv_project_employees);

		try {
			project = new Project();
			project.setProject(db.getProject(getIntent().getIntExtra("ID", 0)));

			tv_project_Name.setText(project.getName());
			bar.setTitle(tv_project_Name.getText().toString());
			int i = 0;
			String[] status = this.getResources().getStringArray(R.array.status_value);
			while (!status[i].equals(String.valueOf(project.getStatus_id())) && i < status.length) {
				i++;
			}
			String str = this.getResources().getStringArray(R.array.status)[i];
			tv_project_status.setText(str);

			String parser = "" + project.getsData();
			String[] date = parser.split(":");
			GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
			tv_project_startdate.setText(DateFormat.format("dd MMMM yyyy", calendar.getTime()));
			parser = "" + project.geteData();
			date = parser.split(":");
			calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
			tv_project_enddate.setText(DateFormat.format("dd MMMM yyyy", calendar.getTime()));

			tv_project_email.setText(project.getEmail());
			if (project.getPhone() != 0) {
				tv_project_phone.setText("" + project.getPhone());
			} else {
				tv_project_phone.setText("");
			}
			tv_project_skype.setText(project.getSkype());
			tv_project_description.setText(project.getDescription());
			// lv_project_employees.setText(

		} catch (SQLiteException ex) {
			ex.printStackTrace();
			Log.w(LOGTAG, "Project DB have not id = " + getIntent().getIntExtra("ID", 0));
		}

	}
}
