package com.kmware.hrm;

import java.text.DecimalFormat;
import java.util.Date;
import java.util.TimeZone;

public class Extras {

	public static String LOGTAG = Extras.class.getSimpleName();
 
	public static final int ID_UNKNOWN = -1;
	public static final int ONEDAY_PER_MILLISECONDS = 86400000;
	public static final int ONEMINUTE_PER_MILLISECONDS = 60000;
	public static final int ONEHOUR_PER_MILLISECONDS = 3600000;

	public static final long msecGMT = TimeZone.getDefault().getOffset(
			new Date().getTime());
	public static final char ENTER = '\n';
	public static final String SPACE = " ";
	public static final Object NULL_OBJECT = null;
	public static final String EMPTY_STRING = "";
	public static final String NA = "n/a";
	public static final String DASH = " - ";

	public static final DecimalFormat floatFormat = new DecimalFormat("0.00");
	
	public static final String DASHBOARD_INTENT = "dashboard";

}
