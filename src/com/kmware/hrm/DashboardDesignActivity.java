package com.kmware.hrm;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DashboardDesignActivity extends Activity implements
		OnClickListener {

	public static String LOGTAG = DashboardDesignActivity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.dashboard_layout);
		init();

	}

	private void init() {
		/**
		 * Creating all buttons instances
		 * */
		// Dashboard People button
		Button btn_people = (Button) findViewById(R.id.btn_people);
		// Dashboard Project button
		Button btn_projects = (Button) findViewById(R.id.btn_projects);
		// Dashboard Position button
		Button btn_position = (Button) findViewById(R.id.btn_positions);
		// Dashboard Interview button
		Button btn_interview = (Button) findViewById(R.id.btn_interviews);
		/**
		 * Handling all button click events
		 * */
		btn_people.setOnClickListener(this);
		btn_projects.setOnClickListener(this);
		btn_position.setOnClickListener(this);
		btn_interview.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent (this, ListContainer.class);
		switch (v.getId()) {
		case R.id.btn_people:
			intent.putExtra(Extras.DASHBOARD_INTENT, getResources().getString(R.string.cat_people));
			startActivity(intent);
			break;
		case R.id.btn_projects:
			intent.putExtra(Extras.DASHBOARD_INTENT, getResources().getString(R.string.cat_projects));
			startActivity(intent);
			break;
		case R.id.btn_positions:
			intent.putExtra(Extras.DASHBOARD_INTENT, getResources().getString(R.string.cat_positions));
			startActivity(intent);
			break;
		case R.id.btn_interviews:
			intent.putExtra(Extras.DASHBOARD_INTENT, getResources().getString(R.string.cat_interviews));
			startActivity(intent);
			break;
		}
		
	}

}
