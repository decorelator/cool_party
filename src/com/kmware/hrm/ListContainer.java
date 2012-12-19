package com.kmware.hrm;

import java.util.ArrayList;

import model.BaseModel;

import android.content.Intent;
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

public class ListContainer extends ZActivity implements OnClickListener {

	public static String LOGTAG = ListContainer.class.getSimpleName();
	private static final int RES_EDIT = 1;
	
	
	Button iv_People;
	Button iv_Project;
	Button iv_Positions;
	Button iv_Interviews;
	ImageView iv_subTitle;
	TextView tv_subTitle;
	EditText tv_Search;
	LinearLayout ll_NavigationButtons;

	private String extra;
	ArrayList<BaseModel> dataList = new ArrayList<BaseModel>();
	CustomContainerAdapter listAdapter;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.list_container);
		super.onCreate(savedInstanceState);
		setTitle("list", R.drawable.cat_people);

		addprefBarBtn(android.R.drawable.ic_menu_search, new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (findViewById(R.id.filter_menu).isShown())
					((LinearLayout) findViewById(R.id.filter_menu))
							.setVisibility(View.GONE);
				else
					((LinearLayout) findViewById(R.id.filter_menu))
							.setVisibility(View.VISIBLE);

			}
		});
		addprefBarBtn(android.R.drawable.ic_input_add, new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				
				if (extra.equals(getResources().getString(R.string.cat_people))) {
					intent = new Intent(ListContainer.this, EditPeople.class);
				}
				if (extra.equals(getResources()
						.getString(R.string.cat_projects))) {
					intent = new Intent(ListContainer.this, EditProject.class);
				}
				if (extra.equals(getResources().getString(
						R.string.cat_positions))) {
					intent = new Intent(ListContainer.this, EditPeople.class);
				}
				if (extra.equals(getResources().getString(
						R.string.cat_interviews))) {
					intent = new Intent(ListContainer.this, EditPeople.class);
				}
				
				startActivityForResult(intent, RES_EDIT);
			}
		});
		init();

	}

	private void createNavigationButtons(int id) {
		// —оздание LayoutParams c шириной и высотой по содержимому
		LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

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

		getExtra();

		createNavigationButtons(R.id.iv_People);
		fillData();
		listAdapter = new CustomContainerAdapter(this, dataList,
				R.layout.list_container_row);

		// настраиваем список
		ListView lv_Conteiner = (ListView) findViewById(R.id.lv_Conteiner);
		lv_Conteiner.setAdapter(listAdapter);
	}

	private void getExtra() {
		Bundle extras = getIntent().getExtras();

		if (extras != null) {
			try {
				extra = extras.getString(Extras.DASHBOARD_INTENT);
			} catch (Exception e) {

				e.printStackTrace();
			}
			
		}
	}

	// генерируем данные дл€ адаптера
	void fillData() {
		for (int i = 1; i <= 20; i++) {
			dataList.add(new BaseModel(i, "" + i * 1000));
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_People:
			// iv_subTitle.setImageResource(R.drawable.cat_people);
			extra = getResources().getString(R.string.cat_people);
			setVisability(R.id.iv_People);
			break;
		case R.id.iv_Projects:
			// iv_subTitle.setImageResource(R.drawable.cat_project);
			extra = getResources().getString(R.string.cat_projects);
			setVisability(R.id.iv_Projects);
			break;
		case R.id.iv_Positions:
			// iv_subTitle.setImageResource(R.drawable.cat_position);
			extra = getResources().getString(R.string.cat_positions);
			setVisability(R.id.iv_Positions);
			break;
		case R.id.iv_Interviews:
			extra = getResources().getString(R.string.cat_interviews);
			// iv_subTitle.setImageResource(R.drawable.cat_intervies);
			setVisability(R.id.iv_Interviews);
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode){
      	
  		case RES_EDIT:
  			
 				if (resultCode == RESULT_OK){
 					listAdapter.notifyDataSetChanged ();
  				}
  			break;
	}	
	}
	
	protected void setVisability(int id) {
		iv_People.setVisibility(View.VISIBLE);
		iv_Project.setVisibility(View.VISIBLE);
		iv_Positions.setVisibility(View.VISIBLE);
		iv_Interviews.setVisibility(View.VISIBLE);
		findViewById(id).setVisibility(View.GONE);
		
	}
}
