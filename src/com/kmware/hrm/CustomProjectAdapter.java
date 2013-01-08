package com.kmware.hrm;

import java.util.ArrayList;
import com.kmware.hrm.model.People;
import com.kmware.hrm.model.Project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomProjectAdapter extends ArrayAdapter<Project> implements
		Filterable {

	public static String LOGTAG = CustomProjectAdapter.class.getSimpleName();

	Context ctx;
	LayoutInflater lInflater;
	public ArrayList<Project> objects;
	private ArrayList<Project> filterList;
	private ListFilter filter;

	CustomProjectAdapter(Context context, ArrayList<Project> list,
			int layout) {
		super(context, layout, list);
		ctx = context;
		objects = list;
		filterList = new ArrayList<Project>(list);
		lInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// пункт списка
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// используем созданные, но не используемые view
		View view = convertView;
		if (view == null) {
			view = lInflater
					.inflate(R.layout.list_container_row_project, parent, false);
		}

		Project p = getItem(position);

		// заполняем View в пункте списка данными
		((TextView) view.findViewById(R.id.tv_project_title)).setText(p.getProject());
		((TextView) view.findViewById(R.id.tv_project_startdate)).setText(p.getsData());
		((TextView) view.findViewById(R.id.tv_project_enddate)).setText(p.geteData());
		((TextView) view.findViewById(R.id.tv_project_status)).setText(p.getCount());

		return view;
	}

	@Override
	public Filter getFilter() {
		if (filter == null){
		 filter = new ListFilter();
		}
		return filter;
	}

	private class ListFilter extends Filter {
		@Override
		protected FilterResults performFiltering(CharSequence constraint) {
			// NOTE: this function is *always* called from a background thread,
			// and
			// not the UI thread.
			constraint = constraint.toString().toLowerCase();
			FilterResults result = new FilterResults();
			if (constraint != null && constraint.toString().length() > 0) {
				ArrayList<Project> founded = new ArrayList<Project>();
				for (Project t : filterList) {
					if (String.valueOf((t.getName())).toLowerCase().contains(constraint))
						founded.add(t);
				}

				result.values = founded;
				result.count = founded.size();
			} else {
				result.values = filterList;
				result.count = filterList.size();
			}

			return result;
		}

		@Override
		protected void publishResults(CharSequence charSequence,
				FilterResults filterResults) {
			clear();
			for (Project o : (ArrayList<Project>) filterResults.values) {
				add(o);
			}
			notifyDataSetChanged();

		}
	}

}
