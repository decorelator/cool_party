package com.kmware.hrm.preferences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.kmware.hrm.R;

public class PrefActivity extends PreferenceActivity {

	SharedPreferences prefs;
	CheckBoxPreference chbx_pref_auth;
	ListPreference lv_pref_theme;
	EditTextPreference edt_pref_text_size;
	EditTextPreference edt_pref_entered_text;
	
	
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    addPreferencesFromResource(R.xml.pref);
    init();
  }
  
  public void init(){
	  prefs = PreferenceManager.getDefaultSharedPreferences(this); 
	  chbx_pref_auth = (CheckBoxPreference) findPreference("Authorizathion");
	  lv_pref_theme = (ListPreference) findPreference("pref_theme");
	  
	  
	  final Boolean APP_PREF_AUTH_CHK = prefs.getBoolean("Authorizathion", false);
	  final String APP_PREF_THEME  = prefs.getString("pref_theme", "Orange");
	  final String APP_PREF_TEXT_SIZE  = prefs.getString("Pref_text_size", "14");
	  final String APP_PREF_TEXT_SIZE_ENTERED  = prefs.getString("Pref_text_size_entered", "14");
	  
  }
	
	
}