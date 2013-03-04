package com.kmware.hrm;

import java.util.ArrayList;
import java.util.List;

import com.kmware.hrm.model.Department;
import com.kmware.hrm.model.Position;
import com.kmware.hrm.model.Project;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class EditPosition extends ZActivity {
	public static String LOGTAG = EditPosition.class.getSimpleName();

	EditText edt_PositionTitle;
	TextView tv_workLoad;
	SeekBar sb_workLoad;
	Spinner sp_Departments;
	Spinner sp_Projects;
	EditText edt_PositionDescription;
	List<Project> projects;
	List<Department> departments;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_position);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		setTitle(getResources().getString(R.string.position_add), android.R.drawable.ic_input_add);
		addprefBarBtn(android.R.drawable.ic_menu_save, new OnClickListener() {

			@Override
			public void onClick(View v) {
				save();
			}
		});

		init();

	}

	private void init() {

		edt_PositionTitle = (EditText) findViewById(R.id.edt_position_name);
		sb_workLoad = (SeekBar) findViewById(R.id.sb_position_work_load);
		sb_workLoad.setMax(101);
		sb_workLoad.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				tv_workLoad.setText("" + sb_workLoad.getProgress() + "%");
			}
		});

		tv_workLoad = (TextView) findViewById(R.id.tv_position_work_load);
		tv_workLoad.setText("" + sb_workLoad.getProgress() + "%");
		sp_Departments = (Spinner) findViewById(R.id.sp_position_depoartments);

		String[] str = this.getResources().getStringArray(R.array.people_facility);
		departments = new ArrayList<Department>();
		departments.add(new Department(0, getResources().getString(R.string.sp_none)));
		for (int i = 1; i <= str.length; i++) {
			departments.add(new Department(i, str[i - 1]));
		}

		ArrayAdapter<Department> dep_adapter = new ArrayAdapter<Department>(this, android.R.layout.simple_spinner_item, departments);
		dep_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_Departments.setAdapter(dep_adapter);

		sp_Projects = (Spinner) findViewById(R.id.sp_position_projects);
		projects = new ArrayList<Project>();
		projects.add(new Project(0, getResources().getString(R.string.sp_none)));

		projects.addAll(Test_Data.list_projects);
		
		ArrayAdapter<Project> project_adapter = new ArrayAdapter<Project>(this, android.R.layout.simple_spinner_item, projects);
		project_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_Projects.setAdapter(project_adapter);

		edt_PositionDescription = (EditText) findViewById(R.id.edt_position_description);

	}

	private void save() {

		if (edt_PositionTitle.getText().toString().trim().length() > 0) {
			Position position = new Position();

			position.setName(edt_PositionTitle.getText().toString());
			position.setWork_load(sb_workLoad.getProgress());
			position.setDepartment(((Department) sp_Departments.getSelectedItem()).getId());
			position.setProject(((Project) sp_Projects.getSelectedItem()).getId());
			position.setDescription(edt_PositionDescription.getText().toString());

			Test_Data.list_position.add(position);
			setResult(RESULT_OK);
			finish();
		} else {
			getDialog().showWarning(this, "Enter the Title");
		}

	}

}
