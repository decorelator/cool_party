package com.kmware.hrm.adapters;

import java.util.ArrayList;

import com.kmware.hrm.R;
import com.kmware.hrm.model.BaseModel;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<BaseModel> implements
		Filterable {

	public static String LOGTAG = CustomAdapter.class.getSimpleName();

	Context ctx;
	LayoutInflater lInflater;
	public ArrayList<BaseModel> objects;
	private ArrayList<BaseModel> filterList;
	private ListFilter filter;

	CustomAdapter(Context context, ArrayList<BaseModel> list,
			int layout) {
		super(context, layout, list);
		ctx = context;
		objects = list;
		filterList = new ArrayList<BaseModel>(list);
		lInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	// ����� ������
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// ���������� ���������, �� �� ������������ view
		View view = convertView;
		if (view == null) {
			view = lInflater
					.inflate(R.layout.list_container_row, parent, false);
		}

		BaseModel p = getItem(position);

		// ��������� View � ������ ������ �������
		((TextView) view.findViewById(R.id.tv_lv_Title)).setText("" + p.getId());
		((TextView) view.findViewById(R.id.tv_lv_Description)).setText(p.getName());

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
				ArrayList<BaseModel> founded = new ArrayList<BaseModel>();
				for (BaseModel t : filterList) {
					if (String.valueOf((t.getId())).contains(constraint)
							|| t.getName().toLowerCase().contains(constraint))
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
			for (BaseModel o : (ArrayList<BaseModel>) filterResults.values) {
				add(o);
			}
			notifyDataSetChanged();

		}
	}

}
