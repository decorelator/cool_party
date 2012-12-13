package login;


import com.kmware.hrm.DashboardDesignActivity;
import com.kmware.hrm.R;
import com.kmware.hrm.R.id;
import com.kmware.hrm.R.layout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Login extends Activity implements OnClickListener{

	private static final String LOGTAG = Login.class.getSimpleName();
	
	private static final String LOGIN_ADMIN = "admin";
	private static final String LOGIN_GUEST = "guest";
	
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
			//PASSWORD
			if (checkLogin().equals(LOGIN_ADMIN))
			{
			Intent intent = new Intent(this, DashboardDesignActivity.class);
			startActivity(intent);
			}
			else{
				Intent intent = new Intent(this, DashboardDesignActivity.class);
				startActivity(intent);	
			}
			finish();
			break;	
			
		
	}
		
	}
	
	private String checkLogin(){
		
	return 	LOGIN_ADMIN;
	}
	
}
