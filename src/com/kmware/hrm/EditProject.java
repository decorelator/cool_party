package com.kmware.hrm;

import java.util.ArrayList;

import com.kmware.hrm.actionbar.ActionBar;

import model.BaseModel;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditProject extends ZActivity {
	public static String LOGTAG = EditProject.class.getSimpleName();
	
	EditText edt_Name;
	Spinner sp_Status;
	EditText edt_Email;
	EditText edt_Phone;
	EditText edt_Skype;
	EditText edt_StartDate;
	EditText edt_EndDate;
	ListView lv_Employee;
	
	private String extra;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_project);
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
		 
		 if (extra.toString().length() != 0 ){
			 setTitle(getResources().getString(R.string.project_edit),android.R.drawable.ic_input_add);
		 } else{
			 setTitle(getResources().getString(R.string.project_add),android.R.drawable.ic_input_add);
		 }
			 
		 init();
	}
	private void init() {

		sp_Status = (Spinner) findViewById(R.id.sp_project_status);
		edt_Name = (EditText) findViewById(R.id.edt_project_name);
		edt_Email = (EditText) findViewById(R.id.edt_project_email);
		edt_Phone = (EditText) findViewById(R.id.edt_project_tel);
		edt_Skype = (EditText) findViewById(R.id.edt_project_skype);
		edt_StartDate = (EditText) findViewById(R.id.edt_project_start_date);
		edt_EndDate = (EditText) findViewById(R.id.edt_project_end_date);
		lv_Employee = (ListView) findViewById(R.id.lv_project_employee);
		sp_Status.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		
		ArrayList<BaseModel> dataList = new ArrayList<BaseModel>();
		for (int i = 1; i <= 20; i++) {
			dataList.add(new BaseModel(i, "" + i * 1000));
		}
		CustomContainerAdapter listAdapter = new CustomContainerAdapter(this, dataList,
				R.layout.list_container_row);

		// настраиваем список
		
		lv_Employee.setAdapter(listAdapter);
		
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
	
	private void save(){
		
	}
}