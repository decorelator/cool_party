package com.kmware.hrm;

import com.kmware.hrm.R;
import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.preferences.PrefActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class Login extends ZActivity implements OnClickListener {

	private static final String LOGTAG = Login.class.getSimpleName();

	private static final String LOGIN_ADMIN = "admin";
	private static final String LOGIN_GUEST = "guest";

	private Button btn_Login;
	private EditText et_Login;
	private EditText et_Password;
	private CheckBox chbx_save;
	SharedPreferences prefs;
	DatabaseHandler db;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.login);
		showMenu = false;
		super.onCreate(savedInstanceState);
		init();
	}

	private void init() {
		db = new DatabaseHandler(this);
		prefs = PreferenceManager.getDefaultSharedPreferences(this);
		et_Login = (EditText) findViewById(R.id.etLogin);
		et_Password = (EditText) findViewById(R.id.etPassword);
		btn_Login = (Button) findViewById(R.id.btnLogin);
		btn_Login.setOnClickListener(this);
		chbx_save = (CheckBox) findViewById(R.id.chbx_save_auth);
		chbx_save.setChecked(prefs.getBoolean(PrefActivity.APP_PREF_AUTH_CHK, false));

		if (prefs.getBoolean(PrefActivity.APP_PREF_AUTH_CHK, false)) {
			et_Login.setText(prefs.getString(PrefActivity.APP_PREF_USERNAME, ""));
			et_Password.setText(prefs.getString(PrefActivity.APP_PREF_PASSWORD, ""));
			if (et_Login.getText().toString().trim().equals(LOGIN_ADMIN) || et_Login.getText().toString().trim().equals(LOGIN_GUEST)) {
				btn_Login.performClick();
			}
		}
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btnLogin:
			if (checkLogin().equals(LOGIN_ADMIN)) {
				Intent intent = new Intent(this, DashboardDesignActivity.class);
				Editor e = prefs.edit();
				String userName = et_Login.getText().toString();
				String password = et_Password.getText().toString();
				e.putString(PrefActivity.APP_PREF_USERNAME, userName);
				e.putString(PrefActivity.APP_PREF_PASSWORD, password);
				if (chbx_save.isChecked()) 
				{
					e.putBoolean(PrefActivity.APP_PREF_AUTH_CHK, true);
				} else {
					e.putBoolean(PrefActivity.APP_PREF_AUTH_CHK, false);
				}
				e.commit();
				startActivity(intent);
				finish();
				Log.i(LOGTAG, "Login like admin");
			} else if (checkLogin().equals(LOGIN_GUEST)) {
				if (db.getInterviewCount() > 0){
				Intent intent = new Intent(this, InterviewGuest.class);
				startActivity(intent);
				Log.i(LOGTAG, "Login like guest");
				finish();
				et_Login.setText("");
				et_Password.setText("");
				}else {
					getDialog().showWarning(this, "DB have not any interviewer");
				}
			}

			break;

		}

	}

	private String checkLogin() {
		if (et_Login.getText().toString().trim().length() == 0) {
			getDialog().showError(getApplicationContext(), getResources().getString(R.string.err_login));
			return Extras.EMPTY_STRING;
		}
		if (et_Login.getText().toString().trim().equals(LOGIN_ADMIN)) {
			return LOGIN_ADMIN;
		} else if (et_Login.getText().toString().trim().equals(LOGIN_GUEST)) {
			return LOGIN_GUEST;

		}
		getDialog().showError(getApplicationContext(), getResources().getString(R.string.err_login));
		return Extras.EMPTY_STRING;
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case DialogInterface.BUTTON_POSITIVE:
						finish();
						System.exit(0);
						break;
					}
				}
			};

			MessageToast.showDialog(this, getString(R.string.app_exit), getString(R.string.app_exit_mess), listener);
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
			return super.onOptionsItemSelected(item);
		
	}
}
