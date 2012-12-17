package com.kmware.hrm.login;

import com.kmware.hrm.DashboardDesignActivity;
import com.kmware.hrm.Extras;
import com.kmware.hrm.R;
import com.kmware.hrm.actionbar.ActionBar;
import com.kmware.hrm.actionbar.ActionBar.IntentAction;
import com.kmware.hrm.preferences.PrefActivity;
import com.kmware.hrm.view.MessageToast;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener {

	private static final String LOGTAG = Login.class.getSimpleName();

	private static final String LOGIN_ADMIN = "admin";
	private static final String LOGIN_GUEST = "guest";

	private Button btn_Login;
	private EditText et_Login;
	private EditText et_Password;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		createActionBar();
		init();
	}

	private void init() {

		et_Login = (EditText) findViewById(R.id.etLogin);
		et_Password = (EditText) findViewById(R.id.etPassword);
		btn_Login = (Button) findViewById(R.id.btnLogin);
		btn_Login.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {

		case R.id.btnLogin:
			if (checkLogin().equals(LOGIN_ADMIN)) {
				Intent intent = new Intent(this, DashboardDesignActivity.class);
				startActivity(intent);
				finish();
			} else if (checkLogin().equals(LOGIN_GUEST)) {
				Intent intent = new Intent(this, DashboardDesignActivity.class);
				// startActivity(intent);
			}
			// finish();
			break;

		}

	}

	private String checkLogin() {
		if (et_Login.getText().toString().trim().length() == 0) {
			MessageToast.showError(getApplicationContext(), getResources()
					.getString(R.string.err_login));
			return Extras.EMPTY_STRING;
		}
		if (!et_Login.getText().toString().trim().equals(LOGIN_ADMIN)) {
			return LOGIN_GUEST;
		}
		return LOGIN_ADMIN;
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		MenuItem mi = menu.add(0, 1, 0, "Preferences");
		mi.setIntent(new Intent(this, PrefActivity.class));
		return super.onCreateOptionsMenu(menu);
	}

	private void createActionBar() {
		ActionBar actionBar = (ActionBar) findViewById(R.id.actionbar);
		actionBar.setTitle(R.string.home);
		// actionBar.addAction(new IntentAction(this,
		// DashboardDesignActivity.createIntent(this), R.drawable.act_pref));

	}

	// public static Intent createIntent(Context context) {
	// Intent i = new Intent(context, PrefActivity.class);
	// i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	// return i;
	// }

}
