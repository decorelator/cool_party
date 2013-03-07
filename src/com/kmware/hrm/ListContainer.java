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
	private final int CHECK_R_PERFORM_CLICK = 0;
	private final int CHECK_R_ID = 1;
	private final int CHECK_CLASS = 2;
	private final int CHECK_INTENT_ADD = 3;

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
	private boolean addButton;
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
				if (getIntent().getStringExtra(Extras.DASHBOARD_INTENT) != null) {
					Intent intent = (Intent) checkingCategory(CHECK_CLASS);
					intent.putExtra("ID", position);
					startActivity(intent);
				}
				if (getIntent().getStringExtra("Interview") != null /*extra.equals(getResources().getString(R.string.cat_people)) || (extra.equals(getResources().getString(R.string.cat_positions)))*/) {
					Intent intent = new Intent();
					switch ((Integer)checkingCategory(CHECK_R_ID)) {
					case 1:
						intent.putExtra("Candidate", Test_Data.list_people.get(position).getId());
						break;
					case 3:
						intent.putExtra("Position", Test_Data.list_position.get(position).getId());
						break;
					}
					setResult(RESULT_OK, intent);
					finish();
				}
				// switch ((Integer) checkingCategory(CHECK_R_ID)) {
				// case 1:
				// intent.putExtra("ID", t_people.get(position).getId());
				// break;
				// case 2:
				// intent.putExtra("ID", t_project.get(position).getId());
				// break;
				// case 3:
				// intent.putExtra("ID", t_position.get(position).getId());
				// break;
				// case 4:
				// intent.putExtra("ID", t_interview.get(position).getId());
				// break;
				// }

			}
		});
		// registerForContextMenu(lv_Conteiner);

	}

	private void getExtra() {
		Bundle extras = getIntent().getExtras();
		if (extras != null && extras.getString(Extras.DASHBOARD_INTENT) != null) {
			try {
				extra = extras.getString(Extras.DASHBOARD_INTENT);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (extras != null && extras.getString("Interview") != null) {
			try {
				extra = extras.getString("Interview");
				findViewById(R.id.ll_buttons).setVisibility(View.GONE);
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
				o = new Intent(ListContainer.this, InterviewInfo.class);
			break;
		case 3:
			if (extra.equals(getResources().getString(R.string.cat_people)))
				o = new Intent(ListContainer.this, EditPeople.class);
			// if
			// (extra.equals(getResources().getString(R.string.cat_projects)))
			// o = new Intent(ListContainer.this, EditProject.class);
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
			try {
				t_people.addAll(Test_Data.list_people);
			} catch (Exception ex) {
				ex.printStackTrace();
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
			try {
				t_project.addAll(Test_Data.list_projects);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			listAdapter = new CustomProjectAdapter(this, t_project, R.layout.list_container_row_project);
			lv_Conteiner.setAdapter(listAdapter);

			if (addButton) {
				deleteBarBtn(1);
				addButton = false;
			}
			// addButton();
			// addButton = true;
			break;
		case R.id.iv_Positions:
			// iv_subTitle.setImageResource(R.drawable.cat_position);
			bar.setTitleIco(R.drawable.cat_position);
			extra = getResources().getString(R.string.cat_positions);
			// Filling of the list of positions by random values
			t_position = new ArrayList<Position>();
			try {
				t_position.addAll(Test_Data.list_position);
			} catch (Exception ex) {
				ex.printStackTrace();
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
			try {
				t_interview.addAll(Test_Data.list_interview);
			} catch (Exception ex) {
				ex.printStackTrace();
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
					if (extra.length() > 0) {
						Intent intent = new Intent();
						intent = (Intent) checkingCategory(CHECK_INTENT_ADD);
						startActivityForResult(intent, RES_ADD);
					}
				}
			});
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {

		case RES_ADD:
			if (resultCode == RESULT_OK) {

				try {
					switch ((Integer) checkingCategory(CHECK_R_ID)) {
					case 1:
						t_people.clear();
						t_people.addAll(Test_Data.list_people);
						break;
					case 2:
						t_project.clear();
						t_project.addAll(Test_Data.list_projects);
						break;
					case 3:
						t_position.clear();
						t_position.addAll(Test_Data.list_position);
						break;
					case 4:
						t_interview.clear();
						t_interview.addAll(Test_Data.list_interview);
						break;
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
				listAdapter.notifyDataSetChanged();
			}
			break;
		}
	}

	// @Override
	// public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo
	// menuInfo) {
	// super.onCreateContextMenu(menu, v, menuInfo);
	// MenuInflater inflater = getMenuInflater();
	// inflater.inflate(R.menu.menu_list_conteiner, menu);
	// }
	//
	// @Override
	// public boolean onContextItemSelected(MenuItem item) {
	//
	// AdapterContextMenuInfo info = (AdapterContextMenuInfo)
	// item.getMenuInfo();
	// int index = info.position;
	// switch (item.getItemId()) {
	// case R.id.lv_row_edit:
	// Intent intent = new Intent();
	// intent = (Intent) checkingCategory(CHECK_INTENT_EDIT);
	// switch ((Integer) checkingCategory(CHECK_R_ID)) {
	// case 1:
	// intent.putExtra("ID", t_people.get(index).getId());
	// Log.i(LOGTAG, "Edit person - " + t_people.get(index).getId() + " " +
	// t_people.get(index).getName());
	// break;
	// case 2:
	// intent.putExtra("ID", t_project.get(index).getId());
	// Log.i(LOGTAG, "Edit project - " + t_project.get(index).getId() + " " +
	// t_project.get(index).getName());
	// break;
	// case 3:
	// intent.putExtra("ID", t_position.get(index).getId());
	// Log.i(LOGTAG, "Edit position - " + t_position.get(index).getId() + " " +
	// t_position.get(index).getName());
	// break;
	// case 4:
	// intent.putExtra("ID", t_interview.get(index).getId());
	// Log.i(LOGTAG, "Edit interview - " + t_interview.get(index).getId() + " "
	// + t_interview.get(index).getName());
	// break;
	// }
	//
	// startActivityForResult(intent, RES_EDIT);
	// return true;
	// case R.id.lv_row_delete:
	// switch ((Integer) checkingCategory(CHECK_R_ID)) {
	// case 1:
	// db.deletePerson(db.getPerson((t_people.get(index).getId())));
	// Log.i(LOGTAG, "Delete person - " + (index + 1) + " " +
	// t_people.get(index).getName());
	// t_people.remove(index);
	// break;
	// case 2:
	// db.deleteProject(db.getProject((t_project.get(index).getId())));
	// Log.i(LOGTAG, "Delete project - " + (index + 1) + " " +
	// t_project.get(index).getName());
	// t_project.remove(index);
	// break;
	// case 3:
	// db.deletePosition(db.getPosition(t_position.get(index).getId()));
	// Log.i(LOGTAG, "Delete position - " + (index + 1) + " " +
	// t_position.get(index).getName());
	// t_position.remove(index);
	// break;
	// case 4:
	// db.deleteInterview(db.getInterview(t_interview.get(index).getId()));
	// Log.i(LOGTAG, "Delete interview - " + (index + 1) + " " +
	// t_interview.get(index).getName());
	// t_interview.remove(index);
	// break;
	// }
	// listAdapter.notifyDataSetChanged();
	// return true;
	// }
	// return false;
	// }

}
