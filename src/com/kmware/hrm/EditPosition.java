package com.kmware.hrm;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

public class EditPosition extends ZActivity{
	public static String LOGTAG = EditPosition.class.getSimpleName();

	EditText edt_PositionName;
	EditText edt_PositionDescription;
	
	private String extra;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_position);
		super.onCreate(savedInstanceState);
		getExtra();
		if (extra.toString().length() != 0) {
			setTitle(getResources().getString(R.string.position_edit),
					android.R.drawable.ic_input_add);
		} else {
			setTitle(getResources().getString(R.string.position_add),
					android.R.drawable.ic_input_add);
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

		edt_PositionName = (EditText) findViewById(R.id.edt_position_name);
		edt_PositionDescription = (EditText) findViewById(R.id.edt_position_description);
		
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

	private void save() {
		setResult(RESULT_OK);
		finish();
	}

}
