package com.kmware.hrm;

import java.util.ArrayList;

import com.kmware.hrm.model.BaseModel;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
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
	ListView lv_Projects;
	LinearLayout ll_listview;

	private String extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_people);
		super.onCreate(savedInstanceState);
		getExtra();
		if (extra.toString().length() != 0) {
			setTitle(getResources().getString(R.string.people_edit),
					android.R.drawable.ic_input_add);
			addprefBarBtn(android.R.drawable.arrow_down_float,
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							ll_listview = (LinearLayout) findViewById(R.id.ll_people_listview);
							if (ll_listview.isShown()) {
								ll_listview.setVisibility(View.GONE);

							} else {
								ll_listview.setVisibility(View.VISIBLE);
								paramOfLayout();
							}
						}

					});
		} else {
			setTitle(getResources().getString(R.string.people_add),
					android.R.drawable.ic_input_add);
			addprefBarBtn(android.R.drawable.arrow_down_float,
					new OnClickListener() {
						@Override
						public void onClick(View v) {
							ll_listview = (LinearLayout) findViewById(R.id.ll_people_listview);
							if (ll_listview.isShown()) {
								ll_listview.setVisibility(View.GONE);

							} else {
								ll_listview.setVisibility(View.VISIBLE);
								paramOfLayout();
							}
						}

					});
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

		sp_Status = (Spinner) findViewById(R.id.sp_people_status);
		edt_Name = (EditText) findViewById(R.id.edt_people_name);
		sp_Position = (Spinner) findViewById(R.id.sp_people_position);
		edt_Email = (EditText) findViewById(R.id.edt_people_email);
		edt_Telephone = (EditText) findViewById(R.id.edt_people_tel);
		edt_Skype = (EditText) findViewById(R.id.edt_people_skype);
		edt_EmployeeDate = (EditText) findViewById(R.id.edt_people_date_in);
		lv_Projects = (ListView) findViewById(R.id.lv_people_projects);
		lv_Projects.getEmptyView();

		ArrayList<BaseModel> dataList = new ArrayList<BaseModel>();
		for (int i = 1; i <= 10; i++) {
			dataList.add(new BaseModel(i, "Project " + i * 1000));
		}
//		ArrayAdapter<BaseModel> listAdapter = new CustomContainerAdapter(this,
//				dataList, android.R.layout.simple_list_item_1);
//
//		lv_Projects.setAdapter(listAdapter);
		sp_Status.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				switch (arg2) {
				case 0:
					findViewById(R.id.rl_people).setVisibility(View.GONE);
					break;
				case 1:
					findViewById(R.id.rl_people).setVisibility(View.VISIBLE);
					paramOfLayout();
					break;
				}
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

	private void paramOfLayout() {
		ll_listview = (LinearLayout) findViewById(R.id.ll_people_listview);
		if (findViewById(R.id.rl_people).isShown()) {
			LayoutParams param = new LinearLayout.LayoutParams(
					LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 0.5f);
			findViewById(R.id.scrl_people).setLayoutParams(param);
			ll_listview.setLayoutParams(param);
		}
	}

	private void save() {
		setResult(RESULT_OK);
		finish();
	}

}
