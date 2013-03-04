package com.kmware.hrm.adapters;

import java.util.ArrayList;
import com.kmware.hrm.R;
import com.kmware.hrm.model.People;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomPeopleAdapter extends CustomContainerAdapter<People> implements Filterable {

	public static String LOGTAG = CustomPeopleAdapter.class.getSimpleName();
	private Context context;
	
	public CustomPeopleAdapter(Context context, ArrayList<People> list, int layout) {
		super(context, list, layout);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.list_container_row_people, parent, false);
		}

		People p = (People) getItem(position);

		((TextView) view.findViewById(R.id.tv_people_title)).setText((p.getLastname() + " " +p.getName()).trim());
		if (p.getFacility() != 0 ){
		((TextView) view.findViewById(R.id.tv_people_facility)).setText(""+context.getResources().getStringArray(R.array.people_facility)[p.getFacility()-1]);
		}else{
			((TextView) view.findViewById(R.id.tv_people_facility)).setText(context.getResources().getString(R.string.sp_none));
		}
		
		return view;
	}

	@Override
	public boolean comparator(People in, CharSequence constraint) {

		return (String.valueOf((in.getName())).toLowerCase().contains(constraint) || String.valueOf((in.getLastname())).toLowerCase().contains(constraint));
	}

}
