package com.kmware.hrm;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditInterview extends ZActivity {
	public static String LOGTAG = EditInterview.class.getSimpleName();

	EditText edt_NameInterviewer;
	Spinner sp_Project;
	Spinner sp_Position;
	EditText edt_Date;
	EditText edt_Time;
	EditText edt_Description;

	private String extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_interview);
		super.onCreate(savedInstanceState);
		getExtra();
		if (extra.toString().length() != 0) {
			setTitle(getResources().getString(R.string.interview_edit),
					android.R.drawable.ic_input_add);

		} else {
			setTitle(getResources().getString(R.string.interview_add),
					android.R.drawable.ic_input_add);
		}

		addprefBarBtn(android.R.drawable.ic_menu_save, new OnClickListener() {

			@Override
			public void onClick(View v) {
				save();
			}
		});

		init();

	}

	private void init() {

		edt_NameInterviewer = (EditText) findViewById(R.id.edt_interview_name);
		sp_Project = (Spinner) findViewById(R.id.sp_interview_project);
		sp_Position = (Spinner) findViewById(R.id.sp_interview_position);
		edt_Date = (EditText) findViewById(R.id.edt_interview_date);
		edt_Time = (EditText) findViewById(R.id.edt_interview_time);
		edt_Description = (EditText) findViewById(R.id.edt_interview_description);

		sp_Project.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		sp_Position.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
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

	private void save() {
		setResult(RESULT_OK);
		finish();
	}
}
