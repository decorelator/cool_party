package com.kmware.hrm;

import com.kmware.hrm.preferences.PrefActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class DashboardDesignActivity extends ZActivity implements OnClickListener {

	public static String LOGTAG = DashboardDesignActivity.class.getSimpleName();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.dashboard_layout);
		super.onCreate(savedInstanceState);
		final Intent i = new Intent(this, PrefActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		addprefBarBtn(R.drawable.act_pref, new OnClickListener() {

			@Override
			public void onClick(View v) {
				startActivity(i);

			}
		});

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

	void fillData() {
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this, ListContainer.class);
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

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	DialogInterface.OnClickListener listener= new DialogInterface.OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which){
						case DialogInterface.BUTTON_POSITIVE:
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
	
}
