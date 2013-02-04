package com.kmware.hrm.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.AttributeSet;
import android.widget.TextView;

public class SimpleTextView extends TextView{
	private final int DEFAULT_TEXT_SIZE = 14;
	private int textSize = DEFAULT_TEXT_SIZE;
	SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());;

	public SimpleTextView(Context context) {
		super(context);
		try {
			textSize = Integer.parseInt(prefs.getString("pref_text_size", "" + DEFAULT_TEXT_SIZE));
			this.setTextSize(textSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public SimpleTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		try {
			textSize = Integer.parseInt(prefs.getString("pref_text_size", "" + DEFAULT_TEXT_SIZE));
			this.setTextSize(textSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public SimpleTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		try {
			textSize = Integer.parseInt(prefs.getString("pref_text_size", "" + DEFAULT_TEXT_SIZE));
			this.setTextSize(textSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void setGlobalTextSize(int size) {
		textSize = size;
	}

	public int getGlobalTextSize() {
		return textSize;
	}

}	
