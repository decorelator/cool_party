package com.kmware.hrm.adapters;

import java.util.ArrayList;
import com.kmware.hrm.R;
import com.kmware.hrm.model.Project;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomProjectAdapter extends CustomContainerAdapter<Project> implements
		Filterable {

	public static String LOGTAG = CustomProjectAdapter.class.getSimpleName();

	public CustomProjectAdapter(Context context, ArrayList<Project> list,
			int layout) {
		super(context, list, layout);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater
					.inflate(R.layout.list_container_row_project, parent, false);
		}

		Project p = (Project) getItem(position);

		((TextView) view.findViewById(R.id.tv_project_title)).setText(p.getName());
		((TextView) view.findViewById(R.id.tv_project_startdate)).setText(p.getsData());
		((TextView) view.findViewById(R.id.tv_project_enddate)).setText(p.geteData());
	//	((TextView) view.findViewById(R.id.tv_project_status)).setText(p.getCount());

		return view;
	}

	@Override
	public boolean comparator(Project in, CharSequence constraint) {
		
		return ((String.valueOf(in.getName().toLowerCase()).contains(constraint)) || (in.getsData().contains(constraint)) || (in.getsData().contains(constraint)));
	}

}
