package com.kmware.hrm.adapters;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import com.kmware.hrm.R;
import com.kmware.hrm.db.DatabaseHandler;
import com.kmware.hrm.model.Interviewer;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.TextView;

public class CustomInterviewerAdapter extends CustomContainerAdapter<Interviewer> implements Filterable {

	public static String LOGTAG = CustomInterviewerAdapter.class.getSimpleName();
	private Context context;
	String project;
	String position;
	DatabaseHandler db;

	public CustomInterviewerAdapter(Context context, ArrayList<Interviewer> list, int layout) {
		super(context, list, layout);
		this.context = context;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		if (view == null) {
			view = lInflater.inflate(R.layout.list_container_row_interview, parent, false);
		}
		db = new DatabaseHandler(context);
		Interviewer p = (Interviewer) getItem(position);

		((TextView) view.findViewById(R.id.tv_interview_title)).setText(p.getName());
		project = "";
		try {
			project = (db.getProject(p.getProject())).getName();
		} catch (SQLiteException ex) {
			ex.printStackTrace();
			Log.w(LOGTAG, "Project DB have not position = " + p.getProject());
		}
		((TextView) view.findViewById(R.id.tv_interview_project)).setText(" " + project);
		this.position = "";
		try {
			this.position = db.getPosition(p.getPosition()).getName();
		} catch (SQLiteException ex) {
			ex.printStackTrace();
			Log.w(LOGTAG, "Position DB have not position = " + p.getPosition());
		}
		((TextView) view.findViewById(R.id.tv_interview_position)).setText(" " + this.position);
		if (p.getPhone() != 0) {
			((TextView) view.findViewById(R.id.tv_interview_phone)).setText(" " + p.getPhone());
		}
		((TextView) view.findViewById(R.id.tv_interview_time)).setText(p.getTime());

		String parser = "" + p.getDate();
		String[] date = parser.split(":");
		if (date.length > 1 && !parser.equals("null")) {
			int year = Integer.parseInt(date[2]);
			int month = Integer.parseInt(date[1]);
			int day = Integer.parseInt(date[0]);
			GregorianCalendar calendar = new GregorianCalendar(year, month, day);
			((TextView) view.findViewById(R.id.tv_interview_date)).setText(DateFormat.format("dd.MM.yy", calendar.getTime()));
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
