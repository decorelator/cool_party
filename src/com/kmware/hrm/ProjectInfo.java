package com.kmware.hrm;

import java.util.GregorianCalendar;
import com.kmware.hrm.model.Project;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.widget.TextView;

public class ProjectInfo extends ZActivity {
	public static String LOGTAG = ProjectInfo.class.getSimpleName();

	TextView tv_project_Title;
	TextView tv_project_Customer;
	TextView tv_project_startdate;
	TextView tv_project_enddate;
	TextView tv_project_description;

	private Project project;
	// private int project_id;
	private int position;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.project);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));

		setTitle(getResources().getString(R.string.people_info));

		init();

	}

	private void init() {
		// project_id = getIntent().getIntExtra("ID", 0);
		position = getIntent().getIntExtra("ID", 0);
		project = new Project();
		// project.setProject(Test_Data.list_projects.get(project_id));
		project.setProject(Test_Data.list_projects.get(position));

		tv_project_Title = (TextView) findViewById(R.id.tv_project_title);
		tv_project_Customer = (TextView) findViewById(R.id.tv_project_customer);
		tv_project_startdate = (TextView) findViewById(R.id.tv_project_startdate);
		tv_project_enddate = (TextView) findViewById(R.id.tv_project_enddate);
		tv_project_description = (TextView) findViewById(R.id.tv_project_description);

		tv_project_Title.setText(project.getName());
		bar.setTitle(tv_project_Title.getText().toString());
		tv_project_Customer.setText(project.getCustomer());

		String parser = "" + project.getsData();
		String[] date = parser.split(":");
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
		tv_project_startdate.setText(DateFormat.format("dd MMMM yyyy", calendar.getTime()));
		parser = "" + project.geteData();
		date = parser.split(":");
		calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
		tv_project_enddate.setText(DateFormat.format("dd MMMM yyyy", calendar.getTime()));
		tv_project_description.setText(project.getDescription());

	}
}
