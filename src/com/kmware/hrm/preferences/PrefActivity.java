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
import android.view.KeyEvent;
import com.kmware.hrm.R;

public class PrefActivity extends PreferenceActivity {

	SharedPreferences prefs;
	CheckBoxPreference chbx_pref_auth;
	ListPreference lv_pref_theme;
	EditTextPreference edt_pref_text_size;
	EditTextPreference edt_pref_entered_text;
	
	public Boolean APP_PREF_AUTH_CHK ;
	public String APP_PREF_THEME ;
	public String APP_PREF_TEXT_SIZE ;
	public String APP_PREF_TEXT_SIZE_ENTERED ;
	public String APP_PREF_USERNAME;
	public String APP_PREF_PASSWORD;
	
	
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.pref);
    init();
  }
  
  public void init(){
	  prefs = PreferenceManager.getDefaultSharedPreferences(this); 
	  
	  APP_PREF_AUTH_CHK = prefs.getBoolean("Authorizathion", false);
	  APP_PREF_THEME  = prefs.getString("pref_theme", "Orange");
	  APP_PREF_TEXT_SIZE  = prefs.getString("Pref_text_size", "14");
	  APP_PREF_TEXT_SIZE_ENTERED  = prefs.getString("Pref_text_size_entered", "14");
	  APP_PREF_USERNAME = prefs.getString("userName", "");
	  APP_PREF_PASSWORD = prefs.getString("password", "");
	  
	  chbx_pref_auth = (CheckBoxPreference) findPreference("Authorizathion");
	  chbx_pref_auth.setOnPreferenceClickListener(new OnPreferenceClickListener() {
			public boolean onPreferenceClick(Preference preference) {
				 Editor e = prefs.edit();
				if(!chbx_pref_auth.isChecked())
				  {
					e.putBoolean("Authorizathion", false);
					e.remove("userName");
					e.remove("password");
					  
				  }
				else {
					e.putBoolean("Authorizathion", true);
					e.putString("userName", APP_PREF_USERNAME);
					e.putString("password", APP_PREF_PASSWORD);
				}
				e.commit();
				return true;
			}
		});    
	 
	  lv_pref_theme = (ListPreference) findPreference("pref_theme");
	  
	  
	  
  }
  
  @Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			chbx_pref_auth.setOnPreferenceClickListener(new OnPreferenceClickListener() {
							public boolean onPreferenceClick(Preference preference) {
								if(!chbx_pref_auth.isChecked())
								  {
									  Editor e = prefs.edit();
									  e.remove("userName");
									  e.remove("password");
									  e.commit();
								  }
								return true;
							}
						});    

		}
		return super.onKeyDown(keyCode, event);
	}
	
	
}