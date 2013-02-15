package com.kmware.hrm;

import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.Position;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

public class EditPosition extends ZActivity {
	public static String LOGTAG = EditPosition.class.getSimpleName();

	EditText edt_PositionName;
	EditText edt_PositionDescription;

	private DatabaseHandler db;
	private Intent extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_position);
		super.onCreate(savedInstanceState);
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		if (extra != null) {
			setTitle(getResources().getString(R.string.position_edit), android.R.drawable.ic_input_add);
		} else {
			setTitle(getResources().getString(R.string.position_add), android.R.drawable.ic_input_add);
		}

		addprefBarBtn(android.R.drawable.ic_menu_save, new OnClickListener() {

			@Override
			public void onClick(View v) {
				save();
			}
		});

		init();

	}

	private void init() {

		db = new DatabaseHandler(this);
		extra = getIntent();

		edt_PositionName = (EditText) findViewById(R.id.edt_position_name);
		edt_PositionDescription = (EditText) findViewById(R.id.edt_position_description);

		if (extra.getIntExtra("ID", 0) != 0) {
			edt_PositionName.setText((db.getPosition(extra.getIntExtra("ID", 0))).getName().toString());
			edt_PositionDescription.setText((db.getPosition(extra.getIntExtra("ID", 0))).getDescription().toString());
		}

	}

	private void save() {

		if (edt_PositionName.getText().toString().trim().length() > 0) {
			Position position = new Position();

			position.setName(edt_PositionName.getText().toString());
			position.setDescription(edt_PositionDescription.getText().toString());
			if (extra.getIntExtra("ID", 0) == 0) {
				db.addPosition(position);
			} else {
				position.setId(extra.getIntExtra("ID", 0));
				db.updatePosition(position);
			}
			setResult(RESULT_OK);
			finish();
		} else {
			Toast toast = Toast.makeText(getApplicationContext(), "Enter the name", Toast.LENGTH_SHORT);
			toast.show();
		}

	}

}
