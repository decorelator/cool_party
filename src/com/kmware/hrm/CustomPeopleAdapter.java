package com.kmware.hrm;

import java.util.ArrayList;
import com.kmware.hrm.model.People;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomPeopleAdapter extends ArrayAdapter<People> implements
		Filterable {

	public static String LOGTAG = CustomPeopleAdapter.class.getSimpleName();

	Context ctx;
	LayoutInflater lInflater;
	public ArrayList<People> objects;
	private ArrayList<People> filterList;
	private ListFilter filter;

	CustomPeopleAdapter(Context context, ArrayList<People> list,
			int layout) {
		super(context, layout, list);
		ctx = context;
		objects = list;
		filterList = new ArrayList<People>(list);
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
					.inflate(R.layout.list_container_row_people, parent, false);
		}

		People p = getItem(position);

		// заполняем View в пункте списка данными
		((TextView) view.findViewById(R.id.tv_people_title)).setText(p.getName());
		((TextView) view.findViewById(R.id.tv_people_position)).setText(p.getPosition());
		((TextView) view.findViewById(R.id.tv_people_status)).setText(p.getStatus());

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
				ArrayList<People> founded = new ArrayList<People>();
				for (People t : filterList) {
					if (String.valueOf((t.getName())).toLowerCase().contains(constraint)
							|| t.getPosition().toLowerCase().contains(constraint))
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
			for (People o : (ArrayList<People>) filterResults.values) {
				add(o);
			}
			notifyDataSetChanged();

		}
	}

}
