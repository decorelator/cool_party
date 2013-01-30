package com.kmware.hrm.preferences;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceClickListener;
import android.util.Log;
import android.view.KeyEvent;
import com.kmware.hrm.R;

public class PrefActivity extends PreferenceActivity {

	SharedPreferences prefs;
	//CheckBoxPreference chbx_pref_auth;
	ListPreference lv_pref_theme;
	EditTextPreference edt_pref_text_size;
	EditTextPreference edt_pref_entered_text;

	//public Boolean APP_PREF_AUTH_CHK;
	public static final String APP_PREF_THEME = "pref_theme";
	public static final String APP_PREF_AUTH_CHK = "Authorizathion";
	public static final String APP_PREF_TEXT_SIZE = "Pref_text_size";
	public static final String APP_PREF_USERNAME = "username";
	public static final String APP_PREF_PASSWORD = "password";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.pref);
		init();
	}

	public void init() {
		prefs = PreferenceManager.getDefaultSharedPreferences(this);

//		APP_PREF_AUTH_CHK = prefs.getBoolean("Authorizathion", false);
//		APP_PREF_THEME = prefs.getString(APP_PREF_THEME, "Orange");
//		APP_PREF_TEXT_SIZE = prefs.getString("Pref_text_size", "14");
//		if (!prefs.getString("userName", "").equals("") && !prefs.getString("password", "").equals("")) {
//			APP_PREF_USERNAME = prefs.getString("userName", "");
//			Log.e("LOG1", "1"+APP_PREF_USERNAME);
//			APP_PREF_PASSWORD = prefs.getString("password", "");
//		}

//		chbx_pref_auth = (CheckBoxPreference) findPreference("Authorizathion");
//		chbx_pref_auth.setOnPreferenceClickListener(new OnPreferenceClickListener() {
//			public boolean onPreferenceClick(Preference preference) {
//				Log.e("CHECH", "2"+APP_PREF_USERNAME);
//				Editor e = prefs.edit();
//				if (!chbx_pref_auth.isChecked()) {
//					e.putBoolean("Authorizathion", false);
//					e.remove("userName");
//					e.remove("password");
//
//				} else {
//					e.putBoolean("Authorizathion", true);
//					Log.e("LOG2", "3"+APP_PREF_USERNAME);
//					e.putString("userName", APP_PREF_USERNAME);
//					e.putString("password", APP_PREF_PASSWORD);
//				}
//				e.commit();
//				return true;
//			}
//		});

		lv_pref_theme = (ListPreference) findPreference("pref_theme");

	}
//
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		if (keyCode == KeyEvent.KEYCODE_BACK) {
//					if (!chbx_pref_auth.isChecked()) {
//						Editor e = prefs.edit();
//						e.remove("userName");
//						e.remove("password");
//						e.commit();
//						Log.e("BAck", "4"+ APP_PREF_USERNAME);
//					}
//					Log.e("BAck2","5"+ APP_PREF_USERNAME);
//		}
//		return super.onKeyDown(keyCode, event);
//	}

}