package com.kmware.hrm;

import java.util.ArrayList;

import com.kmware.hrm.adapters.CustomContainerAdapter;
import com.kmware.hrm.adapters.CustomInterviewerAdapter;
import com.kmware.hrm.adapters.CustomPeopleAdapter;
import com.kmware.hrm.adapters.CustomPositionAdapter;
import com.kmware.hrm.adapters.CustomProjectAdapter;
import com.kmware.hrm.model.BaseModel;
import com.kmware.hrm.model.Interviewer;
import com.kmware.hrm.model.People;
import com.kmware.hrm.model.Position;
import com.kmware.hrm.model.Project;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class ListContainer extends ZActivity implements OnClickListener {

	public static String LOGTAG = ListContainer.class.getSimpleName();
	private static final int RES_ADD = 0;
	private static final int RES_EDIT = 1;
	private final int CHECK_R_PERFORM_CLICK = 0;
	private final int CHECK_R_ID = 1;
	private final int CHECK_CLASS = 2;
	private final int CHECK_INTENT_EDIT = 3;
	private final int CHECK_INTENT_ADD = 4;

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
	private boolean addButton;
	private ArrayList<BaseModel> dataList;
	private CustomContainerAdapter<? extends BaseModel> listAdapter;
	private ArrayList<People> t_people;
	private ArrayList<Project> t_project;
	private ArrayList<Position> t_position;
	private ArrayList<Interviewer> t_interview;

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		setContentView(R.layout.list_container);
		super.onCreate(savedInstanceState);

		backHomeBar(R.drawable.actionbar_back_indicator, DashboardDesignActivity.createIntent(this));
		addprefBarBtn(android.R.drawable.ic_menu_search, new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (findViewById(R.id.filter_menu).isShown())
					((LinearLayout) findViewById(R.id.filter_menu)).setVisibility(View.GONE);
				else
					((LinearLayout) findViewById(R.id.filter_menu)).setVisibility(View.VISIBLE);

			}
		});

		init();

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

		addButton = false;
		getExtra();
		checkingCategory(CHECK_R_PERFORM_CLICK);

		edt_Search = (EditText) findViewById(R.id.edt_Search);
		edt_Search.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
				listAdapter.getFilter().filter(cs);
			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
			}

			@Override
			public void afterTextChanged(Editable arg0) {
			}
		});

		lv_Conteiner.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				startActivity((Intent) checkingCategory(CHECK_CLASS));
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

	private Object checkingCategory(int checkFilter) {
		Object o = null;

		switch (checkFilter) {
		case 0:
			if (extra.equals(getResources().getString(R.string.cat_people)))
				o = iv_People.performClick();
			if (extra.equals(getResources().getString(R.string.cat_projects)))
				o = iv_Project.performClick();
			if (extra.equals(getResources().getString(R.string.cat_positions)))
				o = iv_Positions.performClick();
			if (extra.equals(getResources().getString(R.string.cat_interviews)))
				o = iv_Interviews.performClick();
			break;
		case 1:
			if (extra.equals(getResources().getString(R.string.cat_people)))
				o = 1;
			if (extra.equals(getResources().getString(R.string.cat_projects)))
				o = 2;
			if (extra.equals(getResources().getString(R.string.cat_positions)))
				o = 3;
			if (extra.equals(getResources().getString(R.string.cat_interviews)))
				o = 4;
			break;
		case 2:
			if (extra.equals(getResources().getString(R.string.cat_people)))
				o = new Intent(ListContainer.this, PeopleInfo.class);
			if (extra.equals(getResources().getString(R.string.cat_projects)))
				o = new Intent(ListContainer.this, ProjectInfo.class);
			if (extra.equals(getResources().getString(R.string.cat_positions)))
				o = new Intent(ListContainer.this, PositionInfo.class);
			if (extra.equals(getResources().getString(R.string.cat_interviews)))
				o = new Intent(ListContainer.this, PositionInfo.class);
			break;
		case 3:
			if (extra.equals(getResources().getString(R.string.cat_people)))
				o = new Intent(ListContainer.this, EditPeople.class);
			if (extra.equals(getResources().getString(R.string.cat_projects)))
				o = new Intent(ListContainer.this, EditProject.class);
			if (extra.equals(getResources().getString(R.string.cat_positions)))
				o = new Intent(ListContainer.this, EditPosition.class);
			if (extra.equals(getResources().getString(R.string.cat_interviews)))
				o = new Intent(ListContainer.this, EditInterview.class);
			break;
		}
		return o;
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
			// Filling of the list of peoples by random values
			t_people = new ArrayList<People>();
			for (int i = 0; i < 20; i++) {
				People p = new People(i, "Man" + i, "p" + System.currentTimeMillis());
				t_people.add(p);
			}

			listAdapter = new CustomPeopleAdapter(this, t_people, R.layout.list_container_row_people);
			lv_Conteiner.setAdapter(listAdapter);

			addButton();
			addButton = true;
			break;
		case R.id.iv_Projects:
			// iv_subTitle.setImageResource(R.drawable.cat_project);
			bar.setTitleIco(R.drawable.cat_project);
			extra = getResources().getString(R.string.cat_projects);
			// Filling of the list of projects by random values
			t_project = new ArrayList<Project>();
			for (int i = 0; i < 20; i++) {
				Project p = new Project(i, "Project" + i);
				p.seteData(":" + (System.currentTimeMillis() / 1000));
				p.setsData(":" + (System.currentTimeMillis() / 1000));
				t_project.add(p);
			}

			listAdapter = new CustomProjectAdapter(this, t_project, R.layout.list_container_row_project);
			lv_Conteiner.setAdapter(listAdapter);

			if (addButton) {
				deleteBarBtn(1);
				addButton = false;
			}
			break;
		case R.id.iv_Positions:
			// iv_subTitle.setImageResource(R.drawable.cat_position);
			bar.setTitleIco(R.drawable.cat_position);
			extra = getResources().getString(R.string.cat_positions);
			// Filling of the list of positions by random values
			t_position = new ArrayList<Position>();
			for (int i = 0; i < 20; i++) {
				Position p = new Position(i, "Position" + i);
				t_position.add(p);
			}

			listAdapter = new CustomPositionAdapter(this, t_position, R.layout.list_container_row_position);
			lv_Conteiner.setAdapter(listAdapter);

			addButton();
			addButton = true;
			break;
		case R.id.iv_Interviews:
			// iv_subTitle.setImageResource(R.drawable.cat_intervies);
			bar.setTitleIco(R.drawable.cat_intervies);
			extra = getResources().getString(R.string.cat_interviews);
			// Filling of the list of positions by random values
			t_interview = new ArrayList<Interviewer>();
			for (int i = 0; i < 20; i++) {
				Interviewer p = new Interviewer(i, "Position" + i);
				p.setPhone("" + i * 1234);
				p.setPosition("position" + i * 2);
				p.setProject("Project" + i * 5);
				t_interview.add(p);
			}

			listAdapter = new CustomInterviewerAdapter(this, t_interview, R.layout.list_container_row_interview);
			lv_Conteiner.setAdapter(listAdapter);

			addButton();
			addButton = true;
			break;
		}
		current = (Button) v;
		parent.removeView(current);
	}

	private void addButton() {
		if (!addButton) {
			addprefBarBtn(android.R.drawable.ic_input_add, new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (extra.length() > 0)
						startActivityForResult((Intent) checkingCategory(CHECK_INTENT_EDIT)/* CHECK_INTENT_ADD */, RES_EDIT);
				}
			});
		}
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

		case RES_ADD:
			if (resultCode == RESULT_OK) {
				listAdapter.notifyDataSetChanged();
			}
			break;
		}
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_list_conteiner, menu);
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		// AdapterContextMenuInfo info = (AdapterContextMenuInfo) item
		// .getMenuInfo();

		switch (item.getItemId()) {
		case R.id.lv_row_edit:
			Intent intent = new Intent();
			intent = (Intent) checkingCategory(CHECK_INTENT_EDIT);
			startActivityForResult(intent, RES_EDIT);
			return true;
		case R.id.lv_row_delete:
			switch((Integer) checkingCategory(CHECK_R_ID)){
			case 1:
				Log.e(LOGTAG,""+lv_Conteiner.getSelectedItemPosition());
				break;
			case 2:
				//t_project.remove(item.getItemId());
				break;
			case 3:
				//t_position.remove(item.getItemId());
				break;
			case 4:
				//t_interview.remove(item.getItemId());
				break;
			}
			
			return true;
		}
		return false;
	}

}
