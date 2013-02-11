package com.kmware.hrm;


import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.content.Intent;

public class SplashScreen extends ZActivity {

	public static String LOGTAG = SplashScreen.class.getSimpleName();
	private Thread timer;
	private long splashTime = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		try{
			timer = (Thread) getLastNonConfigurationInstance();
		}catch(Exception e){
			e.printStackTrace();
		}
		timer = new Thread() {
			public void run() {
				try {
					sleep(splashTime);
					Intent intent = new Intent(SplashScreen.this, Login.class);
//					intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
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
	
	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		Log.d(LOGTAG, "onRestoreInstanceState");
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		Log.d(LOGTAG, "onSaveInstanceState");
	}
	
	@Override
	public Object onRetainNonConfigurationInstance() {
	    return timer;
	  }
}
