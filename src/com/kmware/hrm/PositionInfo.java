package com.kmware.hrm;

import com.kmware.hrm.model.Position;
import android.os.Bundle;
import android.widget.TextView;

public class PositionInfo extends ZActivity {
	public static String LOGTAG = PositionInfo.class.getSimpleName();

	TextView tv_position_title;
	TextView tv_position_work_load;
	TextView tv_position_department;
	TextView tv_position_project;
	TextView tv_position_description;

	private Position position;
	private int position_id;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.position);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));

		init();

	}

	private void init() {

		position_id = getIntent().getIntExtra("ID", 0);
		position = new Position();
		position.setPosition(Test_Data.list_position.get(position_id));
		bar.setTitle(position.getName());

		tv_position_title = (TextView) findViewById(R.id.tv_position_name);
		tv_position_work_load = (TextView) findViewById(R.id.tv_position_work);
		tv_position_department = (TextView) findViewById(R.id.tv_position_department);
		tv_position_project = (TextView) findViewById(R.id.tv_position_project);
		tv_position_description = (TextView) findViewById(R.id.tv_position_description);

		tv_position_title.setText(position.getName());
		tv_position_work_load.setText(position.getWork_load() + "%");
		if (position.getDepartment() != 0) {
			tv_position_department.setText(getResources().getStringArray(R.array.people_facility)[position.getDepartment()-1]);
		} else
			tv_position_department.setText(getResources().getString(R.string.sp_none));
		int i = 0;
		while (Test_Data.list_projects.get(i).getId() != Test_Data.list_position.get(position.getProject()).getProject() && i < Test_Data.list_projects.size()) {
			i++;
		}
		if (i != 0) {
			tv_position_project.setText(Test_Data.list_projects.get(i).getName());
		} else {
			tv_position_project.setText(getResources().getString(R.string.sp_none));
		}
		tv_position_description.setText(position.getDescription());

	}

}
