package com.kmware.hrm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Set;
import java.util.TreeSet;

import com.kmware.hrm.model.BaseModel;
import com.kmware.hrm.view.CustomDatePickerDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.Spinner;

public class EditPeople extends ZActivity  {

	public static String LOGTAG = EditPeople.class.getSimpleName();
	public final String ROLEFILENAME = "role.txt";
	private final int DATE_DIALOG_ID = 0;
	
	Spinner sp_Status;
	Spinner sp_Position;
	Spinner sp_Role;
	Button btn_AddRole;
	Button btn_EmployeeDate;
	EditText edt_Name;
	EditText edt_Email;
	EditText edt_Telephone;
	EditText edt_Skype;
	ListView lv_Projects;
	LinearLayout ll_listview;
	ArrayAdapter<String> sp_Adapter;
	
	LinearLayout ll_admin;
	LinearLayout ll_hr;
	LinearLayout ll_empl;
	LinearLayout ll_interview;
	ImageView iv_del_admin;
	ImageView iv_del_hr;
	ImageView iv_del_empl;
	ImageView iv_del_interview;

	private int year;
	private int month;
	private int day;
	private String extra;
	private Set<String> roles;

//	private List<String> roleArray = new ArrayList<String>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_people);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		getExtra();
		if (extra.toString().length() != 0) {
			setTitle(getResources().getString(R.string.people_edit), android.R.drawable.ic_input_add);
			addprefBarBtn(android.R.drawable.arrow_down_float, new OnClickListener() {
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
			setTitle(getResources().getString(R.string.people_add), android.R.drawable.ic_input_add);
			addprefBarBtn(android.R.drawable.arrow_down_float, new OnClickListener() {
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

		Calendar c = Calendar.getInstance();
		year = c.get(Calendar.YEAR);
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		roles = new TreeSet<String>();
		
		sp_Status = (Spinner) findViewById(R.id.sp_people_status);
		edt_Name = (EditText) findViewById(R.id.edt_people_name);
		sp_Position = (Spinner) findViewById(R.id.sp_people_position);
		sp_Role = (Spinner) findViewById(R.id.sp_people_role);
		edt_Email = (EditText) findViewById(R.id.edt_people_email);
		edt_Telephone = (EditText) findViewById(R.id.edt_people_tel);
		edt_Skype = (EditText) findViewById(R.id.edt_people_skype);
		lv_Projects = (ListView) findViewById(R.id.lv_people_projects);
		lv_Projects.getEmptyView();
		btn_EmployeeDate = (Button) findViewById(R.id.btn_people_date_in);
		btn_EmployeeDate.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				btnChangeDate_Click(v);
			}
		});
		setBtnDateText();
		btn_AddRole = (Button) findViewById(R.id.btn_people_add_role);
		btn_AddRole.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addRole();
			}
		});
		ArrayList<BaseModel> dataList = new ArrayList<BaseModel>();
		for (int i = 1; i <= 10; i++) {
			dataList.add(new BaseModel(i, "Project " + i * 1000));
		}
		sp_Status.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
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
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		sp_Adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Arrays.asList(getResources().getStringArray(R.array.people_roles)));
		sp_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		sp_Role.setAdapter(sp_Adapter);
		sp_Role.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
	//TEMPORARY	
		ll_admin = (LinearLayout) findViewById(R.id.ll_people_roles_admin);
		ll_hr = (LinearLayout) findViewById(R.id.ll_people_roles_hr);
		ll_empl = (LinearLayout) findViewById(R.id.ll_people_roles_emp);
		ll_interview = (LinearLayout) findViewById(R.id.ll_people_roles_interveew);
		iv_del_admin = (ImageView) findViewById(R.id.iv_people_admin_del);
		iv_del_admin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ll_admin.setVisibility(View.GONE);
				roles.remove(getResources().getString(R.string.people_role_administartor));
			}
		});
		iv_del_hr = (ImageView) findViewById(R.id.iv_people_hr_del);
		iv_del_hr.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ll_hr.setVisibility(View.GONE);
				roles.remove(getResources().getString(R.string.people_role_hrmanager));
			}
		});
		iv_del_empl = (ImageView) findViewById(R.id.iv_people_emp_del);
		iv_del_empl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ll_empl.setVisibility(View.GONE);
				roles.remove(getResources().getString(R.string.people_role_employee));
			}
		});
		iv_del_interview = (ImageView) findViewById(R.id.iv_people_int_del);
		iv_del_interview.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ll_interview.setVisibility(View.GONE);
				roles.remove(getResources().getString(R.string.people_role_interviewer));
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

	private void addRole(){
		switch(sp_Role.getSelectedItemPosition()){
		case 0:
			ll_admin.setVisibility(View.VISIBLE);
			roles.add(getResources().getString(R.string.people_role_administartor));
			break;
		case 1:
			ll_hr.setVisibility(View.VISIBLE);
			roles.add(getResources().getString(R.string.people_role_hrmanager));
			break;
		case 2:
			ll_empl.setVisibility(View.VISIBLE);
			roles.add(getResources().getString(R.string.people_role_employee));
			break;
		case 3:
			ll_interview.setVisibility(View.VISIBLE);
			roles.add(getResources().getString(R.string.people_role_interviewer));
			break;
		}
	}
	
