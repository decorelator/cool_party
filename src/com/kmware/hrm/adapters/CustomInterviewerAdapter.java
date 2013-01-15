package com.kmware.hrm.adapters;

import java.util.ArrayList;
import com.kmware.hrm.R;
import com.kmware.hrm.model.Interviewer;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomInterviewerAdapter extends CustomContainerAdapter<Interviewer> implements
		Filterable {

	public static String LOGTAG = CustomInterviewerAdapter.class.getSimpleName();

	public CustomInterviewerAdapter(Context context, ArrayList<Interviewer> list,
			int layout) {
		super(context, list, layout);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater
					.inflate(R.layout.list_container_row_interview, parent, false);
		}

		Interviewer p = (Interviewer) getItem(position);

		((TextView) view.findViewById(R.id.tv_interview_title)).setText(p.getName());
		((TextView) view.findViewById(R.id.tv_interview_project)).setText(p.getProject());
		((TextView) view.findViewById(R.id.tv_interview_position)).setText(p.getPosition());
		((TextView) view.findViewById(R.id.tv_interview_phone)).setText(p.getPhone());
	//	((TextView) view.findViewById(R.id.tv_interview_time)).setText(p.getTime());
	//	((TextView) view.findViewById(R.id.tv_interview_date)).setText(p.getDate());

		return view;
	}

	@Override
	public boolean comparator(Interviewer in, CharSequence constraint) {
		return (in.getName().toLowerCase()).contains(constraint) || (in.getPosition().toLowerCase().contains(constraint)) || (in.getProject().toLowerCase().contains(constraint));
	}

}
