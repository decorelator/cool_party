package com.kmware.hrm;

import java.util.ArrayList;

import com.kmware.hrm.model.BaseModel;
import com.kmware.hrm.model.People;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomPeopleAdapter extends CustomContainerAdapter<BaseModel> implements Filterable {

	CustomPeopleAdapter(Context context, ArrayList<BaseModel> list, int layout) {
		super(context, list, layout);
		// TODO Auto-generated constructor stub
	}

	public static String LOGTAG = CustomPeopleAdapter.class.getSimpleName();

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater
					.inflate(R.layout.list_container_row_people, parent, false);
		}

		People p = (People) getItem(position);

		// заполняем View в пункте списка данными
		((TextView) view.findViewById(R.id.tv_people_title)).setText(p.getName());
		((TextView) view.findViewById(R.id.tv_people_position)).setText(p.getPosition());

		return view;
	}

	@Override
	public boolean comparator(BaseModel in, CharSequence constraint) {
		People t = (People) in;
		return (String.valueOf((t.getName())).toLowerCase().contains(constraint) || t.getPosition().toLowerCase().contains(constraint));

	}

}