//	public void showEnterTextDialog(Context context, String title) {
//
//		LayoutInflater factory = LayoutInflater.from(context);
//		final View textEntryView = factory.inflate(R.layout.alert_dialog_text_entry, null);
//
//		AlertDialog dialog = new AlertDialog.Builder(context).setTitle(title).setView(textEntryView)
//				.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//						String enter = ((EditText) textEntryView.findViewById(R.id.edt_alertdialog)).getText().toString();
//						writeToFile(enter);
//					}
//				}).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
//					public void onClick(DialogInterface dialog, int whichButton) {
//					}
//				}).create();
//		dialog.setCancelable(true);
//		dialog.show();
//	}

//	void writeToFile(String text) {
//		try {
//			// Open tread for write
//			BufferedWriter bw = new BufferedWriter(new FileWriter(this.getFileStreamPath(ROLEFILENAME), true));
//			// Writing data
//			bw.append(text);
//			roleArray.add(text);
//			sp_Adapter.notifyDataSetChanged();
//			// Close tread
//			bw.close();
//			Log.d(LOGTAG, "File Writed");
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	void readFile() {
//		File file = this.getFileStreamPath(ROLEFILENAME);
//		if (!file.exists()) {
//			try {
//				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(openFileOutput(ROLEFILENAME, MODE_PRIVATE)));
//				roleArray.addAll(Arrays.asList((getResources().getStringArray(R.array.people_roles))));
//				for (String str : roleArray) {
//					bw.write(str + "\n");
//				}
//				// Close tread
//				bw.close();
//
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		try {
//			// Open tread for read
//			BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput(ROLEFILENAME)));
//			String str = "";
//			roleArray.clear();
//			// Reading entry
//			while ((str = br.readLine()) != null) {
//				roleArray.add(str);
//				Log.d(LOGTAG, str);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}

	public void btnChangeDate_Click(View v) {
		showDialog(DATE_DIALOG_ID);
	}

	@Override
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case DATE_DIALOG_ID:
			return new CustomDatePickerDialog(this, dateSetListener, year, month, day);
		
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
	
	private void setBtnDateText() {
		CustomDatePickerDialog.setDate(year, month, day);
		GregorianCalendar calendar = new GregorianCalendar(year, month, day);
		btn_EmployeeDate.setText(DateFormat.format("dd MMMM yyyy", calendar.getTime()));
	}
	
	private void paramOfLayout() {
		ll_listview = (LinearLayout) findViewById(R.id.ll_people_listview);
		if (findViewById(R.id.rl_people).isShown()) {
			LayoutParams param = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT, 0.5f);
			findViewById(R.id.scrl_people).setLayoutParams(param);
			ll_listview.setLayoutParams(param);
		}
	}

	private void save() {
		setResult(RESULT_OK);
		finish();
	}
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(LOGTAG, "onRestoreInstanceState");
	}
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(LOGTAG, "onSaveInstanceState");
	}
	
}
