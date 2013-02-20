package com.kmware.hrm;

import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.Interviewer;
import com.kmware.hrm.model.Position;
import com.kmware.hrm.model.Project;
import com.kmware.hrm.view.CustomDatePickerDialog;
import com.kmware.hrm.view.CustomTimePickerDialog;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
	EditText edt_phone;
	Button btn_Date;
	Button btn_Time;
	EditText edt_Description;

	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;
	private String extra;

	private ArrayAdapter<String> sp_Adapter;
	private Interviewer interview;
	List<String> sp_projects;
	List<String> sp_position;
	List<Project> projects;
	List<Position> position;
	DatabaseHandler db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_interview);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		getExtra();
		if (extra != null) {
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

		db = new DatabaseHandler(this);

		Calendar c = Calendar.getInstance();

		if (!parseDate()) {
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
			hour = c.get(Calendar.HOUR_OF_DAY);
			minute = c.get(Calendar.MINUTE);
		}
		edt_NameInterviewer = (EditText) findViewById(R.id.edt_interview_name);
		edt_phone = (EditText) findViewById(R.id.edt_interview_phone);
		sp_Project = (Spinner) findViewById(R.id.sp_interview_project);
		sp_Position = (Spinner) findViewById(R.id.sp_interview_position);
		btn_Date = (Button) findViewById(R.id.btn_interview_date);
		setBtnDateText();
		btn_Date.setOnClickListener(this);
		btn_Time = (Button) findViewById(R.id.btn_interview_time);
		setBtnTimeText();
		btn_Time.setOnClickListener(this);
		edt_Description = (EditText) findViewById(R.id.edt_interview_description);

		sp_projects = new ArrayList<String>();
		projects = new ArrayList<Project>(db.getAllProject());
		for (int i = 0; i < projects.size(); i++) {
			sp_projects.add(projects.get(i).getName());
		}
		setSpinnerAdapter(R.id.sp_interview_project, sp_projects);
		sp_Project.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		sp_position = new ArrayList<String>();
		position = new ArrayList<Position>(db.getAllPositions());
		for (int i = 0; i < position.size(); i++) {
			sp_position.add(position.get(i).getName());
		}
		setSpinnerAdapter(R.id.sp_interview_position, sp_position);
		sp_Position.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		if (getIntent().getIntExtra("ID", 0) != 0) {
			enterField(getIntent().getIntExtra("ID", 0));
		}
	}

	private void enterField(int id) {
		interview = new Interviewer();
		try {
			interview.setInterview(db.getInterview(id));
			edt_NameInterviewer.setText(interview.getName());
			if (interview.getPhone() != 0) {
				edt_phone.setText("" + interview.getPhone());
			}
			String[] array = sp_projects.toArray(new String[sp_projects.size()]);
			int i = 0;

			while (!array[i].equals(projects.get(interview.getProject() - 1).getName()) && i < array.length) {
				i++;
			}
			sp_Project.setSelection(i);
			array = sp_position.toArray(new String[sp_position.size()]);
			i = 0;
			while (!array[i].equals(position.get(interview.getPosition() - 1).getName()) && i < array.length) {
				i++;
			}
			sp_Position.setSelection(i);
			edt_Description.setText(interview.getDescription());

		} catch (Exception ex) {
			ex.printStackTrace();
			Log.w(LOGTAG, "DB have not ID = " + id);
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

	private void save() {
		GregorianCalendar time = new GregorianCalendar(year, month, day, hour, minute);

		if (edt_NameInterviewer.getText().toString().trim().length() > 0) {
			if (System.currentTimeMillis() < time.getTimeInMillis()) {
				interview = new Interviewer();
				interview.setName(edt_NameInterviewer.getText().toString().trim());
				if (edt_phone.getText().toString().trim().length() != 0) {
					interview.setPhone(Integer.parseInt(edt_phone.getText().toString().trim()));
				} else {
					interview.setPhone(0);
				}
				String str = sp_projects.get(sp_Project.getSelectedItemPosition());
				int i = 0;
				try {
					i = db.getProjectByName(str).getId();
				} catch (SQLiteException ex) {
					ex.printStackTrace();
					Log.w(LOGTAG, "Project DB have not project with name - " + str);
				}
				if (i != 0)
					interview.setProject(i);
				str = sp_position.get(sp_Position.getSelectedItemPosition());
				i = 0;
				try {
					i = db.getPositionByName(str).getId();
				} catch (SQLiteException ex) {
					ex.printStackTrace();
					Log.w(LOGTAG, "Position DB have not position with name - " + str);
				}
				if (i != 0)
					interview.setPosition(i);
				interview.setDate("" + day + ":" + month + ":" + year);
				interview.setTime("" + hour + ":" + minute);
				interview.setDescription(edt_Description.getText().toString().trim());

				if (getIntent().getIntExtra("ID", 0) == 0) {
					db.addInterview(interview);
				} else {
					interview.setId(getIntent().getIntExtra("ID", 0));
					db.updateInterview(interview);
				}

				setResult(RESULT_OK);
				finish();
			} else {
				getDialog().showWarning(this, "Interview date is before the present");
			}
		} else {
			getDialog().showWarning(this, getResources().getString(R.string.project_error_date));
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
			setBtnDateText();
		}
	};

	private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minuteOfDay) {
			hour = hourOfDay;
			minute = minuteOfDay;
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
		switch (v.getId()) {
		case (R.id.btn_interview_date):
			btnChangeDate_Click(v);
			break;

		case (R.id.btn_interview_time):
			btnChangeTime_Click(v);
			break;
		}
	}

	private void setSpinnerAdapter(int id, List<String> lst) {
		sp_Adapter = new ArrayAdapter<String>(EditInterview.this, android.R.layout.simple_spinner_item, lst);
		sp_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		switch (id) {
		case R.id.sp_interview_position:
			sp_Position.setAdapter(sp_Adapter);
			break;
		case R.id.sp_interview_project:
			sp_Project.setAdapter(sp_Adapter);
			break;
		}

	}

	private boolean parseDate() {
		try {
			String parser;
			if (getIntent().getIntExtra("ID", 0) != 0) {
				if (db.getInterview(getIntent().getIntExtra("ID", 0)) != null) {
					parser = "" + db.getInterview(getIntent().getIntExtra("ID", 0)).getDate();
					String[] date = parser.split(":");
					if (date.length > 1 && !parser.equals("null")) {
						year = Integer.parseInt(date[2]);
						month = Integer.parseInt(date[1]);
						day = Integer.parseInt(date[0]);
						parser = "" + db.getInterview(getIntent().getIntExtra("ID", 0)).getTime();
						date = parser.split(":");
						if (date.length > 1 && !parser.equals("null")) {
							minute = Integer.parseInt(date[1]);
							hour = Integer.parseInt(date[0]);
							return true;
						}
					}
					return false;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
