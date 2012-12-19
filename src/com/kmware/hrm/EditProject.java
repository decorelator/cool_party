package com.kmware.hrm;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class EditProject extends ZActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.edit_project);
		super.onCreate(savedInstanceState);
		addprefBarBtn(android.R.drawable.ic_menu_save, new OnClickListener() {

			@Override
			public void onClick(View v) {
				setResult(RESULT_OK);
				// save();
				finish();
			}
		});
		// getExtra();
		// init();
	}
}