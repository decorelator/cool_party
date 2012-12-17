package com.kmware.hrm;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class ListContainer extends Activity implements OnClickListener {

	public static String LOGTAG = ListContainer.class.getSimpleName();
	
	Button iv_People;
	Button iv_Project;
	Button iv_Positions;
	Button iv_Interviews;
	ImageView iv_subTitle;
	TextView tv_subTitle;
	EditText tv_Search;
	LinearLayout ll_NavigationButtons;

	ArrayList<ContainerRow> dataList = new ArrayList<ContainerRow>();
	CustomContainerAdapter listAdapter;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_container);
		init();

	}

	private void createNavigationButtons(int id){
		// Создание LayoutParams c шириной и высотой по содержимому
		LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
		
		// Создание LayoutParams c шириной и высотой по содержимому
//	      LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
//	          wrapContent, wrapContent);
//	      // переменная для хранения значения выравнивания
//	      // по умолчанию пусть будет LEFT
//	      int btnGravity = Gravity.LEFT;
//	      // определяем, какой RadioButton "чекнут" и 
//	      // соответственно заполняем btnGravity 
//	      switch (rgGravity.getCheckedRadioButtonId()) {
//	      case R.id.rbLeft:
//	        btnGravity = Gravity.LEFT;
//	        break;
//	      case R.id.rbCenter:
//	        btnGravity = Gravity.CENTER_HORIZONTAL;
//	        break;
//	      case R.id.rbRight:
//	        btnGravity = Gravity.RIGHT;
//	        break;
//	      }
//	      // переносим полученное значение выравнивания в LayoutParams
//	      lParams.gravity = btnGravity;
//
//	      // создаем Button, пишем текст и добавляем в LinearLayout
//	      Button btnNew = new Button(this);
//	      btnNew.setText(etName.getText().toString());
//	      ll_NavigationButtons.addView(btnNew, lParams);
		
		
	}
	
	private void init() {

		
		ll_NavigationButtons = (LinearLayout) findViewById(R.id.ll_NavigationButtons);
		
		iv_People = (Button) findViewById(R.id.iv_People);
		iv_People.setOnClickListener(this);
		iv_Project = (Button) findViewById(R.id.iv_Projects);
		iv_Project.setOnClickListener(this);
		iv_Positions = (Button) findViewById(R.id.iv_Positions);
		iv_Positions.setOnClickListener(this);
		iv_Interviews = (Button) findViewById(R.id.iv_Interviews);
		iv_Interviews.setOnClickListener(this);
		iv_subTitle = (ImageView) findViewById(R.id.iv_Title);
		tv_subTitle = (TextView) findViewById(R.id.tv_ConteinerTitle);
		// создаем адаптер
		
		getExtra();
		createNavigationButtons(R.id.iv_People);
		fillData();
		listAdapter = new CustomContainerAdapter(this, dataList);

		// настраиваем список
		ListView lv_Conteiner = (ListView) findViewById(R.id.lv_Conteiner);
		lv_Conteiner.setAdapter(listAdapter);
	}

	private void getExtra(){
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			try{
			tv_subTitle.setText(extras.getString(Extras.DASHBOARD_INTENT));
			}catch(Exception e){
				
				e.printStackTrace();
			}
			if (iv_People.getText().equals(tv_subTitle.getText())) {
				iv_subTitle.setImageResource(R.drawable.cat_people);
			} else if (iv_Project.getText().equals(tv_subTitle.getText())) {
				iv_subTitle.setImageResource(R.drawable.cat_project);
			} else if (iv_Positions.getText().equals(tv_subTitle.getText())) {
				iv_subTitle.setImageResource(R.drawable.cat_position);
			} else {
				iv_subTitle.setImageResource(R.drawable.cat_intervies);
			}
		}
	}
	// генерируем данные для адаптера
	void fillData() {
		for (int i = 1; i <= 20; i++) {
			dataList.add(new ContainerRow("" + tv_subTitle.getText() + " " + i,
					"" + i * 1000));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_People:
			iv_subTitle.setImageResource(R.drawable.cat_people);
			setVisability(R.id.iv_People);
			break;
		case R.id.iv_Projects:
			iv_subTitle.setImageResource(R.drawable.cat_project);
			setVisability(R.id.iv_Projects);
			break;
		case R.id.iv_Positions:
			iv_subTitle.setImageResource(R.drawable.cat_position);
			setVisability(R.id.iv_Positions);
			break;
		case R.id.iv_Interviews:
			iv_subTitle.setImageResource(R.drawable.cat_intervies);
			setVisability(R.id.iv_Interviews);
			break;
		}

	}

	protected void setVisability(int id) {
		iv_People.setVisibility(View.VISIBLE);
		iv_Project.setVisibility(View.VISIBLE);
		iv_Positions.setVisibility(View.VISIBLE);
		iv_Interviews.setVisibility(View.VISIBLE);
		findViewById(id).setVisibility(View.GONE);
		tv_subTitle.setText(((Button) findViewById(id)).getText());
	}
}
