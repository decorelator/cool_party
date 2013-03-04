package com.kmware.hrm.adapters;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.kmware.hrm.R;
import com.kmware.hrm.model.Project;
import android.content.Context;
import android.text.format.DateFormat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomProjectAdapter extends CustomContainerAdapter<Project> implements Filterable {

	public static String LOGTAG = CustomProjectAdapter.class.getSimpleName();
	private Context context;

	public CustomProjectAdapter(Context context, ArrayList<Project> list, int layout) {
		super(context, list, layout);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.list_container_row_project, parent, false);
		}

		Project p = (Project) getItem(position);

		((TextView) view.findViewById(R.id.tv_project_title)).setText(p.getName());
		String parser = "" + p.getsData();
		String[] date = parser.split(":");
		GregorianCalendar calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
		((TextView) view.findViewById(R.id.tv_project_startdate)).setText(": " + DateFormat.format("dd.MM.yyyy", calendar.getTime()));
		parser = "" + p.geteData();
		date = parser.split(":");
		calendar = new GregorianCalendar(Integer.parseInt(date[2]), Integer.parseInt(date[1]), Integer.parseInt(date[0]));
		((TextView) view.findViewById(R.id.tv_project_enddate)).setText(": " + DateFormat.format("dd.MM.yyyy", calendar.getTime()));

		return view;
	}

	@Override
	public boolean comparator(Project in, CharSequence constraint) {

		return ((String.valueOf(in.getName().toLowerCase()).contains(constraint)) || (in.getsData().contains(constraint)) || (in.getsData()
				.contains(constraint)));
	}

}
