package com.kmware.hrm;

import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.Position;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PositionInfo extends ZActivity {
	public static String LOGTAG = PositionInfo.class.getSimpleName();

	TextView tv_position_name;
	TextView tv_position_description;

	private DatabaseHandler db;
	private Position position;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.position);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));

		setTitle(getResources().getString(R.string.people_info));

		init();

	}

	private void init() {

		db = new DatabaseHandler(this);

		tv_position_name = (TextView) findViewById(R.id.tv_position_name);
		bar.setTitle(tv_position_name.getText().toString());
		tv_position_description = (TextView) findViewById(R.id.tv_position_description);

		try {
			position = new Position();
			position.setPosition(db.getPosition(getIntent().getIntExtra("ID", 0)));
			tv_position_name.setText(position.getName());
			tv_position_description.setText(position.getDescription());
		} catch (SQLiteException ex) {
			ex.printStackTrace();
			Log.w(LOGTAG, "Position DB have not id = " + getIntent().getIntExtra("ID", 0));
		}

	}

}
