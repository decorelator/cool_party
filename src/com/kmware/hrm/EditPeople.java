package com.kmware.hrm;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.People;
import com.kmware.hrm.model.Position;
import com.kmware.hrm.model.Roles;
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
	EditText edt_Lastname;
	EditText edt_Email;
	EditText edt_Telephone;
	EditText edt_Skype;
	ListView lv_Projects;
	LinearLayout ll_listview;
	LinearLayout ll_roles;

	private ArrayAdapter<String> sp_Adapter;
	private int year;
	private int month;
	private int day;
	private String extra;
	private List<String> roles;
	private List<String> set_roles;
	private List<Roles> role;
	private List<Position> position;
	// private List<People> people;
	private People person;

	private DatabaseHandler db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_people);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		getExtra();
		if (extra != null) {
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
		db = new DatabaseHandler(this);
		
		role = new ArrayList<Roles>(db.getAllRoles());
		position = new ArrayList<Position>(db.getAllPositions());

		Calendar c = Calendar.getInstance();
		if (!parseDate()) {
			year = c.get(Calendar.YEAR);
			month = c.get(Calendar.MONTH);
			day = c.get(Calendar.DAY_OF_MONTH);
		}
		if (roles == null)
			roles = new ArrayList<String>();

		sp_Status = (Spinner) findViewById(R.id.sp_people_status);
		edt_Name = (EditText) findViewById(R.id.edt_people_name);
		edt_Lastname = (EditText) findViewById(R.id.edt_people_lastname);
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

		List<String> pos = new ArrayList<String>();
		for (int i = 0; i < position.size(); i++) {
			pos.add(position.get(i).getName());
		}
		setSpinnerAdapter(R.id.sp_people_position, pos);
		sp_Position.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		set_roles = new ArrayList<String>();

		for (int i = 0; i < role.size(); i++) {
			set_roles.add(role.get(i).getRoleName());
		}
		setSpinnerAdapter(R.id.sp_people_role, set_roles);
		sp_Role.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

		ll_roles = (LinearLayout) findViewById(R.id.ll_people_roles);
		if (getIntent().getIntExtra("ID", 0) != 0) {
			enterField(getIntent().getIntExtra("ID", 0));
		}
	}

	public void enterField(int id) {

		// people = new ArrayList<People>();
		person = new People();
		try {
			person.setPerson(db.getPerson(id));
			sp_Status.setSelection(person.getStatus_id());
			edt_Name.setText(person.getName());
			edt_Lastname.setText(person.getLastname());
			int i = 0;
			while (((position.get(i)).getId() != person.getPosition()) && (i < position.size())) {
				i++;
			}
			sp_Position.setSelection(i);
			// sp_Role
			edt_Email.setText(person.getEmail());

			if (person.getPhone() != 0) {
				edt_Telephone.setText(String.valueOf(person.getPhone()));
			} else
				edt_Telephone.setText("");
			edt_Skype.setText(person.getSkype());
			String db_role = "" + db.getPerson(getIntent().getIntExtra("ID", 0)).getRole();
			if (db_role.length() > 1 && !db_role.equals("null")) {
				String[] db_roles = db_role.split(":");
				for (i = 0; i < db_roles.length; i++) {
					int j = 0;
					while (!set_roles.get(j).equals(db.getRole(Integer.parseInt(db_roles[i])).getRoleName())) {
						j++;
					}
					sp_Role.setSelection(j);
					addRole();
				}
			}
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

	private void addRole() {
		String role = sp_Role.getSelectedItem().toString();
		set_roles.remove(role);
		if (!set_roles.isEmpty()) {
			setSpinnerAdapter(R.id.sp_people_role, set_roles);

		} else {
			set_roles.add(getResources().getString(R.string.empty));
			setSpinnerAdapter(R.id.sp_people_role, set_roles);
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

					for (int i = 0; i < role.size(); i++) {
						set_roles.add(role.get(i).getRoleName());
					}

					// set_roles.addAll(Arrays.asList(getResources().getStringArray(R.array.people_roles)));
					set_roles.removeAll(roles);
					setSpinnerAdapter(R.id.sp_people_role, set_roles);
					sp_Role.setSelection(0);
				}
			});

			ll_role.addView(view);
		}

	}

	private void setSpinnerAdapter(int id, List<String> lst) {
		sp_Adapter = new ArrayAdapter<String>(EditPeople.this, android.R.layout.simple_spinner_item, lst);
		sp_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		switch (id) {
		case R.id.sp_people_role:
			sp_Role.setAdapter(sp_Adapter);
			break;
		case R.id.sp_people_position:
			sp_Position.setAdapter(sp_Adapter);
			break;
		}

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
		if (edt_Name.getText().toString().trim().length() > 0) {
			People person = new People();
			person.setName(edt_Name.getText().toString().trim());
			person.setLastname(edt_Lastname.getText().toString().trim());

			if (sp_Status.getSelectedItemId() == 1) {
				person.setSkype(edt_Skype.getText().toString().trim());
				person.setEmail(edt_Email.getText().toString().trim());
				if (edt_Telephone.getText().toString().trim().length() > 0) {
					person.setPhone(Integer.parseInt(edt_Telephone.getText().toString().trim()));
				}
				person.setEmployment_date("" + day + ":" + month + ":" + year);
			}
			person.setStatus_id(sp_Status.getSelectedItemPosition());
			person.setPosition((db.getPositionByName(String.valueOf(sp_Position.getSelectedItem()))).getId());
			String str = "";
			for (int i = 0; i < roles.size(); i++) {
				str = str + String.valueOf(db.getRoleByName(roles.get(i)).getRoleId()) + ":";
			}
			if (str.length() > 0 && str.charAt(str.length() - 1) == ':') {
				str = str.substring(0, str.length() - 1);
			}
			person.setRole(str);
			if (getIntent().getIntExtra("ID", 0) == 0) {
				db.addPerson(person);
			} else {
				person.setId(getIntent().getIntExtra("ID", 0));
				db.updatePerson(person);
			}

			setResult(RESULT_OK);
			finish();
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		roles = savedInstanceState.getStringArrayList(SAVE_ROLE);
		resetRoles();
		set_roles.clear();
		for (int i = 0; i < role.size(); i++) {
			set_roles.add(role.get(i).getRoleName());
		}
		set_roles.removeAll(roles);
		if (!set_roles.isEmpty())
			setSpinnerAdapter(R.id.sp_people_role, set_roles);
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

	private boolean parseDate() {
		try {
			String parser;
			if (db.getPerson(getIntent().getIntExtra("ID", 0)) != null) {
				parser = "" + db.getPerson(getIntent().getIntExtra("ID", 0)).getEmployment_date();
				String[] date = parser.split(":");
				if (date.length > 1 && !parser.equals("null")) {
					year = Integer.parseInt(date[2]);
					month = Integer.parseInt(date[1]);
					day = Integer.parseInt(date[0]);
					return true;
				}
				return false;
			} else {
				return false;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
