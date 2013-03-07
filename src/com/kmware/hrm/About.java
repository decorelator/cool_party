package com.kmware.hrm;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class About extends ZActivity implements OnClickListener {

	private static final String LOGTAG = About.class.getSimpleName();
	
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.about_activity);
		super.onCreate(savedInstanceState);
		
		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
	}
	
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
