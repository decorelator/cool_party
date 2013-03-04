package com.kmware.hrm.adapters;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;
import com.kmware.hrm.R;
import com.kmware.hrm.Test_Data;
import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.Interviewer;
import com.kmware.hrm.model.People;
import com.kmware.hrm.model.Position;
import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomInterviewerAdapter extends CustomContainerAdapter<Interviewer> implements Filterable {

	public static String LOGTAG = CustomInterviewerAdapter.class.getSimpleName();
//	private Context context;
	DatabaseHandler db;
	Interviewer p;
	List<People> candidates;
	List<Position> positions;

	public CustomInterviewerAdapter(Context context, ArrayList<Interviewer> list, int layout) {
		super(context, list, layout);
//		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.list_container_row_interview, parent, false);
		}
		p = (Interviewer) getItem(position);

		((TextView) view.findViewById(R.id.tv_interview_title)).setText(p.getName());

		int i = 0;
		try {
			while (Test_Data.list_position.get(i).getId() != p.getPosition() && i < Test_Data.list_position.size()) {
				i++;
				
			}

			((TextView) view.findViewById(R.id.tv_interview_position)).setText(" " + Test_Data.list_position.get(i).getName());
		} catch (Exception ex) {
			((TextView) view.findViewById(R.id.tv_interview_position)).setText(" ");
			ex.printStackTrace();
			Log.w(LOGTAG, "OutOfBound in listpositiona");
		}

		int j = 0;
		try {
			while (Test_Data.list_projects.get(j).getId() != Test_Data.list_position.get(i).getProject() && j < Test_Data.list_projects.size()) {
				j++;
			}

			((TextView) view.findViewById(R.id.tv_interview_project)).setText(" " + Test_Data.list_projects.get(j).getName());
		} catch (Exception ex) {
			((TextView) view.findViewById(R.id.tv_interview_project)).setText(" ");
			ex.printStackTrace();
			Log.w(LOGTAG, "OutOfBound in listprojects");
		}

		

		String parser = "" + p.getDate();
		String[] date = parser.split(":");
		if (date.length > 1 && !parser.equals("null")) {
			int year = Integer.parseInt(date[2]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[0]);
			GregorianCalendar calendar = new GregorianCalendar(year, month, day);
			((TextView) view.findViewById(R.id.tv_interview_date)).setText(DateFormat.format("dd.MM.yy", calendar.getTime()));
		}
		
		parser = "" + p.getTime();
		 date = parser.split(":");
		 if (date.length > 1 && !parser.equals("null")) {
				int hour = Integer.parseInt(date[0]);
				int minute = Integer.parseInt(date[1]);
				
				GregorianCalendar calendar = new GregorianCalendar(0, 0, 0, hour, minute);
				((TextView) view.findViewById(R.id.tv_interview_time)).setText(DateFormat.format("hh:mm a", calendar.getTime()));
			}
		
		return view;
	}

	@Override
	public boolean comparator(Interviewer in, CharSequence constraint) {
		return (in.getName().toLowerCase()).contains(constraint) /*
																 * ||
																 * (in.getPosition
																 * (
																 * ).toLowerCase
																 * ().contains(
																 * constraint))
																 * ||
																 * (in.getProject
																 * (
																 * ).toLowerCase
																 * ().contains(
																 * constraint))
																 */;
	}

}
