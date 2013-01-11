package com.kmware.hrm.view;

import java.sql.Time;
import java.text.SimpleDateFormat;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.TimePicker;
import android.app.TimePickerDialog;

public class CustomTimePickerDialog extends TimePickerDialog {
	
	private static int hour_of_day;
	private static int minute_of_hour;
	
	private SimpleDateFormat sdf;

	public CustomTimePickerDialog(Context context, OnTimeSetListener callBack,
			int hour, int minute) {
		super(context, callBack, hour, minute, true);
		
		sdf = new SimpleDateFormat("H:mm");
		
		setTitle(sdf.format(new Time(hour, minute, 0)));
		setOnDismissListener(new OnDismissListener() {	
			public void onDismiss(DialogInterface dialogInterface) {
				updateTime(hour_of_day, minute_of_hour);
				Time time = new Time(hour_of_day, minute_of_hour, 0);
				setTitle(sdf.format(time));
			}
		});
		
		setOnCancelListener(new OnCancelListener() {
			public void onCancel(DialogInterface dialogInterface) {
				updateTime(hour_of_day, minute_of_hour);
				Time time = new Time(hour_of_day, minute_of_hour, 0);
				setTitle(sdf.format(time));
			}
		});
	}
	
	@Override
	public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
		super.onTimeChanged(view, hourOfDay, minute);
		setTitle(sdf.format(new Time(hourOfDay,minute,0)));
	}
	
	public static void setTime(int hour, int minute){
		hour_of_day = hour;
		minute_of_hour = minute;
	}
}