package com.kmware.hrm;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

	ArrayList<ContainerRow> dataList = new ArrayList<ContainerRow>();
	CustomContainerAdapter listAdapter;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_container);
		init();

	}

	private void init() {

		

		iv_People = (Button) findViewById(R.id.ivPeople);
		iv_People.setOnClickListener(this);
		iv_Project = (Button) findViewById(R.id.ivProjects);
		iv_Project.setOnClickListener(this);
		iv_Positions = (Button) findViewById(R.id.ivPositions);
		iv_Positions.setOnClickListener(this);
		iv_Interviews = (Button) findViewById(R.id.ivInterviews);
		iv_Interviews.setOnClickListener(this);
		iv_subTitle = (ImageView) findViewById(R.id.ivTitle);
		tv_subTitle = (TextView) findViewById(R.id.tvConteinerTitle);
		// создаем адаптер
		getExtra();
		fillData();
		listAdapter = new CustomContainerAdapter(this, dataList);

		// настраиваем список
		ListView lvConteiner = (ListView) findViewById(R.id.lvConteiner);
		lvConteiner.setAdapter(listAdapter);
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
		case R.id.ivPeople:
			iv_subTitle.setImageResource(R.drawable.cat_people);
			setVisability(R.id.ivPeople);
			break;
		case R.id.ivProjects:
			iv_subTitle.setImageResource(R.drawable.cat_project);
			setVisability(R.id.ivProjects);
			break;
		case R.id.ivPositions:
			iv_subTitle.setImageResource(R.drawable.cat_position);
			setVisability(R.id.ivPositions);
			break;
		case R.id.ivInterviews:
			iv_subTitle.setImageResource(R.drawable.cat_intervies);
			setVisability(R.id.ivInterviews);
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
