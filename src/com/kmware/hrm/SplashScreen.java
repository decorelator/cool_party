package com.kmware.hrm;

import android.os.Bundle;
import android.view.KeyEvent;
import android.app.Activity;
import android.content.Intent;

public class SplashScreen extends Activity {

	private long splashTime = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		Thread timer = new Thread() {
			public void run() {
				try {
					sleep(splashTime);
					Intent intent = new Intent(SplashScreen.this, Login.class);
					startActivity(intent);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();
				}
			}
		};
		timer.start();
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	        return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
}
