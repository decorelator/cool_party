package com.kmware.hrm.adapters;

import java.util.ArrayList;
import com.kmware.hrm.R;
import com.kmware.hrm.model.Position;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomPositionAdapter extends CustomContainerAdapter<Position> implements
		Filterable {

	public static String LOGTAG = CustomPositionAdapter.class.getSimpleName();

	public CustomPositionAdapter(Context context, ArrayList<Position> list,
			int layout) {
		super(context, list, layout);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater
					.inflate(R.layout.list_container_row_position, parent, false);
		}

		Position p = (Position) getItem(position);

		((TextView) view.findViewById(R.id.tv_position_title)).setText(p.getName());
		//((TextView) view.findViewById(R.id.tv_position_status)).setText(p.getsData());

		return view;
	}

	@Override
	public boolean comparator(Position in, CharSequence constraint) {
		
		return (String.valueOf(in.getName().toLowerCase()).contains(constraint));
	}

}
