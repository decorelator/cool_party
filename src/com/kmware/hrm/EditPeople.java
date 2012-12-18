package com.kmware.hrm;

import android.app.ExpandableListActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;


public class EditPeople extends ZActivity {

	public static String LOGTAG = EditPeople.class.getSimpleName();

	Spinner sp_Status;
	Spinner sp_Position;
	EditText edt_Name;
	EditText edt_Email;
	EditText edt_Telephone;
	EditText edt_Skype;
	EditText edt_EmployeeDate;
	ExpandableListView expl_Projects;
	
	private String extra;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_people);
		super.onCreate(savedInstanceState);
		addprefBarBtn(android.R.drawable.ic_menu_save, new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				save();
				finish();
			}
		});
		getExtra();
		init();

	}

	private void init() {
		
		sp_Status = (Spinner) findViewById(R.id.sp_people_status);
		
//		if (sp_Status.getSelectedItem().toString().equals(getResources().getString(R.string.people_employee))){
//			findViewById(R.id.rl_people).setVisibility(View.VISIBLE);
//		}
		if (sp_Status.getSelectedItemPosition() == 2)
				{
			findViewById(R.id.rl_people).setVisibility(View.VISIBLE);
		}
		
		edt_Name = (EditText) findViewById(R.id.edt_people_name);
		sp_Position = (Spinner) findViewById(R.id.sp_people_position);
		edt_Email = (EditText) findViewById(R.id.edt_people_email);
		edt_Telephone = (EditText) findViewById(R.id.edt_people_tel);
		edt_Skype = (EditText) findViewById(R.id.edt_people_skype);
		edt_EmployeeDate = (EditText) findViewById(R.id.edt_people_date_in);
		expl_Projects = (ExpandableListView) findViewById(R.id.exl_people_projects);
		
	}
	
	private void getExtra() {
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			try {
				extra = extras.getString(Extras.ADD_INTENT);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
		}
	}
	private void save(){
		
	}

}
