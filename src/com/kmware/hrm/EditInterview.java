package com.kmware.hrm;

import com.kmware.hrm.model.Interviewer;
import com.kmware.hrm.view.CustomDatePickerDialog;
import com.kmware.hrm.view.CustomTimePickerDialog;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

public class EditInterview extends ZActivity implements OnClickListener {
	public static String LOGTAG = EditInterview.class.getSimpleName();
	private final int DATE_DIALOG_ID = 0;
	private final int TIME_DIALOG_ID = 1;
	private final int ADD_CANDIDATE = 10;
	private final int ADD_POSITION = 11;

	EditText edt_Title;
	Button btn_Date;
	Button btn_Time;
	Button btn_Candidate;
	Button btn_Position;
	Spinner sp_Status;

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private boolean setDate;
	private boolean setTime;

	private Interviewer interview;
	// public Position position;
	// public People people;

	private int candidate_id;
	private int position_id;

	// DatabaseHandler db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_interview);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		// if (extra != null) {
		// setTitle(getResources().getString(R.string.interview_edit),
		// android.R.drawable.ic_input_add);
		//
		// } else {
		setTitle(getResources().getString(R.string.interview_add), android.R.drawable.ic_input_add);
		// }

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
		setDate = false;
		setTime = false;

		candidate_id = -1;
		position_id = -1;

		// people = new People();
		// position = new Position();

		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		hour = c.get(Calendar.HOUR_OF_DAY);
		minute = c.get(Calendar.MINUTE);

		edt_Title = (EditText) findViewById(R.id.edt_interview_name);
		btn_Date = (Button) findViewById(R.id.btn_interview_date);
		setBtnDateText();
		btn_Date.setOnClickListener(this);
		btn_Time = (Button) findViewById(R.id.btn_interview_time);
		setBtnTimeText();
		btn_Time.setOnClickListener(this);
		btn_Candidate = (Button) findViewById(R.id.btn_interview_candidate);
		btn_Candidate.setOnClickListener(this);
		btn_Position = (Button) findViewById(R.id.btn_interview_position);
		btn_Position.setOnClickListener(this);

		sp_Status = (Spinner) findViewById(R.id.sp_interview_status);
		ArrayAdapter<String> status_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, this.getResources().getStringArray(
				R.array.interview_status));
		status_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_Status.setAdapter(status_adapter);

		if (getIntent().getStringExtra("Candidate interview") != null) {
			int position = Integer.parseInt(getIntent().getStringExtra("Candidate interview"));
			int i = 0;
			while (Test_Data.list_people.get(i).getId() != position && i < Test_Data.list_people.size()) {
				i++;
			}
			candidate_id = Test_Data.list_people.get(i).getId();
			btn_Candidate.setText(Test_Data.list_people.get(i).getLastname() + " " + Test_Data.list_people.get(i).getName());
		}

	}

	private void save() {
		GregorianCalendar time = new GregorianCalendar(year, month, day, hour, minute);

		if (edt_Title.getText().toString().trim().length() > 0) {
			if (candidate_id != -1) {
				if (position_id != -1) {
					if (setDate) {
						if (setTime) {
							if (System.currentTimeMillis() < time.getTimeInMillis()) {

								interview = new Interviewer();
								interview.setName(edt_Title.getText().toString().trim());
								interview.setDate("" + day + ":" + month + ":" + year);
								interview.setTime("" + hour + ":" + minute);
								interview.setCandidate(candidate_id);
								interview.setPosition(position_id);
								interview.setStatus(sp_Status.getSelectedItemPosition());
								Test_Data.list_interview.add(interview);
								setResult(RESULT_OK);
								finish();
							} else {
								getDialog().showWarning(this, "Interview date is before the present");
							}
						} else {
							getDialog().showWarning(this, "Choose time");
						}
					} else {
						getDialog().showWarning(this, "Choose date");
					}
				} else {
					getDialog().showWarning(this, "Choose position");
				}
			} else {
				getDialog().showWarning(this, "Choose candidate");
			}
		} else {
			getDialog().showWarning(this, "Fill the title");
		}

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
			setDate = true;
			setBtnDateText();
		}
	};

	private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
			hour = hourOfDay;
			minute = minuteOfDay;
			setTime = true;
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

	private void setBtnTimeText() {
		CustomTimePickerDialog.setTime(hour, minute);
		SimpleDateFormat in = new SimpleDateFormat("HH:mm");
		btn_Time.setText(in.format(new Time(hour, minute, 0)));

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, ListContainer.class);
		switch (v.getId()) {
		case (R.id.btn_interview_date):
			btnChangeDate_Click(v);
			break;
		case (R.id.btn_interview_time):
			btnChangeTime_Click(v);
			break;
		case (R.id.btn_interview_candidate):
			intent.putExtra("Interview", getResources().getString(R.string.cat_people));
			startActivityForResult(intent, ADD_CANDIDATE);
			break;
		case (R.id.btn_interview_position):
			intent.putExtra("Interview", getResources().getString(R.string.cat_positions));
			startActivityForResult(intent, ADD_POSITION);
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (data == null) {
			return;
		}
		int i = 0;
		if (data.getIntExtra("Candidate", -1) != -1) {
			while (Test_Data.list_people.get(i).getId() != data.getIntExtra("Candidate", 0) && i < Test_Data.list_people.size()) {
				i++;
			}
			candidate_id = Test_Data.list_people.get(i).getId();
			btn_Candidate.setText(Test_Data.list_people.get(i).getLastname() + " " + Test_Data.list_people.get(i).getName());
		}
		i = 0;
		if (data.getIntExtra("Position", -1) != -1) {
			while (Test_Data.list_position.get(i).getId() != data.getIntExtra("Position", 0) && i < Test_Data.list_position.size()) {
				i++;
			}
			position_id = Test_Data.list_position.get(i).getId();
			btn_Position.setText(Test_Data.list_position.get(i).getName());
		}
	}

}
