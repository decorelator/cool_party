package com.kmware.hrm.adapters;

import java.util.ArrayList;
import com.kmware.hrm.R;
import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.People;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomPeopleAdapter extends CustomContainerAdapter<People> implements Filterable {

	public static String LOGTAG = CustomPeopleAdapter.class.getSimpleName();
	private Context context;
	DatabaseHandler db;

	public CustomPeopleAdapter(Context context, ArrayList<People> list, int layout) {
		super(context, list, layout);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		db = new DatabaseHandler(context);
		if (view == null) {
			view = lInflater.inflate(R.layout.list_container_row_people, parent, false);
		}

		People p = (People) getItem(position);

		((TextView) view.findViewById(R.id.tv_people_title)).setText(p.getLastname() + " " +p.getName());
		((TextView) view.findViewById(R.id.tv_people_position)).setText(db.getPosition(p.getPosition()).getName());
		TextView tv_Status = (TextView) view.findViewById(R.id.tv_people_status);
		switch (p.getStatus_id()) {
		case 1:
			tv_Status.setText("E");
			tv_Status.setTextColor(Color.parseColor("#FFFFFF"));
			tv_Status.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.people_blue));
			break;
		default:
			tv_Status.setText("C");
			tv_Status.setTextColor(Color.parseColor("#000000"));
			tv_Status.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.people_orange));
		}

		return view;
	}

	@Override
	public boolean comparator(People in, CharSequence constraint) {
		DatabaseHandler db = new DatabaseHandler(context);
		String position = "";
		try {
			position = db.getPosition(in.getPosition()).getName();
		} catch (Exception ex) {
			ex.printStackTrace();
			Log.w(LOGTAG, "DB have not position with ID = " + in.getPosition());
		}

		return (String.valueOf((in.getName())).toLowerCase().contains(constraint) || position.toLowerCase().contains(constraint));
	}

}
