package com.kmware.hrm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import com.kmware.hrm.model.BaseModel;
import com.kmware.hrm.view.CustomDatePickerDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
import android.widget.TextView;

public class EditPeople extends ZActivity {

	public static String LOGTAG = EditPeople.class.getSimpleName();
	// public final String ROLEFILENAME = "role.txt";
	private final int DATE_DIALOG_ID = 0;
	private final String SAVE_ROLE = "role";

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
	LinearLayout ll_roles;

	private List<String> set_roles;
	private ArrayAdapter<String> sp_Adapter;
	private int year;
	private int month;
	private int day;
	private String extra;
	private List<String> roles;

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
		if (roles == null)
			roles = new ArrayList<String>();

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
				if (!sp_Role.getSelectedItem().toString().equals(getResources().getString(R.string.empty)))
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
		set_roles = new ArrayList<String>();
		set_roles.addAll(Arrays.asList(getResources().getStringArray(R.array.people_roles)));

		setSpinnerAdapter(set_roles);
		sp_Role.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		ll_roles = (LinearLayout) findViewById(R.id.ll_people_roles);
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

	private void addRole() {
		String role = sp_Role.getSelectedItem().toString();
		set_roles.remove(role);
		if (!set_roles.isEmpty()) {
			setSpinnerAdapter(set_roles);

		} else {
			set_roles.add(getResources().getString(R.string.empty));
			setSpinnerAdapter(set_roles);
		}

		roles.add(role);
		java.util.Collections.sort(roles);

		resetRoles();
	}

	private void resetRoles() {
		LinearLayout ll_role = (LinearLayout) findViewById(R.id.ll_people_roles);
		LayoutInflater ltInflater = getLayoutInflater();
		if (ll_role.getChildCount() > 0)
			ll_role.removeAllViews();
		for (int i = 0; i < roles.size(); i++) {
			final View view = ltInflater.inflate(R.layout.people_role, null, false);
			TextView tv_role = (TextView) view.findViewById(R.id.tv_people_role);
			tv_role.setText(roles.get(i));
			ImageView iv_role_del = (ImageView) view.findViewById(R.id.iv_people_role_del);
			iv_role_del.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {

					TextView tv_role = (TextView) view.findViewById(R.id.tv_people_role);
					ViewGroup parent = (ViewGroup) view.getParent();

					parent.removeView(view);
					roles.remove(tv_role.getText());

					set_roles.clear();
					set_roles.addAll(Arrays.asList(getResources().getStringArray(R.array.people_roles)));
					set_roles.removeAll(roles);
					setSpinnerAdapter(set_roles);
					sp_Role.setSelection(0);
				}
			});

			ll_role.addView(view);
		}

	}

	private void setSpinnerAdapter(List<String> lst) {
		sp_Adapter = new ArrayAdapter<String>(EditPeople.this, android.R.layout.simple_spinner_item, lst);
		sp_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp_Role.setAdapter(sp_Adapter);

	}

	// public void showEnterTextDialog(Context context, String title) {
	//
	// LayoutInflater factory = LayoutInflater.from(context);
	// final View textEntryView =
	// factory.inflate(R.layout.alert_dialog_text_entry, null);
	//
	// AlertDialog dialog = new
	// AlertDialog.Builder(context).setTitle(title).setView(textEntryView)
	// .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int whichButton) {
	// String enter = ((EditText)
	// textEntryView.findViewById(R.id.edt_alertdialog)).getText().toString();
	// writeToFile(enter);
	// }
	// }).setNegativeButton(R.string.cancel, new
	// DialogInterface.OnClickListener() {
	// public void onClick(DialogInterface dialog, int whichButton) {
	// }
	// }).create();
	// dialog.setCancelable(true);
	// dialog.show();
	// }

	// void writeToFile(String text) {
	// try {
	// // Open tread for write
	// BufferedWriter bw = new BufferedWriter(new
	// FileWriter(this.getFileStreamPath(ROLEFILENAME), true));
	// // Writing data
	// bw.append(text);
	// roleArray.add(text);
	// sp_Adapter.notifyDataSetChanged();
	// // Close tread
	// bw.close();
	// Log.d(LOGTAG, "File Writed");
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	//
	// void readFile() {
	// File file = this.getFileStreamPath(ROLEFILENAME);
	// if (!file.exists()) {
	// try {
	// BufferedWriter bw = new BufferedWriter(new
	// OutputStreamWriter(openFileOutput(ROLEFILENAME, MODE_PRIVATE)));
	// roleArray.addAll(Arrays.asList((getResources().getStringArray(R.array.people_roles))));
	// for (String str : roleArray) {
	// bw.write(str + "\n");
	// }
	// // Close tread
	// bw.close();
	//
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }
	// try {
	// // Open tread for read
	// BufferedReader br = new BufferedReader(new
	// InputStreamReader(openFileInput(ROLEFILENAME)));
	// String str = "";
	// roleArray.clear();
	// // Reading entry
	// while ((str = br.readLine()) != null) {
	// roleArray.add(str);
	// Log.d(LOGTAG, str);
	// }
	// } catch (FileNotFoundException e) {
	// e.printStackTrace();
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	// }

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
		roles = savedInstanceState.getStringArrayList(SAVE_ROLE);
		resetRoles();
		set_roles.clear();
		set_roles.addAll(Arrays.asList(getResources().getStringArray(R.array.people_roles)));
		set_roles.removeAll(roles);
		if (!set_roles.isEmpty())
			setSpinnerAdapter(set_roles);
		else
			set_roles.add(getResources().getString(R.string.empty));
		sp_Role.setSelection(0);
		Log.d(LOGTAG, "onRestoreInstanceState - " + roles.toString());
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putStringArrayList(SAVE_ROLE, (ArrayList<String>) roles);
		Log.d(LOGTAG, "onSaveInstanceState - " + roles.toString());
	}

}
