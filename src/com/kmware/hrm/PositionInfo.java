package com.kmware.hrm;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

public class PositionInfo extends ZActivity {
	public static String LOGTAG = PositionInfo.class.getSimpleName();

	TextView tv_position_name;
	TextView tv_position_description;
	
	String extra;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.position);
		super.onCreate(savedInstanceState);
		getExtra();

		setTitle(getResources().getString(R.string.people_info));

		init();

	}

	private void init() {

		tv_position_name = (TextView) findViewById(R.id.tv_position_name);
		tv_position_description = (TextView) findViewById(R.id.tv_position_description);

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

}
