package com.kmware.hrm.view;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.app.DatePickerDialog;

public class CustomDatePickerDialog extends DatePickerDialog {
	
	private static int year_on_button;
	private static int month_on_button;
	private static int day_on_button;
	
	private SimpleDateFormat sdf;

	public CustomDatePickerDialog(Context context, OnDateSetListener callBack,
			int year, int monthOfYear, int dayOfMonth) {
		
		super(context, callBack, year, monthOfYear, dayOfMonth);
		
		sdf = new SimpleDateFormat("dd MMMM yyyy");
		
		setTitle(sdf.format(new Date(year - 1900, monthOfYear, dayOfMonth)));
		setOnDismissListener(new OnDismissListener() {	
			public void onDismiss(DialogInterface dialogInterface) {
				updateDate(year_on_button, month_on_button, day_on_button);
				Date date = new Date(year_on_button - 1900, month_on_button, day_on_button);
				setTitle(sdf.format(date));
			}
		});
		
		setOnCancelListener(new OnCancelListener() {
			public void onCancel(DialogInterface dialogInterface) {
				updateDate(year_on_button, month_on_button, day_on_button);
				Date date = new Date(year_on_button - 1900, month_on_button, day_on_button);
				setTitle(sdf.format(date));
			}
		});
	}
	
	@Override
	public void onDateChanged(DatePicker view, int year, int month, int day) {
		super.onDateChanged(view, year, month, day);
		setTitle(sdf.format(new Date(year - 1900, month, day)));
	}
	
	public static void setDate(int year, int month, int day){
		year_on_button = year;
		month_on_button = month;
		day_on_button = day;
	}
}