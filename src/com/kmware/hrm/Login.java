package com.kmware.hrm;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener{
	
	private static final String LOGTAG = Login.class.getSimpleName();
	
	
	private Button btn_Login;
	private EditText et_Login;
	private EditText et_Password;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		init();
	}
	
	private void init(){
		
		et_Login = (EditText) findViewById(R.id.etLogin);
		et_Password = (EditText) findViewById(R.id.etPassword);
		btn_Login = (Button) findViewById(R.id.btnLogin);
		btn_Login.setOnClickListener(this);
		
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()){
		
		case R.id.btnLogin:
			Intent intent = new Intent(this, ListContainer.class);
			startActivity(intent);
			finish();
			break;	
			
		
	}
		
	}
}
