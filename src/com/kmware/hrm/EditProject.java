package com.kmware.hrm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import com.kmware.hrm.model.BaseModel;
import com.kmware.hrm.view.CustomDatePickerDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.LinearLayout.LayoutParams;

public class EditProject extends ZActivity implements OnClickListener {
	public static String LOGTAG = EditProject.class.getSimpleName();
	private final int START_DATE_DIALOG = 0;
	private final int END_DATE_DIALOG = 1;

	EditText edt_Name;
	Spinner sp_Status;
	EditText edt_Email;
	EditText edt_Phone;
	EditText edt_Skype;
	Button btn_StartDate;
	Button btn_EndDate;
	ListView lv_Employee;
	LinearLayout ll_listview;

	private int s_year;
	private int s_month;
	private int s_day;
	private int e_year;
	private int e_month;
	private int e_day;
	private String extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_project);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		getExtra();
		if (extra.toString().length() != 0) {
			setTitle(getResources().getString(R.string.project_edit), android.R.drawable.ic_input_add);
			addprefBarBtn(android.R.drawable.arrow_down_float, new OnClickListener() {
				@Override
				public void onClick(View v) {
					ll_listview = (LinearLayout) findViewById(R.id.ll_project_employee);
					if (ll_listview.isShown()) {
						ll_listview.setVisibility(View.GONE);
					} else {
						ll_listview.setVisibility(View.VISIBLE);
						paramOfLayout();

					}
				}

			});
		} else {
			setTitle(getResources().getString(R.string.project_add), android.R.drawable.ic_input_add);
			addprefBarBtn(android.R.drawable.arrow_down_float, new OnClickListener() {
				@Override
				public void onClick(View v) {
					ll_listview = (LinearLayout) findViewById(R.id.ll_project_employee);
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

		Calendar c = Calendar.getInstance();
		s_year = c.get(Calendar.YEAR);
		s_month = c.get(Calendar.MONTH);
		s_day = c.get(Calendar.DAY_OF_MONTH);
		e_year = s_year;
		e_month = s_month;
		e_day = s_day;

		sp_Status = (Spinner) findViewById(R.id.sp_project_status);
		edt_Name = (EditText) findViewById(R.id.edt_project_name);
		edt_Email = (EditText) findViewById(R.id.edt_project_email);
		edt_Phone = (EditText) findViewById(R.id.edt_project_tel);
		edt_Skype = (EditText) findViewById(R.id.edt_project_skype);
		btn_StartDate = (Button) findViewById(R.id.btn_project_sdate);
		setBtnDateText(btn_StartDate);
		btn_StartDate.setOnClickListener(this);

		btn_EndDate = (Button) findViewById(R.id.btn_project_edate);
		setBtnDateText(btn_EndDate);
		btn_EndDate.setOnClickListener(this);

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

	private void setBtnDateText(View v) {
		GregorianCalendar calendar;
		switch (v.getId()) {
		case R.id.btn_project_sdate:
			CustomDatePickerDialog.setDate(s_year, s_month, s_day);
			calendar = new GregorianCalendar(s_year, s_month, s_day);
			btn_StartDate.setText(DateFormat.format("dd MMMM yyyy", calendar.getTime()));
			break;

		case R.id.btn_project_edate:
			CustomDatePickerDialog.setDate(e_year, e_month, e_day);
			calendar = new GregorianCalendar(e_year, e_month, e_day);
			btn_EndDate.setText(DateFormat.format("dd MMMM yyyy", calendar.getTime()));
			break;

		}
	}

	private void paramOfLayout() {
		LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 0.5f);

		findViewById(R.id.scrl_project).setLayoutParams(param);
		ll_listview.setLayoutParams(param);
	}

	private void save() {
		setResult(RESULT_OK);
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_project_sdate:
			btnChangeDate_Click(v);
			break;

		case R.id.btn_project_edate:
			btnChangeDate_Click(v);
			break;

		}
	}

	public void btnChangeDate_Click(View v) {
		switch (v.getId()) {
		case R.id.btn_project_sdate:
			showDialog(START_DATE_DIALOG);
			break;

		case R.id.btn_project_edate:
			showDialog(END_DATE_DIALOG);
			break;
		}
	}
	
	private DatePickerDialog.OnDateSetListener startDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int currentYear, int monthOfYear, int dayOfMonth) {
			s_year = currentYear;
			s_month = monthOfYear;
			s_day = dayOfMonth;
			setBtnDateText(btn_StartDate);
		}
	};
	private DatePickerDialog.OnDateSetListener endDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int currentYear, int monthOfYear, int dayOfMonth) {
			e_year = currentYear;
			e_month = monthOfYear;
			e_day = dayOfMonth;
			setBtnDateText(btn_EndDate);
		}
	};
	
	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case START_DATE_DIALOG:
			return new CustomDatePickerDialog(this, startDateSetListener, s_year, s_month, s_day);
		case END_DATE_DIALOG:
			return new CustomDatePickerDialog(this, endDateSetListener, e_year, e_month, e_day);
		default:
			return null;
		}
	}


}