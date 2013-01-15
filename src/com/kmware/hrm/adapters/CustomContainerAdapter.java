package com.kmware.hrm.adapters;

import java.util.ArrayList;

import com.kmware.hrm.model.BaseModel;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;

public abstract class CustomContainerAdapter<T extends BaseModel> extends ArrayAdapter<T> implements
		Filterable {

	public static String LOGTAG = CustomContainerAdapter.class.getSimpleName();

	Context ctx;
	LayoutInflater lInflater;
	public ArrayList<T> objects;
	private ArrayList<T> filterList;
	private ListFilter filter;

	CustomContainerAdapter(Context context, ArrayList<T> list,
			int layout) {
		super(context, layout, list);
		ctx = context;
		objects = list;
		filterList = new ArrayList<T>(list);
		lInflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	}

	public abstract View getView(int position, View convertView, ViewGroup parent);

	public abstract boolean comparator(T in,CharSequence constraint);
	
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
				ArrayList<T> founded = new ArrayList<T>();
				for (T t : filterList) {
					if (comparator(t,constraint))
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
			for (T o : (ArrayList<T>) filterResults.values) {
				add(o);
			}
			notifyDataSetChanged();

		}
	}

}
