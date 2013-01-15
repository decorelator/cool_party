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
	
	public CustomPeopleAdapter(Context context, ArrayList<People> list, int layout) {
		super(context, list, layout);
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater
					.inflate(R.layout.list_container_row_people, parent, false);
		}

		People p = (People) getItem(position);

		((TextView) view.findViewById(R.id.tv_people_title)).setText(p.getName());
		((TextView) view.findViewById(R.id.tv_people_position)).setText(p.getPosition());

		return view;
	}

//	@Override
//	public boolean comparator(BaseModel in, CharSequence constraint) {
//		People t = (People) in;
//		return (String.valueOf((t.getName())).toLowerCase().contains(constraint) || t.getPosition().toLowerCase().contains(constraint));
//
//	}

	@Override
	public boolean comparator(People in, CharSequence constraint) {
		// TODO Auto-generated method stub
		return(String.valueOf((in.getName())).toLowerCase().contains(constraint) || in.getPosition().toLowerCase().contains(constraint));
	}

}
