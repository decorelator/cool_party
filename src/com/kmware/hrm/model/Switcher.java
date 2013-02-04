package com.kmware.hrm.model;

public class Switcher {
//	private int ico;
	private String caption;
	private int id;
	
	public Switcher(int id, /*int ico,*/ String caption) {
//		this.ico = ico;
		this.caption = caption;
		this.id = id;
	}


	public String getCaption() {
		return caption;
	}
	public int getId()
	{
		return id;
	}
	
}
