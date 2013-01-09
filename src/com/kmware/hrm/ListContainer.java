package com.kmware.hrm;

import java.util.ArrayList;
import java.util.Collections;

import com.kmware.hrm.model.BaseModel;
import com.kmware.hrm.model.People;



import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class ListContainer extends ZActivity implements OnClickListener {

	public static String LOGTAG = ListContainer.class.getSimpleName();
	private static final int RES_ADD = 0;
	private static final int RES_EDIT = 1;
	private Button current;

	Button iv_People;
	Button iv_Project;
	Button iv_Positions;
	Button iv_Interviews;
	ImageView iv_subTitle;
	TextView tv_subTitle;
	EditText edt_Search;
	LinearLayout ll_NavigationButtons;
	LinearLayout parent;
	ListView lv_Conteiner;
	private String extra;
	private int currentPos;
	ArrayList<BaseModel> dataList;// = new ArrayList<BaseModel>();
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

				if (extra.length() > 0)
					startActivityForResult(intentCheck(extra), RES_EDIT);
			}
		});
	
		init();

	}

	private void createNavigationButtons(int id) {
		// Создание LayoutParams c шириной и высотой по содержимому
		LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

	}

	private void init() {

		ll_NavigationButtons = (LinearLayout) findViewById(R.id.ll_NavigationButtons);

		iv_People = (Button) findViewById(R.id.iv_People);
		iv_People.setTag(0);
		iv_People.setOnClickListener(this);

		iv_Project = (Button) findViewById(R.id.iv_Projects);
		iv_Project.setOnClickListener(this);
		iv_Project.setTag(0);

		iv_Positions = (Button) findViewById(R.id.iv_Positions);
		iv_Positions.setOnClickListener(this);
		iv_Positions.setTag(1);

		iv_Interviews = (Button) findViewById(R.id.iv_Interviews);
		iv_Interviews.setOnClickListener(this);
		iv_Interviews.setTag(2);

		current = iv_People;
		lv_Conteiner = (ListView) findViewById(R.id.lv_Conteiner);
		parent = (LinearLayout) current.getParent();
		parent.removeView(current);
		getExtra();
		fillData();

		if (extra.equals(getResources().getString(R.string.cat_people)))
			iv_People.performClick();
		if (extra.equals(getResources().getString(R.string.cat_projects)))
			iv_Project.performClick();
		if (extra.equals(getResources().getString(R.string.cat_positions)))
			iv_Positions.performClick();
		if (extra.equals(getResources().getString(R.string.cat_interviews)))
			iv_Interviews.performClick();

		createNavigationButtons(R.id.iv_People);

		lv_Conteiner.setAdapter(listAdapter);

		edt_Search = (EditText) findViewById(R.id.edt_Search);
		edt_Search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2,
					int arg3) {
				listAdapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});

		lv_Conteiner.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(ListContainer.this, PeopleInfo.class);
				startActivity(intent);
			}
		});

		registerForContextMenu(lv_Conteiner);

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

	// генерируем данные для адаптера
	void fillData() {
		dataList = new ArrayList<BaseModel>();
		for (int i = 1; i <= 20; i++) {
			dataList.add(new BaseModel(i, "" + i * 1000));
		}
	}

	@Override
	public void onClick(View v) {

		// l.removeView(current);
		parent.addView(current, (Integer) v.getTag());
		bar.setTitle(((Button) v).getText());
		current.setTag(v.getTag());
		switch (v.getId()) {
		case R.id.iv_People:
			// iv_subTitle.setImageResource(R.drawable.cat_people);
			bar.setTitleIco(R.drawable.cat_people);
			extra = getResources().getString(R.string.cat_people);
			setVisability(R.id.iv_People);
			ArrayList<BaseModel> t = new ArrayList<BaseModel>();

			for(BaseModel model:dataList){
				BaseModel p = new People(model.getId(), model.getName(),"p"+System.currentTimeMillis());
				t.add(p);
			}
			
			listAdapter = new CustomPeopleAdapter(this, t, R.layout.list_container_row_people); 
			//lv_Conteiner.setAdapter(listAdapter);
			break;
		case R.id.iv_Projects:
			// iv_subTitle.setImageResource(R.drawable.cat_project);
			bar.setTitleIco(R.drawable.cat_project);
			extra = getResources().getString(R.string.cat_projects);
			setVisability(R.id.iv_Projects);
			break;
		case R.id.iv_Positions:
			// iv_subTitle.setImageResource(R.drawable.cat_position);
			bar.setTitleIco(R.drawable.cat_position);
			extra = getResources().getString(R.string.cat_positions);
			// setVisability(R.id.iv_Positions);
			break;
		case R.id.iv_Interviews:
			bar.setTitleIco(R.drawable.cat_intervies);
			extra = getResources().getString(R.string.cat_interviews);
			// iv_subTitle.setImageResource(R.drawable.cat_intervies);

			setVisability(R.id.iv_Interviews);
			break;
		}
		current = (Button) v;
		parent.removeView(current);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {

		case RES_EDIT:

			if (resultCode == RESULT_OK) {
				listAdapter.notifyDataSetChanged();
			}
			break;
		}
	}

	protected void setVisability(int id) {
		iv_People.setVisibility(View.VISIBLE);
		iv_Project.setVisibility(View.VISIBLE);
		iv_Positions.setVisibility(View.VISIBLE);
		iv_Interviews.setVisibility(View.VISIBLE);
		// findViewById(id).setVisibility(View.GONE);

	}

	private Intent intentCheck(String extra) {
		Intent intent = new Intent();

		if (extra.equals(getResources().getString(R.string.cat_people))) {
			intent = new Intent(ListContainer.this, EditPeople.class);
		}
		if (extra.equals(getResources().getString(R.string.cat_projects))) {
			intent = new Intent(ListContainer.this, EditProject.class);
		}
		if (extra.equals(getResources().getString(R.string.cat_positions))) {
			intent = new Intent(ListContainer.this, EditPosition.class);
		}
		if (extra.equals(getResources().getString(R.string.cat_interviews))) {
			intent = new Intent(ListContainer.this, EditInterview.class);
		}
		return intent;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_list_conteiner, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
//		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
//				.getMenuInfo();

		switch (item.getItemId()) {
		case R.id.lv_row_edit:
			Intent intent = new Intent();
			intent = intentCheck(extra);
			startActivityForResult(intent, RES_EDIT);
			return true;
		case R.id.lv_row_delete:
			
			return true;
		}
		return false;
	}

}
