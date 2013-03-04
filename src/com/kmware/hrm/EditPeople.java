package com.kmware.hrm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.kmware.hrm.model.People;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

public class EditPeople extends ZActivity {

	public static String LOGTAG = EditPeople.class.getSimpleName();
	// public final String ROLEFILENAME = "role.txt";

	private final int ADDED_INTERVIEW = 0;

	Spinner sp_Facility;
	EditText edt_Name;
	EditText edt_Lastname;
	EditText edt_Email;
	CheckBox chb_show_dialog;

	private ArrayAdapter<String> sp_Adapter;
	private boolean valid_mail;
	// private boolean checkDataSet;
	// private People person;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_people);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		// getExtra();
		// if (extra != null) {
		// setTitle(getResources().getString(R.string.people_edit),
		// android.R.drawable.ic_input_add);
		// } else {
		setTitle(getResources().getString(R.string.people_add), android.R.drawable.ic_input_add);
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
		// checkDataSet = false;
		valid_mail = false;
		edt_Name = (EditText) findViewById(R.id.edt_people_name);
		edt_Lastname = (EditText) findViewById(R.id.edt_people_lastname);
		edt_Email = (EditText) findViewById(R.id.edt_people_pr_mail);
		edt_Email.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				if (edt_Email.getText().toString().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+") && s.length() > 0)
	            {
					valid_mail = true;
	            }else {
	            	valid_mail = false;
	            }
	            	
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
			}
		});
		
		sp_Facility = (Spinner) findViewById(R.id.sp_people_facility);
		chb_show_dialog = (CheckBox) findViewById(R.id.chb_people_show_dialog);
		List<String> facility = new ArrayList<String>();
		facility.add(this.getResources().getString(R.string.sp_none));
		facility.addAll(Arrays.asList(this.getResources().getStringArray(R.array.people_facility)));
		setSpinnerAdapter(R.id.sp_people_facility, facility);
		sp_Facility.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});

	}

	// public void enterField(int id) {
	//
	// person = new People();
	// try {
	// person.setPerson(db.getPerson(id));
	// sp_Facility.setSelection(person.getFacility());
	// edt_Name.setText(person.getName());
	// edt_Lastname.setText(person.getLastname());
	// edt_Email.setText(person.getEmail());
	//
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// Log.w(LOGTAG, "DB have not ID = " + id);
	// }
	//
	// }

	// private void getExtra() {
	// Bundle extras = getIntent().getExtras();
	// extra = Extras.EMPTY_STRING;
	// if (extras != null) {
	// try {
	// extra = extras.getString(Extras.ADD_INTENT);
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	//
	// }
	// }

	private void setSpinnerAdapter(int id, List<String> lst) {
		sp_Adapter = new ArrayAdapter<String>(EditPeople.this, android.R.layout.simple_spinner_item, lst);
		sp_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		switch (id) {
		case R.id.sp_people_facility:
			sp_Facility.setAdapter(sp_Adapter);
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

	private void save() {
		if (edt_Name.getText().toString().trim().length() > 0) {
			if (edt_Lastname.getText().toString().trim().length() > 0) {
				if (edt_Email.getText().toString().trim().length() > 0 ) {
					if (valid_mail){
					People person = new People();
					person.setId(Test_Data.list_people.get(Test_Data.list_people.size()-1).getId() + 1);
					person.setName(edt_Name.getText().toString().trim());
					person.setLastname(edt_Lastname.getText().toString().trim());
					person.setEmail(edt_Email.getText().toString().trim());
					person.setFacility((int) sp_Facility.getSelectedItemId());
					Test_Data.list_people.add(person);
					if (chb_show_dialog.isChecked()) {
						Intent intent = new Intent(this, EditInterview.class);
						intent.putExtra("Candidate interview", ""+person.getId());
						startActivityForResult(intent, ADDED_INTERVIEW);
					} else {
						setResult(RESULT_OK);
						finish();
					}
					}else{
						getDialog().showWarning(this, "E-mail is incorrect");
					}
				} else {
					getDialog().showWarning(this, "Enter the primary email");
				}
			} else {
				getDialog().showWarning(this, "Enter the lastname");
			}
		} else {
			getDialog().showWarning(this, this.getResources().getString(R.string.people_err_name));
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {

		case ADDED_INTERVIEW:
			setResult(RESULT_OK);
			finish();
			break;
		}
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

}
