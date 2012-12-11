package com.kmware.hrm;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ListContainer extends Activity implements OnClickListener {

	ImageView iv_People; 
	ImageView iv_Project;
	ImageView iv_Positions;
	ImageView iv_Interviews;
	TextView tv_Search;
	
		ArrayList<ContainerRow> dataList = new ArrayList<ContainerRow>();
		CustomContainerAdapter listAdapter;

		  /** Called when the activity is first created. */
		  public void onCreate(Bundle savedInstanceState) {
		    super.onCreate(savedInstanceState);
		    setContentView(R.layout.list_container);
		    init();
		   
		  }

		  private void init(){
			  iv_People = (ImageView) findViewById(R.id.ivPeople);
			  iv_People.setOnClickListener(this);
			  iv_Project = (ImageView) findViewById(R.id.ivProjects);
			  iv_Project.setOnClickListener(this);
			  iv_Positions = (ImageView) findViewById(R.id.ivPositions);
			  iv_Positions.setOnClickListener(this);
			  iv_Interviews = (ImageView) findViewById(R.id.ivInterviews);
			  iv_Interviews.setOnClickListener(this);
			  // создаем адаптер
			    fillData();
			    listAdapter = new CustomContainerAdapter(this, dataList);

			    // настраиваем список
			    ListView lvConteiner = (ListView) findViewById(R.id.lvConteiner);
			    lvConteiner.setAdapter(listAdapter); 
		  }
		  
		  // генерируем данные для адаптера
		  void fillData() {
		    for (int i = 1; i <= 20; i++) {
		    	dataList.add(new ContainerRow("Worker " + i, ""+i * 1000));
		    }
		  }

		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.ivPeople:
				setVisability(R.id.ivPeople);
				break;
			case R.id.ivProjects:
				setVisability(R.id.ivProjects);
				break;
			case R.id.ivPositions:
				setVisability(R.id.ivPositions);
				break;
			case R.id.ivInterviews:
				setVisability(R.id.ivInterviews);
				break;
			}
			
		}

		protected void setVisability(int id){
			ImageView ivTemp = (ImageView) findViewById(id);
			iv_People.setVisibility(View.VISIBLE);
			iv_Project.setVisibility(View.VISIBLE);
			iv_Positions.setVisibility(View.VISIBLE);
			iv_Interviews.setVisibility(View.VISIBLE);
			ivTemp.setVisibility(View.GONE);
		}
}
