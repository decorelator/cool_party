package com.kmware.hrm;

import com.kmware.hrm.view.CustomDatePickerDialog;
import com.kmware.hrm.view.CustomTimePickerDialog;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemSelectedListener;

public class EditInterview extends ZActivity implements OnClickListener {
	public static String LOGTAG = EditInterview.class.getSimpleName();
	private final int DATE_DIALOG_ID = 0;
	private final int TIME_DIALOG_ID = 1;

	EditText edt_NameInterviewer;
	Spinner sp_Project;
	Spinner sp_Position;
	Button btn_Date;
	Button btn_Time;
	EditText edt_Description;

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_interview);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		getExtra();
		if (extra.toString().length() != 0) {
			setTitle(getResources().getString(R.string.interview_edit), android.R.drawable.ic_input_add);

		} else {
			setTitle(getResources().getString(R.string.interview_add), android.R.drawable.ic_input_add);
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
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);
		
		
		edt_NameInterviewer = (EditText) findViewById(R.id.edt_interview_name);
		sp_Project = (Spinner) findViewById(R.id.sp_interview_project);
		sp_Position = (Spinner) findViewById(R.id.sp_interview_position);
		btn_Date = (Button) findViewById(R.id.btn_interview_date);
		setBtnDateText();
		btn_Date.setOnClickListener(this);
		btn_Time = (Button) findViewById(R.id.btn_interview_time);
		setBtnTimeText();
		btn_Time.setOnClickListener(this);
		edt_Description = (EditText) findViewById(R.id.edt_interview_description);

		sp_Project.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		sp_Position.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

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

	public void btnChangeDate_Click(View v) {
		showDialog(DATE_DIALOG_ID);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new CustomDatePickerDialog(this, dateSetListener, year, month, day);
		case TIME_DIALOG_ID:
			return new CustomTimePickerDialog(this, timeSetListener, hour, minute);
		default:
			return null;
		}
	}

	private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int currentYear, int monthOfYear, int dayOfMonth) {
			year = currentYear;
			month = monthOfYear;
			day = dayOfMonth;
			setBtnDateText();
		}
	};
	
	private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
		
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
			hour = hourOfDay;
			minute =  minuteOfDay;
			setBtnTimeText();
		}
	};

	private void setBtnDateText() {
		CustomDatePickerDialog.setDate(year, month, day);
		GregorianCalendar calendar = new GregorianCalendar(year, month, day);
		btn_Date.setText(DateFormat.format("dd MMMM yyyy", calendar.getTime()));
	}

	public void btnChangeTime_Click(View v) {
		showDialog(TIME_DIALOG_ID);
	}
	
	private void setBtnTimeText()  {
		CustomTimePickerDialog.setTime(hour,minute);
		SimpleDateFormat in= new SimpleDateFormat("HH:mm");
			btn_Time.setText(in.format(new Time(hour, minute, 0)));
		
		
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.btn_interview_date):
			btnChangeDate_Click(v);
			break;

		case (R.id.btn_interview_time):
			btnChangeTime_Click(v);
			break;
		}
	}

}
